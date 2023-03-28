package src.view;

import java.util.Map;
import java.util.Scanner;

public class View_Home {
    private static final Scanner in = new Scanner(System.in);
    private final View_SignIn vs = new View_SignIn();
    private final View_Admin va = new View_Admin();
    private final View_Better vb = new View_Better();
    private Map.Entry<String, String> user_and_type;

    public void home() {
        System.out.println("------------------------------------------");
        System.out.println("-----TRABALHO PRÁTICO R.A.S 2021/2022-----");
        System.out.println("------------------RASBET------------------");
        System.out.println("[0] Sair da Aplicação");
        System.out.println("[1] Menu sign in");

        boolean end = false;
        while (!end) {
            end = true;
            String s = in.nextLine();
            switch (s) {
                case "0" -> System.exit(0);
                case "1" -> {
                    this.user_and_type = vs.signinMenu();
                }
                default -> end = false;
            }
        }
        if (this.user_and_type != null) {
            switch (this.user_and_type.getValue()) {
                case "Admin" -> va.adminMenu();
                case "Better" -> vb.betterMenu(this.user_and_type.getKey());
                default -> System.out.println("Err");
            }
        } else {
            this.home();
        }
    }
}