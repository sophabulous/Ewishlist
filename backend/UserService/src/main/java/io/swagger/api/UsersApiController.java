package io.swagger.api;

import io.swagger.model.LoginRequest;
import io.swagger.model.LoginToken;
import io.swagger.model.NewUserRequest;
import io.swagger.model.UpdateUserRequest;

import io.swagger.database.UserJdbcDatabase;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-01T17:36:03.863Z")

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserJdbcDatabase db;

    @Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> authenticateAdminToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        String accept = request.getHeader("Accept");
        try {
            if(db.validateAdmin(body)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (IOException e) {
	        return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    public ResponseEntity<Void> authenticateToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        log.info("authenticating user");
        try {
            if(db.validateToken(body)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (IOException e) {
            return new ResponseEntity<Void>(HttpStatus.I_AM_A_TEAPOT);
        }
        
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        log.info("deleting user");
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<LoginToken> getUserToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginRequest body) {
        log.info("login user");
        String accept = request.getHeader("Accept");
        log.info("login: " + body.toString());
        if (accept != null && accept.contains("application/json")) {
            try {

                LoginToken t;

                try {
                    t = db.loginUser(body);
                } catch(IOException e) {
                    log.info("couldn't authenticate"+body.toString());
                    return new ResponseEntity<LoginToken>(HttpStatus.METHOD_NOT_ALLOWED);
                }
                return new ResponseEntity<LoginToken>(t, HttpStatus.OK);

            }catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<LoginToken>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        log.error("headers of the request not set properly");
        return new ResponseEntity<LoginToken>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    public ResponseEntity<Void> invalidateToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        log.info("log off user");
        try {
            log.info(body.toString());
            if(db.invalidateToken(body)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                log.error("couldn't invalidate token");
                return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (IOException e) {
            return new ResponseEntity<Void>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    public ResponseEntity<Void> newUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody NewUserRequest body) {
        log.info("create new user");
        try {
            log.info(body.toString());
            db.createUser(body);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<Void> pingUsers() {
        log.info("pinged");
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UpdateUserRequest body) {
        log.info("update user");
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
