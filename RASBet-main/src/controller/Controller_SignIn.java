package src.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.AbstractMap;
import java.util.Map;

public class Controller_SignIn {
    //
    public Map.Entry<String, String> login(String email, String pwd) {
        // establish connection
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://rasbet:rasbet2022@rasbet.ov7kl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // get user_management database
        MongoDatabase user_management = mongoClient.getDatabase("user_management");

        // get admin collection
        MongoCollection<Document> admins_collect = user_management.getCollection("admin");
        // get betters collection
        MongoCollection<Document> betters_collect = user_management.getCollection("better");

        // create a document to find
        Document find = new Document();
        find.put("email", email);
        find.put("pwd", pwd);

        try {
            // look if exists on admins collection
            Document doc = admins_collect.find(find).first();
            if (doc != null) {
                mongoClient.close();
                return new AbstractMap.SimpleEntry<>(email, "Admin");
            } else {
                // look if exists on better collection
                Document doc1 = betters_collect.find(find).first();
                if (doc1 != null) {
                    mongoClient.close();
                    return new AbstractMap.SimpleEntry<>(email, "Better");
                }
            }
        } catch (Exception e) {
            // exception handler
        }
        return null;
    }

    public void registo(String user, String pass, String tlf, String gen, String birth) {
        // establish connection
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://rasbet:rasbet2022@rasbet.ov7kl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // get user_management database
        MongoDatabase user_management = mongoClient.getDatabase("user_management");
        MongoDatabase user_wallets = mongoClient.getDatabase("user_wallets");

        // get betters collection
        MongoCollection<Document> betters_collect = user_management.getCollection("better");
        MongoCollection<Document> wallets_collect = user_wallets.getCollection("wallets");

        // create a document to find
        Document find = new Document();
        find.put("email", user);

        // check if document exists
        Document doc = betters_collect.find(find).first();

        if (doc == null) {
            find.put("pwd", pass);
            find.put("tlf", tlf);
            find.put("gender", gen);
            find.put("birthday", birth);
            betters_collect.insertOne(find);

            //// create a document to insert to wallets
            Document wallet = new Document();
            wallet.put("user", user);
            wallet.put("euro", 0.0);
            wallet.put("dollar", 0.0);
            wallet.put("pound", 0.0);
            wallet.put("cardano", 0.0);

            wallets_collect.insertOne(wallet);

        } else {
            System.out.println("O USER JÁ SE ENCONTRA REGISTADO!");
        }

        mongoClient.close();
    }
}
