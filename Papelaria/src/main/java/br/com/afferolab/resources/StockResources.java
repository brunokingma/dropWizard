package br.com.afferolab.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import br.com.afferolab.domain.Stock;
import br.com.afferolab.service.MongoService;
import io.swagger.annotations.Api;
import org.bson.Document;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/stock")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "stock", description = "Stock CRUD")
public class StockResources {

    private MongoCollection<Document> collection;
    private final MongoService mongoService;

    public StockResources(MongoCollection<Document> collection, MongoService mongoService) {
        this.collection = collection;
        this.mongoService = mongoService;
    }

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStock(@NotNull @Valid final Stock stock) {
    	
        Gson gson = new Gson();
        String json = gson.toJson(stock);
        mongoService.insertOne(collection, new Document(BasicDBObject.parse(json)));
        List<Document> documents = mongoService.find(collection);
        return Response.ok(documents).build();
        
    }



    @GET
    @Timed
    public Response getStock() {
        List<Document> documents = mongoService.find(collection);
        return Response.ok(documents).build();
    }

    @GET
    @Timed
    @Path("{name}")
    public Response getStock(@PathParam("name") final String name) {
        List<Document> documents = mongoService.findByKey(collection, "name", name);
        return Response.ok(documents).build();
    }



    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editStock(@NotNull @Valid final Stock stock) {
        Gson gson = new Gson();
        String json = gson.toJson(stock);
        mongoService.updateOneStock(collection, new Document(BasicDBObject.parse(json)));
        List<Document> documents = mongoService.find(collection);
        return Response.ok(documents).build();
    }
    

    @DELETE
    @Timed
    @Path("{id}")
    public Response deleteStock(@PathParam("id") final String id) {
        mongoService.deleteOne(collection, "id", id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Stock with Id: " + id + " deleted successfully");
        return Response.ok(response).build();
    }
}