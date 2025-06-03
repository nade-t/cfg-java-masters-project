package com.cfgmasters.project.model;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class Menu {
    static Scanner keyboard = new Scanner(System.in);
    static String input;
    static boolean quit = false;

    public static void initaliseMenu() {
        log.debug("Initialising Menu");
        System.out.println("Bookstore operations");
        do {
            mainMenu();

        } while (!quit);
    }

    public static void mainMenu() {
        System.out.println("1. Check stock");
        System.out.println("2. Retail operations");
        System.out.println("Q. Exit");
        input = keyboard.nextLine();

        switch (input) {
            case "1":
                inventoryMenu();
                break;
            case "2":
                System.out.println("Retail operations");
                break;
            case "Q":
                exitSystem();
                break;
            default:
                System.out.println("Invalid input");
                input = keyboard.nextLine();

        }
    }


    public static void inventoryMenu() {
        log.info("Inventory accessed");
        System.out.println("Getting stock"); // replace with API call
    }

    public static void retailOperationsMenu() {
        log.info("Retail operations");
        // call methods from sales model to sell and refund books
    }

    public static void exitSystem() {
        log.debug("System shut down");
        keyboard.close();
        quit = true;
    }
}


