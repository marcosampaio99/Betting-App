package src.view;

import src.controller.Controller_Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View_Admin {
    private static final Scanner in = new Scanner(System.in);
    private final Controller_Admin ca = new Controller_Admin();

    public void adminMenu(){
        System.out.println("------------------------------------------");
        System.out.println("------------- Administrador --------------");
        System.out.println("------------------------------------------");
        System.out.println("O que deseja fazer?");
        System.out.println("[0] Sair da Aplicação");
        System.out.println("[1] Alterar dados");
        System.out.println("[2] Histórico de apostas");
        System.out.println("[3] Gestão de utilizadores");
        System.out.println("[4] Gestão de apostas");

        while (true) {
            String s = in.nextLine();
            switch (s) {
                case "0" -> System.exit(0);
                case "1" -> adminEdit();
                case "2" -> history();
                case "3" -> manageUsers();
                case "4" -> manageBets();
            }
        }
    }


    public void adminEdit() {
        System.out.println("O que deseja alterar?");
        System.out.println("[0] Nada");
        System.out.println("[1] Password");
    }


    public void history() {
        System.out.println("------------------------------------------");
        System.out.println("----------- Histórico Apostas ------------");
        System.out.println("------------------------------------------");
        // Obter todas as apostas da db
        List<String> l = new ArrayList<>();
        for (String s : l) {
            System.out.println(s);
        }
    }


    public void manageUsers() {
        System.out.println("------------------------------------------");
        System.out.println("----------- Gerir Utilizadores -----------");
        System.out.println("------------------------------------------");

        // Banir ou suspender ou eliminar
    }


    public void manageBets() {

    }
}
