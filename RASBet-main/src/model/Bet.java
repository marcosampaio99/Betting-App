package src.model;

import java.util.Map;

public class Bet implements Enums {
    // o id de uma bet
    private String bet_id;
    // todas as odds e possíveis outcomes de uma aposta
    private Map<String, Float> match_odds;
    // o tipo de aposta
    private BET_KIND kind;
    // o estado da aposta
    private BET_STATUS status;
    // qual a competição associada à odd
    private Competition competition;

    public Bet(String bet_id, Map<String, Float> match_odds, BET_KIND kind, BET_STATUS status, String place, SEASON_TYPE type, String year) {
        this.bet_id = bet_id;
        this.match_odds = match_odds;
        this.kind = kind;
        this.status = status;
        this.competition = new Competition(place, type, year);
    }

    @Override
    public String toString() {
        return "Aposta: " + bet_id +
                "\n\tOdds: " + match_odds.toString() +
                "\n\tTipo: " + kind +
                "\n\tEstado: " + status;
    }

    public String getBet_id() {
        return bet_id;
    }

    public BET_STATUS getStatus() {
        return status;
    }
}
