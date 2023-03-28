package src.view;

import src.controller.Controller_SignIn;

import java.util.Map;
import java.util.Scanner;

public class View_SignIn {
    private static final Scanner in = new Scanner(System.in);
    private final Controller_SignIn cs = new Controller_SignIn();

    public Map.Entry<String, String> signinMenu() {
        System.out.println("------------------------------------------");
        System.out.println("----------------- SignIn -----------------");
        System.out.println("------------------------------------------");
        System.out.println("O que deseja fazer?");
        System.out.println("[0] Sair da Aplicação");
        System.out.println("[1] Login");
        System.out.println("[2] Registar");

        String s = in.nextLine();
        // Needs java 14
        switch (s) {
            case "0" -> System.exit(0);
            case "1" -> {
                return login();
            }
            case "2" -> registo();
            default -> System.out.println("Input inválido. Tente novamente.");
        }

        return null;
    }

    public Map.Entry<String, String> login() {
        System.out.println("Insira aqui o seu email:");
        String e = in.nextLine();
        System.out.println("Insira aqui a sua password:");
        String p = in.nextLine();

        //
        return cs.login(e, p);
    }

    public void registo() {
        System.out.println("Insira aqui o seu email:");
        String email = in.nextLine();
        while (email.equals("")) {
            System.out.println("O email não pode ser vazio.");
            email = in.nextLine();
        }

        System.out.println("Insira aqui a sua password:");
        String passwd = in.nextLine();
        while (passwd.equals("")) {
            System.out.println("A password não pode ser vazia.");
            passwd = in.nextLine();
        }

        System.out.println("Insira aqui o seu número de telefone:");
        String telefone = in.nextLine();

        System.out.println("Insira aqui o seu género:");
        String genero = in.nextLine();

        System.out.println("Insira aqui a sua data de nascimento (DD-MM-AA):");
        String dataNasc = in.nextLine();

        cs.registo(email, passwd, telefone, genero, dataNasc);
    }
}
