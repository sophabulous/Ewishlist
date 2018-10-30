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
    

-- Table: public.products

-- DROP TABLE public.products;

CREATE TABLE public.products
(
    item_name text COLLATE pg_catalog."default" NOT NULL,
    site text COLLATE pg_catalog."default" NOT NULL,
    trigger_price real NOT NULL,
    CONSTRAINT items_pkey PRIMARY KEY (site)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.products
    OWNER to postgres;
    
-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    user_name text COLLATE pg_catalog."default" NOT NULL,
    user_id text COLLATE pg_catalog."default" NOT NULL,
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

-- DROP TABLE public.wishlist;

CREATE TABLE public.wishlist
(
    user_id text COLLATE pg_catalog."default" NOT NULL,
    site text COLLATE pg_catalog."default" NOT NULL,
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