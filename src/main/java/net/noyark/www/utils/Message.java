package net.noyark.www.utils;

import java.util.Scanner;

public class Message {
    static {
        scanner = new Scanner(System.in);
    }

    private static Scanner scanner;

    public static void info(String msg){
        System.out.println("[INFO ]"+msg);
    }

    public static void error(String msg){
        System.out.println("[ERROR ]"+msg);
    }

    public static String input(){
        return scanner.nextLine();
    }

    public static String cmd(){
        System.out.print(">");
        return scanner.next();
    }
}
