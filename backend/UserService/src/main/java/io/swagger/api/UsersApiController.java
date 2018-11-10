package io.swagger.api;

import io.swagger.model.LoginRequest;
import io.swagger.model.LoginToken;
import io.swagger.model.NewUserRequest;
import io.swagger.model.UpdateUserRequest;

import com.EbucketList.database.UserJdbcDatabase;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-11-01T17:36:03.863Z")

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> authenticateToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        UserJdbcDatabase db;
        try {
            db = new UserJdbcDatabase();
            db.init();
        } catch (IOException e) {
            log.error("database connection could not be established");
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
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
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<LoginToken> getUserToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginRequest body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {

                LoginToken t;
                UserJdbcDatabase db = new UserJdbcDatabase();
                db.init();
                try {
                    t = db.loginUser(body);
                } catch(IOException e) {
                    return new ResponseEntity<LoginToken>(HttpStatus.METHOD_NOT_ALLOWED);
                }
                return new ResponseEntity<LoginToken>(t, HttpStatus.OK);

            } catch (IOException e) {
                log.error("database connection could not be established");
                return new ResponseEntity<LoginToken>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<LoginToken>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<LoginToken>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    public ResponseEntity<Void> invalidateToken(@ApiParam(value = "" ,required=true )  @Valid @RequestBody LoginToken body) {
        UserJdbcDatabase db;
        try {
            db = new UserJdbcDatabase();
            db.init();
        } catch (IOException e) {
            log.error("database connection could not be established");
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        try {
            if(db.invalidateToken(body)) {
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (IOException e) {
            return new ResponseEntity<Void>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    public ResponseEntity<Void> newUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody NewUserRequest body) {
        UserJdbcDatabase db;
        try {
            db = new UserJdbcDatabase();
            db.init();
        } catch (IOException e) {
            log.error("database connection could not be established");
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        try {
            db.createUser(body);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<Void> pingUsers() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UpdateUserRequest body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
