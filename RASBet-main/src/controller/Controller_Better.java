package src.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class Controller_Better {

    public void betterHistory(String user) {
        // establish connection
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://rasbet:rasbet2022@rasbet.ov7kl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // get user_management database
        MongoDatabase user_management = mongoClient.getDatabase("bets_history");

        // get betters collection
        MongoCollection<Document> betters_history = user_management.getCollection("history");

        // get all documents
        try (MongoCursor<Document> cursor = betters_history.find().iterator()) {
            while (cursor.hasNext()) {
                // get the line
                Document doc = cursor.next();

                if (doc.get("user").equals(user)) {
                    int odd = (int) doc.get("odd_id");
                    System.out.println("ODD ID: " + odd);

                    double amount = (double) doc.get("amount_bet");
                    System.out.println("Valor apostado: " + amount);

                    double results = (double) doc.get("results");
                    System.out.println("Valor ganho: " + results);

                    System.out.println();
                }
            }
        }

        mongoClient.close();
    }

    public boolean changePassword(String user, String passwd) {
        // establish connection
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://rasbet:rasbet2022@rasbet.ov7kl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // get user_management database
        MongoDatabase user_management = mongoClient.getDatabase("user_management");

        // get betters collection
        MongoCollection<Document> betters_collect = user_management.getCollection("better");

        // create replacement
        Document replacement = new Document();
        replacement.put("email", user);

        // find the document of user
        Document find = betters_collect.find().first();

        // update replacement data
        replacement.put("tlf", find.get("tlf"));
        replacement.put("gender", find.get("gender"));
        replacement.put("birthday", find.get("birthday"));
        replacement.put("pwd", passwd);

        // replace found with replacement
        betters_collect.replaceOne(find, replacement);

        mongoClient.close();

        return true;
    }

    public boolean changeNumber(String user, String number) {
        // establish connection
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://rasbet:rasbet2022@rasbet.ov7kl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // get user_management database
        MongoDatabase user_management = mongoClient.getDatabase("user_management");

        // get betters collection
        MongoCollection<Document> betters_collect = user_management.getCollection("better");

        // create replacement
        Document replacement = new Document();
        replacement.put("email", user);

        // find the document of user
        Document find = betters_collect.find().first();

        // update replacement data
        replacement.put("pwd", find.get("pwd"));
        replacement.put("gender", find.get("gender"));
        replacement.put("birthday", find.get("birthday"));
        replacement.put("tlf", number);

        // replace found with replacement
        betters_collect.replaceOne(find, replacement);

        mongoClient.close();

        return true;
    }
}
