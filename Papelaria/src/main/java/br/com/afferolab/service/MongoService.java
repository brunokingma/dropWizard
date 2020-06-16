package br.com.afferolab.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;

import br.com.afferolab.domain.Stock;

import static com.mongodb.client.model.Filters.*;

public class MongoService {

    public void insertOne(MongoCollection<Document> collection, Document document) {
        collection.insertOne(document);
    }

    public void insertMany(MongoCollection<Document> collection, List<Document> documents) {
        collection.insertMany(documents);
    }

    public List<Document> find(MongoCollection<Document> collection) {
        return collection.find().into(new ArrayList<>());
    }

    public List<Document> findByKey(MongoCollection<Document> collection, String key, String value) {
        return collection.find(eq(key, value)).into(new ArrayList<>());
    }

    public List<Document> findByCriteria(MongoCollection<Document> collection, String key, int lessThanValue, int greaterThanValue, int sortOrder) {
        List<Document> documents = new ArrayList<>();
        FindIterable iterable = collection.find(and(lt(key, lessThanValue),
                gt(key, greaterThanValue))).sort(new Document(key, sortOrder));
        iterable.into(documents);
        return documents;
    }

    public void updateOneStock(MongoCollection<Document> collection, Document document) {
       
    	collection.updateOne(
        		eq("id", document.get("id")),
                new Document("$set",
                		new Document("description",document.get("description").toString())
	                		.append("category", document.get("category").toString())
	                		.append("quantity", document.get("quantity").toString())
	                		.append("name", document.get("name").toString())
                		));

    }

    public void deleteOne(MongoCollection<Document> collection, String key, String value) {
        collection.deleteOne(eq(key, value));
    }
}