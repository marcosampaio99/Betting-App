package src.view;

import src.controller.Controller_Wallet;
import src.model.Enums;

import java.util.Scanner;

public class View_Wallet {
    private static final Scanner in = new Scanner(System.in);
    private Controller_Wallet cw = new Controller_Wallet();

    public void walletMenu(String user) {
        System.out.println("------------------------------------------");
        System.out.println("----------------- Wallet -----------------");
        System.out.println("------------------------------------------");
        System.out.println("O que deseja fazer?");
        System.out.println("[0] Sair da Aplicação");
        System.out.println("[1] Ver saldo");
        System.out.println("[2] Depositar");
        System.out.println("[3] Levantar");

        String s = in.nextLine();
        // Needs java 14
        switch (s) {
            case "0" -> System.exit(0);
            case "1" -> {
                balanceMenu(user);
            }
            case "2" -> {
                depositMenu(user);
            }
            case "3" -> {
                withdrawMenu(user);
            }
            default -> System.out.println("Input inválido. Tente novamente.");
        }
    }

    public void balanceMenu(String user) {
        cw.getBalance(user);
    }

    public void depositMenu(String user) {
        String sType, currency, sValue;

        System.out.println("Introduza o tipo de pagamento");
        sType = in.nextLine();
        Enums.PAYMENT_TYPE type = Enums.PAYMENT_TYPE.valueOf(sType);

        System.out.println("Introduza o tipo de pagamento (euro, dollar, pound, cardano)");
        currency = in.nextLine();

        System.out.println("Introduza o montante");
        sValue = in.nextLine();

        boolean success = cw.deposit(type, currency, Double.parseDouble(sValue), user);
        if (success)
            System.out.println("Depósito efetuado com sucesso");
        else
            System.out.println("!!!!!!Erro ao efetuar o depósito");

        balanceMenu(user);
    }

    public void withdrawMenu(String user) {
        String sType, currency, sValue;

        System.out.println("Introduza o tipo de pagamento");
        sType = in.nextLine();
        Enums.PAYMENT_TYPE type = Enums.PAYMENT_TYPE.valueOf(sType);

        System.out.println("Introduza o tipo de pagamento (euro, dollar, pound, cardano)");
        currency = in.nextLine();

        System.out.println("Introduza o montante");
        sValue = in.nextLine();

        boolean success = cw.withdraw(type, currency, Double.parseDouble(sValue), user);
        if (success)
            System.out.println("Levantamento efetuado com sucesso");
        else
            System.out.println("Erro ao efetuar o levantamento");
        balanceMenu(user);
    }
}
