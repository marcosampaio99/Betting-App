package src.model;

public interface Enums {
    public enum SPORTS_NAME {
        FORMULA_1,
        FOOTBALL
    }
    // enumerador para os tipos de apostas
    public enum SPORT_TYPE {
        INDIVIDUAL,
        COLLECTIVE
    }
    // qual o tipo de aposta a fazer
    public enum BET_KIND {
        BET_TEAM, // apostar numa equipa
        BET_PLAYER, // apostar numa individual
        BET_GOAL, // aposta de golos
        BET_POSITION // apostar na posição de uma competição
    }
    // estado da aposta
    public enum BET_STATUS {
        OPEN, // aberta
        SUSPENDED, // suspendida
        CLOSED // fechada
    }
    public enum BULLETIN_TYPE {
        SINGLE,
        MULTIPLE
    }
    // enumerar as diferentes estações do ano
    public enum SEASON_TYPE {
        WINTER, // sazonal
        SPRING, // sazonal
        SUMMER, // sazonal
        AUTUMN, // sazonal
        ANUAL, // anual
        SEMESTER // semestral
    }

    public enum PAYMENT_TYPE {
        VISA_CARD,
        MAESTRO_CARD,
        MBWAY,
        CRYPTO
    }

}
