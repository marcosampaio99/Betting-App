package src.model;

import java.util.Map;

import javax.swing.text.PasswordView;

import src.model.Observer_Pattern.Observer;
import src.model.Observer_Pattern.Subject;

public class Better extends User implements Observer {
    private String nif;
    private String id;
    private Wallet wallet;

    public Better() {
        super();
    }

    public Better(String name,String password,String nif,String id, Wallet wallet){
        super(name,password);
        this.nif=nif;
        this.id=id;
        this.wallet=wallet;

    }
    /** Construtor clone */
    public Better(Better better){
        super(better.getUsername(),better.getPass());  
    }

    public String getNif(){return nif;}
    public String getID(){return id;}
    public Wallet getWallet(){return wallet;}

    public void setNif(String nif){this.nif=nif;}
    public void setID(String id){this.id=id;}
    public void setWallet(Wallet wallet){this.wallet=wallet;}


    @Override
    public void update(Subject.Event event, Map<String, Integer> team_scores) {
        if (event == Subject.Event.GOAL) {
            // goal
            System.out.println("GOAL! Scoring: ");
            for (Map.Entry<String, Integer> entry : team_scores.entrySet()) {
                System.out.print(entry.getKey() + " - " + entry.getValue() + "\n");
            }
        } else {
            if (event == Subject.Event.START) {
                // start game
                System.out.println("START! Game between: ");
                for (Map.Entry<String, Integer> entry : team_scores.entrySet()) {
                    System.out.print(entry.getKey() + " ");
                }
                System.out.print("\n");
            }
            else {
                if (event == Subject.Event.HALF) {
                    // half game
                    System.out.println("Half-Time! Game between: ");
                    for (Map.Entry<String, Integer> entry : team_scores.entrySet()) {
                        System.out.print(entry.getKey() + " ");
                    }
                    System.out.print("\n");
                }
                else {
                    // end of game
                    System.out.println("GAME ENDED! Scoring: ");
                    for (Map.Entry<String, Integer> entry : team_scores.entrySet()) {
                        System.out.print(entry.getKey() + " - " + entry.getValue() + "\n");
                    }
                }
            }

        }
    }
    
}
