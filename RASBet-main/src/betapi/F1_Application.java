package src.betapi;

import java.io.IOException;

import src.betapi.F1.Ergast;

/**
 * Date: 08.03.17
 *
 * @author olerom
 */
public class F1_Application {
    
    public static void main(String[] args) {
        new F1_Application().run();
    }

    private void run() {
        Ergast ergast = new Ergast(2021, Ergast.DEFAULT_LIMIT, Ergast.DEFAULT_OFFSET);

        try {
            //ergast.getCircuits().forEach(System.out::println);
            ergast.getDrivers().forEach(System.out::println);

            //ergast.getConstructors().forEach(System.out::println);
            //ergast.getSeasons().forEach(System.out::println);
            //ergast.getSchedules().forEach(System.out::println);

            //ergast.getRaceResults(2).forEach(System.out::println);
            //ergast.getQualificationResults(2).forEach(System.out::println);

            //ergast.getDriverStandings(Ergast.NO_ROUND).forEach(System.out::println);
            //ergast.getConstructorStandings(Ergast.NO_ROUND).forEach(System.out::println);

            //ergast.getFinishingstatuses(Ergast.NO_ROUND).forEach(System.out::println);
            //ergast.getLapTimes(11).forEach(System.out::println);

            //ergast.getRacePitStops(11).forEach(System.out::println);

            ergast.getRaceResults(Ergast.NO_ROUND).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
