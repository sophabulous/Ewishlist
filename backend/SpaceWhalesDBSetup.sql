-- Database: SpaceWhales

-- DROP DATABASE "SpaceWhales";

CREATE DATABASE "SpaceWhales"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_Canada.1252'
    LC_CTYPE = 'English_Canada.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
    

-- Extension for uuid generation (primarily for tokens)
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


-- Table: public.products

DROP TABLE IF EXISTS public.products CASCADE;

CREATE TABLE public.products
(
    item_name text COLLATE pg_catalog."default" NOT NULL,
    site text COLLATE pg_catalog."default" NOT NULL,
    yesterday_price real NOT NULL,
    current_price real NOT NULL,
    CONSTRAINT items_pkey PRIMARY KEY (site)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.products
    OWNER to postgres;
    
-- Table: public.users

DROP TABLE IF EXISTS public.users CASCADE;

CREATE TABLE public.users
(
    user_name text COLLATE pg_catalog."default" NOT NULL,
    user_id serial,
    password_hash text COLLATE pg_catalog."default" NOT NULL,
    email text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;

-- Table: public.wishlist

DROP TABLE IF EXISTS public.wishlist CASCADE;

CREATE TABLE public.wishlist
(
    user_id int NOT NULL,
    site text COLLATE pg_catalog."default" NOT NULL,
    trigger_price real NOT NULL,
    CONSTRAINT wishlist_pkey PRIMARY KEY (user_id, site),
    CONSTRAINT wishlist_site_fkey FOREIGN KEY (site)
        REFERENCES public.products (site) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT wishlist_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.wishlist
    OWNER to postgres;

-- Table: public.user_session

DROP TABLE IF EXISTS public.user_session CASCADE;

CREATE TABLE public.user_session
(
    user_id int NOT NULL,
    session_uuid text COLLATE pg_catalog."default" NOT NULL,
    start_time timestamp NOT NULL,
    expiry_time timestamp NOT NULL,
    extended_count int NOT NULL, -- number of times we've extended this session
    terminated boolean DEFAULT FALSE NOT NULL, -- indicates if this session has been manually terminated and should be treated as inactive
    CONSTRAINT user_session_pkey PRIMARY KEY (user_id, session_uuid),
    CONSTRAINT user_session_userid_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.user_session
    OWNER to postgres;


-- Takes a username, password and email.
-- if conflicting credentials exist in database, or the provided credentials are invalid for any other reason, return false
-- otherwise return true
CREATE OR REPLACE FUNCTION createUser(IN user_name text, IN password text, IN email text, OUT status boolean)
AS $$
begin
    -- duplicate credentials
    IF EXISTS(SELECT * FROM users U WHERE U.user_name = createUser.user_name OR U.email = createUser.email) THEN
        status := FALSE;
        return;
    END IF; 
    
    INSERT INTO users (user_name, password_hash, email)
    VALUES (createUser.user_name, createUser.password, createUser.email);
    
    status := TRUE;
END;
$$
language plpgsql;

-- Takes a username and password hash
-- if invalid credentials, return null.
-- else refreshes tokens which are still valid.
-- else logs in a user and gives them a new token.
CREATE OR REPLACE FUNCTION loginUser(IN user_name text, IN password text, OUT session_token text, OUT expiry timestamp)
AS $$
DECLARE u_id INT;
BEGIN
    SELECT U.user_id INTO u_id
    FROM users U
    WHERE U.user_name = loginUser.user_name AND U.password_hash = loginUser.password;

    -- user doesn't exist in system
    IF u_id IS NULL THEN
        session_token := NULL;
        expiry := NULL;
        return;    
    END IF;

    expiry := NOW() + interval '24 hours';

    -- existing active session - we'll extend it
    -- can reasonably occur if a user uses multiple devices - we don't want to invalidate the 
    -- other session, and it's a lot more work to make device-based sessions.
    IF EXISTS(SELECT * FROM user_session S WHERE S.user_id = u_id AND S.expiry_time > NOW() AND S.terminated = FALSE) THEN
        UPDATE user_session S
        SET expiry_time = expiry, extended_count = extended_count + 1
        WHERE S.user_id = u_id 
        AND S.expiry_time > NOW() 
        AND S.terminated = FALSE;

        session_token := (
            SELECT S.session_uuid 
            FROM user_session S 
            WHERE S.user_id = u_id 
            AND S.expiry_time > NOW() 
            AND S.terminated = FALSE);
        RETURN;
    END IF;

    session_token := uuid_generate_v4();

    -- new session    
    INSERT INTO user_session 
        VALUES (u_id, session_token, NOW(), expiry, 0);

END;
$$
language plpgsql;


-- Takes a parameterized login token
-- if the token is currently valid, set status to true, else false
-- if the token has ever been valid, return the expiration time, else null
CREATE OR REPLACE FUNCTION validateToken(IN user_name text, IN session_token text, OUT expiry timestamp, OUT status boolean)
AS $$
begin
    SELECT T.expiry_time INTO expiry
    FROM (SELECT expiry_time
            FROM users U 
            JOIN user_session S on U.user_id = S.user_id 
            WHERE U.user_name = validateToken.user_name 
            AND S.session_uuid = session_token 
            AND S.terminated = FALSE) AS T;

    status := expiry IS NOT NULL AND expiry > NOW();
END;
$$
language plpgsql; 


-- Takes a parameterized login token 
-- if the token is currently valid, set status to true and invalidate the user_session
-- if the token has been previously terminated or has expired, set status to false, else true.
-- if the provided token was invalid, set legalToken to false, else true.
CREATE OR REPLACE FUNCTION invalidateToken(IN user_name text, IN session_token text, OUT legalToken boolean, OUT status boolean)
AS $$
DECLARE u_id int;
DECLARE term boolean;
BEGIN
    SELECT U.user_id, S.terminated OR S.expiry_time < NOW() INTO u_id, term
        FROM users U
        JOIN user_session S on U.user_id = S.user_id 
        WHERE U.user_name = invalidateToken.user_name 
        AND S.session_uuid = session_token;

    IF u_id IS NOT NULL THEN
        legalToken := TRUE;

        IF term = FALSE THEN
            UPDATE user_session S
            SET terminated = TRUE
            WHERE S.user_id = u_id AND S.session_uuid = session_token;

            status := TRUE;

        ELSE
            status := FALSE;

        END IF;
    ELSE
        status := FALSE;
        legalToken := FALSE;
    END IF;
END;
$$
language plpgsql; 


