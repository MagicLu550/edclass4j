package net.noyark.www.utils;

import jline.console.completer.Completer;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleLine implements Completer {

    public int complete(String buffer, int index, List<CharSequence> list) {
        Set<String> entries =
                JarEncode.commandBaseMap
                        .keySet()
                        .stream()
                        .filter(e->e.startsWith(buffer)).collect(Collectors.toSet());
        list.addAll(entries);
        return index-buffer.length();
    }
}
