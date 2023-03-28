package src.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import src.model.Enums;
import src.model.Wallet;

public class Controller_Wallet {
    private final Wallet w = new Wallet();

    public void getBalance(String user) {
        // establish connection
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://rasbet:rasbet2022@rasbet.ov7kl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // get user_management database
        MongoDatabase user_wallets = mongoClient.getDatabase("user_wallets");

        // get betters collection
        MongoCollection<Document> wallets_collect = user_wallets.getCollection("wallets");

        // create a document to find
        Document find = new Document();
        find.put("user", user);

        Document doc = wallets_collect.find(find).first();
        System.out.println("EURO: " + doc.get("euro"));
        System.out.println("DOLLARS: " + doc.get("dollar"));
        System.out.println("LIBRAS: " + doc.get("pound"));
        System.out.println("CARDANO: " + doc.get("cardano"));

        mongoClient.close();
    }

    public boolean deposit(Enums.PAYMENT_TYPE type, String currency, double value, String user) {
        // establish connection
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://rasbet:rasbet2022@rasbet.ov7kl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // get user_management database
        MongoDatabase user_wallets = mongoClient.getDatabase("user_wallets");

        // get betters collection
        MongoCollection<Document> wallets_collect = user_wallets.getCollection("wallets");

        // create a document to find
        Document find = new Document();
        find.put("user", user);

        Document doc = wallets_collect.find(find).first();
        doc.replace(currency, (double) doc.get(currency) + value);

        // find the value to update and value to update
        wallets_collect.findOneAndReplace(wallets_collect.find(find).first(), doc);

        mongoClient.close();

        return true;
    }

    public boolean withdraw(Enums.PAYMENT_TYPE type, String currency, double value, String user) {
        // establish connection
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://rasbet:rasbet2022@rasbet.ov7kl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        // get user_management database
        MongoDatabase user_wallets = mongoClient.getDatabase("user_wallets");

        // get betters collection
        MongoCollection<Document> wallets_collect = user_wallets.getCollection("wallets");

        // create a document to find
        Document find = new Document();
        find.put("user", user);

        Document doc = wallets_collect.find(find).first();
        if ((double) doc.get(currency) - value >= 0) {
            doc.replace(currency, (double) doc.get(currency) - value);
            wallets_collect.findOneAndReplace(wallets_collect.find(find).first(), doc);
        } else {
            System.out.println("Não existe saldo na carteira!!");
        }

        // find the value to update and value to update

        mongoClient.close();

        return true;
    }
}
