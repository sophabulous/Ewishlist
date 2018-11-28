package batch;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.api.APIHandler;
import io.swagger.api.TrackingApiController;
import io.swagger.database.api.JdbcDatabase;
import io.swagger.model.ProductItem;
import io.swagger.model.UserEmailItem;

public class BatchProductUpdate {

    private static final Logger log = LoggerFactory.getLogger(TrackingApiController.class);
	
	public static void updateAllProducts(JdbcDatabase db, APIHandler apiHandler)
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
    
	public static void sendEmailsForUpdatedProducts(JdbcDatabase db)
	{
		Stream<UserEmailItem> items = db.getUsersToNotify();
		//TODO email users
		items.forEach(i -> log.error("Emailing User[" + i.getUsername() + "] at [" + i.getEmail() + "]"));
		items.close();
	}

	
}
