package external.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.swagger.model.LoginToken;

@Component
public class UserServiceAPI {

    @Value("${external.api.validateToken}")
    private String userServiceValidateToken;

    @Value("${external.api.validateTokenAdmin}")
    private String userServiceValidateTokenAdmin;
    
    /*uses the user service to authenticate user token*/
   public boolean validateToken(LoginToken loginToken){
       RestTemplate restTemplate = new RestTemplate();
       try {
       	ResponseEntity<String> response = restTemplate.postForEntity(userServiceValidateToken, loginToken, String.class);
       	return response.getStatusCode().value() == 200;        	
       } catch (Exception e) {
       	return false;
       }
   }
   
   /* uses the user service to validate an admin token */
   public boolean validateAdmin(LoginToken loginToken){
       RestTemplate restTemplate = new RestTemplate();
       try {
       	ResponseEntity<String> response = restTemplate.postForEntity(userServiceValidateTokenAdmin, loginToken, String.class);
       	return response.getStatusCode().value() == 200;        	
       } catch (Exception e) {
       	return false;
       }
   }


}
