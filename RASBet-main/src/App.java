package src;

import src.view.*;

import java.util.logging.Logger;
import java.util.logging.Level;

public class App {
    public static void main(String[] args) {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        new View_Home().home();
    }
}
