package src.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;
import src.model.Bet;
import src.model.Enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Controller_Bet {
    // Ideia é sempre q se adicionar uma aposta vem para esta lista
    private List<Bet> listBetsF1 = new ArrayList<>();
    private List<Bet> listBetsFB = new ArrayList<>();

    public MongoClient createMongo() {
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://rasbet:rasbet2022@rasbet.ov7kl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(settings);
    }

    // f1_db or football_db
    private void updateList(String database) {
        MongoClient mongoClient = createMongo();
        MongoDatabase db = mongoClient.getDatabase(database);
        MongoCollection<Document> odds = db.getCollection("odds");

        // Create iterator for odds
        try (MongoCursor<Document> cursor = odds.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String betID = (String) doc.get("bet_id");
                Enums.BET_KIND betKind = Enums.BET_KIND.valueOf((String) doc.get("bet_kind"));
                Enums.BET_STATUS betStatus = Enums.BET_STATUS.valueOf((String) doc.get("bet_status"));
                String place = (String) doc.get("competition_place");
                Enums.SEASON_TYPE season = Enums.SEASON_TYPE.valueOf((String) doc.get("season"));
                String year = (String) doc.get("year");
                Map<String, Float> matchOdds = (Map<String, Float>) doc.get("odds", Map.class);

                Bet newBet = new Bet(betID, matchOdds, betKind, betStatus, place, season, year);
                switch (database) {
                    case "f1_db" -> listBetsF1.add(newBet);
                    case "football_db" -> listBetsFB.add(newBet);
                }
            }
        }
        mongoClient.close();
    }

    public List<String> listBetsF1() {
        if (listBetsF1.isEmpty())
            updateList("f1_db");
        List<String> r = new ArrayList<>();
        for (Bet bet : listBetsF1) {
            r.add(bet.toString());
        }
        return r;
    }

    public List<String> listBetsFB() {
        if (listBetsFB.isEmpty())
            updateList("football");
        List<String> r = new ArrayList<>();
        for (Bet bet : listBetsFB) {
            r.add(bet.toString());
        }
        return r;
    }

    public boolean hasOpenBet(String bet, int type) {
        switch (type) {
            case 0 -> {
                for (Bet b : listBetsFB) {
                    if (b.getBet_id().equals(bet) && b.getStatus() == Enums.BET_STATUS.OPEN)
                        return true;
                }
            }
            case 1 -> {
                for (Bet b : listBetsF1) {
                    if (b.getBet_id().equals(bet) && b.getStatus() == Enums.BET_STATUS.OPEN)
                        return true;
                }
            }
        }
        return false;
    }

    public boolean pay(String user, String paymentMethod, double value) {
        boolean success = false;
        MongoClient mongoClient = createMongo();
        MongoDatabase db = mongoClient.getDatabase("user_wallets");
        MongoCollection<Document> wallet = db.getCollection("wallets");
        Document find = new Document();
        find.put("user", user);
        Document doc = wallet.find(find).first();
        Double balance = (Double) doc.get(paymentMethod.toLowerCase());
        if(balance >= value) {
            doc.put(paymentMethod.toLowerCase(), balance - value);
            wallet.findOneAndReplace(find, doc);
            success = true;
        }
        mongoClient.close();
        return success;
    }
}
