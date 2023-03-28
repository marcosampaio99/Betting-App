package src.view;

import src.controller.Controller_Better;

import java.util.Scanner;

public class View_Better {
    private static final Scanner in = new Scanner(System.in);
    private final Controller_Better cb = new Controller_Better();
    private final View_Wallet w = new View_Wallet();
    private final View_Bet vb = new View_Bet();
    private String user;

    public void betterMenu(String user) {
        this.user = user;
        while (true) {
            System.out.println("------------------------------------------");
            System.out.println("--------------- Utilizador ---------------");
            System.out.println("------------------------------------------");
            System.out.println("O que deseja fazer?");
            System.out.println("[0] Sair da Aplicação");
            System.out.println("[1] Alterar dados");
            System.out.println("[2] Histórico de apostas");
            System.out.println("[3] Wallet");
            System.out.println("[4] Apostas");
            String s = in.nextLine();
            switch (s) {
                case "0" -> System.exit(0);
                case "1" -> betterEdit();
                case "2" -> betterHistory();
                case "3" -> w.walletMenu(user);
                case "4" -> vb.betMenu(user);
            }
        }

    }

    public void betterEdit() {
        System.out.println("O que deseja alterar?");
        System.out.println("[0] Nada");
        System.out.println("[1] Password");
        System.out.println("[2] Telefone");
        boolean end = false;
        while (!end) {
            end = true;
            String s = in.nextLine();
            switch (s) {
                case "1" -> {
                    System.out.println("Insira aqui a sua password:");
                    String passwd = in.nextLine();
                    while (passwd.equals("")) {
                        System.out.println("A password não pode ser vazia.");
                        passwd = in.nextLine();
                    }

                    if (cb.changePassword(this.user, passwd))
                        System.out.println("Password alterada com sucesso!");
                }
                case "2" -> {
                    System.out.println("Insira aqui o seu número de telefone:");
                    String telefone = in.nextLine();

                    if (cb.changeNumber(this.user, telefone))
                        System.out.println("Telefone alterado com sucesso!");
                }
                default -> end = false;
            }
        }
        this.betterMenu(user);
    }

    public void betterHistory() {
        System.out.println("------------------------------------------");
        System.out.println("----------- Histórico Apostas ------------");
        System.out.println("------------------------------------------");
        cb.betterHistory(user);

        this.betterMenu(user);
    }
}
