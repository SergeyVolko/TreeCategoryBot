package org.example.treecategorybot.bot.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class AddElementCommandTest {

    @Test
    public void template() {
        String text = "/addElement <родительский элемент> <дочерний элемент>";
        String regex = "/addElement\\s+<[^<>]+>(\\s+<[^<>]+>)?";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()) {
            System.out.println("Строка соответствует формату.");
        } else {
            System.out.println("Строка не соответствует формату.");
        }
        System.out.println(text);
        String[] elements = text.substring(text.indexOf("<") + 1, text.length() - 1)
                .split("> <");
        System.out.println(Arrays.toString(elements));

    }

}