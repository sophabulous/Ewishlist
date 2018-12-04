package batch;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.EbucketList.email.Email;

import io.swagger.api.APIHandler;
import io.swagger.api.TrackingApiController;
import io.swagger.database.api.JdbcDatabase;
import io.swagger.model.ProductItem;
import io.swagger.model.UserEmailItem;

@Component
public class BatchProductUpdate {
	
    private static final Logger log = LoggerFactory.getLogger(TrackingApiController.class);

    @Value("${batch.email_address}")
    private String email_address;

    @Value("${batch.email_password}")
    private String email_password;
    
	public void updateAllProducts(JdbcDatabase db, APIHandler apiHandler)
	{
		Stream<ProductItem> items = db.getAllProducts();
		// insert arbitrary level of parallelism
		items.forEach(i -> 
			{
				try {
					i.setCurrentPrice(apiHandler.getPrice(i.getUrl()));
					db.updatePrice(i);
					log.debug("Updated " + i.getUrl());
				} catch (Exception e) {
					log.error("Could not update " + i.getUrl());
					// probably set an error flag on the product since it's url is broken
				}
			});
		items.close();
	}
    
	public void sendEmailsForUpdatedProducts(JdbcDatabase db)
	{
		Stream<UserEmailItem> items = db.getUsersToNotify();

		final String subject = "URGENT: SALE NOTIFICATIONS FROM SPACEWHALES";
		final String msg = "THERE IS STUFF ON SALE \n\n "
						+ "WOW GREAT PRICES \n\n\n "
						+ "MUCH DISCOUNT \n\n\n\n "
						+ "Koolaid";
		
		items.forEach(i -> {
			log.debug("Emailing User[" + i.getUsername() + "] at [" + i.getEmail() + "]");
			Email.sendEmail(email_address, email_password, i.getEmail(), subject, i.getUsername().toUpperCase() + " " + msg);
		});
		
		items.close();
	}

	
}
