package net.noyark.www.utils;


import jline.console.ConsoleReader;
import java.io.IOException;


public class Message {
    private static ConsoleReader lineReader;


    static {
        try{
            lineReader = new ConsoleReader();
            lineReader.addCompleter(new SimpleLine());
        }catch (IOException e){

        }
    }


    public static void info(String msg){

        System.out.println("[INFO ]"+msg);
    }

    public static void error(String msg){
        System.out.println("[ERROR ]"+msg);
    }

    public static String cmd(){
        try{
            return lineReader.readLine(">");
        }catch (IOException e){
            throw new RuntimeException("输入异常",e);
        }
    }
}
