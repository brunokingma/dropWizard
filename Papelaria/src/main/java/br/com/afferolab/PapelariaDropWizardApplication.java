package br.com.afferolab;

import io.dropwizard.Application;
import io.dropwizard.bundles.assets.ConfiguredAssetsBundle;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.afferolab.domain.Stock;
import br.com.afferolab.health.MongoDBHealthCheck;
import br.com.afferolab.resources.StockResources;
import br.com.afferolab.service.MongoService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.bson.Document;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PapelariaDropWizardApplication extends Application<PapelariaDropWizardConfiguration> {

	
    private static final Logger logger = LoggerFactory.getLogger(PapelariaDropWizardApplication.class);

    
    public static void main(final String[] args) throws Exception {
        new PapelariaDropWizardApplication().run(args);
    }



    @Override
    public void initialize(final Bootstrap<PapelariaDropWizardConfiguration> bootstrap) {
    	bootstrap.addBundle(new ConfiguredAssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(final PapelariaDropWizardConfiguration configuration,
                    final Environment environment) {
    	
    	
    	 // Enable CORS headers
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    	
    	MongoClientURI uri = new MongoClientURI(configuration.getHost());
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase(configuration.getDbName());
        MongoCollection<Document> collection = db.getCollection(configuration.getCollName());
       
        logger.info("++================ Registering RESTful API resources ==============++");
        
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(new StockResources(collection, new MongoService()));
        environment.healthChecks().register("DropwizardMongoDBHealthCheck",new MongoDBHealthCheck(mongoClient));
    }
    

}
