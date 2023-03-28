package src.model;

public class Competition implements Enums{
   
    // sítio onde ocorre a competição
    private String place;
    // qual o tipo da season da competição
    private SEASON_TYPE season_type;
    // o ano da competição
    private String year;

    public Competition(String place, SEASON_TYPE season_type, String year) {
        this.place = place;
        this.season_type = season_type;
        this.year = year;
    }
}
