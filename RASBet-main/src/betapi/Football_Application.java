package src.betapi;

import src.betapi.Football.manager.JfdataManager;
import src.betapi.Football.model.competition.CompetitionList;
import src.betapi.Football.config.ConfigToken;

// test class
public class Football_Application {
  public static void main(String[] args) {
        JfdataManager jfdataManager = new JfdataManager(ConfigToken.TOKEN);
        CompetitionList actual = jfdataManager.getAllCompetitions();

        System.out.println(actual);
    }
}
