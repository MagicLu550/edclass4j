package net.noyark.www.utils;


import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;


public class Message {
    private static LineReader lineReader;
    static {
        try{
            Terminal terminal = TerminalBuilder.builder()
                    .system(true)
                    .build();
            lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .build();
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
        return lineReader.readLine(">");
    }
}
