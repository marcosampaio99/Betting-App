package src.view;

import src.controller.Controller_Bet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View_Bet {
    private static final Scanner in = new Scanner(System.in);
    private final Controller_Bet cb = new Controller_Bet();

    public void betMenu(String user) {
        System.out.println("------------------------------------------");
        System.out.println("----------------- Aposta -----------------");
        System.out.println("------------------------------------------");
        System.out.println("O que deseja fazer?");
        System.out.println("[0] Sair da Aplicação");
        System.out.println("[1] Ver listagem de apostas de F1");
        System.out.println("[2] Ver listagem de apostas de futebol");

        String s = in.nextLine();
        // Needs java 14
        switch (s) {
            case "0" -> System.exit(0);
            case "1" -> listBetsF1(user);
            case "2" -> listBetsFB(user);
            default -> System.out.println("Input inválido. Tente novamente.");
        }
    }

    private void listBetsFB(String user) {
        List<String> bets = cb.listBetsFB();
        for (String bet : bets) {
            System.out.println(bet);
        }
        bet(user, 2);
    }

    private void listBetsF1(String user) {
        List<String> bets = cb.listBetsF1();
        for (String bet : bets) {
            System.out.println(bet);
        }
        bet(user, 1);
    }

    // 0 -> fb
    // 1 -> f1
    private void bet(String user, int type) {
        boolean end = false;
        List<String[]> l = new ArrayList<>();
        System.out.println("Faça a sua aposta: [id aposta] [resultado] [valor]");
        System.out.println("Caso não queira apostar mais não escreva nada");
        while (!end) {
            String s = in.nextLine();
            if (s.isEmpty())
                end = true;
            else {
                String[] ss = s.split(" ");
                boolean exists = cb.hasOpenBet(ss[0], type);
                if (exists) {
                    l.add(ss);
                    System.out.println("Aposta adicionada");
                } else
                    System.out.println("Aposta não existente ou fechada");
            }
        }
        double value = 0;
        for (String[] s : l) {
            value += Double.parseDouble(s[2]);
        }
        System.out.println(value);
        System.out.println("Como deseja pagar? [euro] | [dollar] | [pound] | [cardano]");
        String paymentMethod = in.nextLine();
        boolean success = cb.pay(user, paymentMethod, value);
        if(success)
            System.out.println("Pago com sucesso");
        else
            System.out.println("Erro no pagamento");
    }
}
