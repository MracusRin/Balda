package org.mracus.corp.balda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class GameDictionary {
    private final Path file = Path.of("src/main/resources/ru_dictionary.txt");

    public String getWord(int wordLength) {
        List<String> dictionary = readGameDictionary(wordLength);
        int wordRndNum = new Random().nextInt(dictionary.size());
        return dictionary.get(wordRndNum).toUpperCase();
    }

    private List<String> readGameDictionary(int wordLength) {
        try {
            return Files.readAllLines(file).stream().filter(s -> s.length() == wordLength).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
