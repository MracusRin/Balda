package org.mracus.corp.balda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDictionary {
    private final Path file = Path.of("src/main/resources/ru_dictionary.txt");
    private List<String> dictionary = new ArrayList<>();

    public GameDictionary(int minWordLength, int maxWordLength) {
        readGameDictionary(minWordLength, maxWordLength);
    }

    public String getWord() {
        int wordRndNum = new Random().nextInt(dictionary.size());
        return dictionary.get(wordRndNum).toUpperCase();
    }

    private void readGameDictionary(int minWordLength, int maxWordLength) {
        try {
            dictionary = Files.readAllLines(file).stream()
                    .filter(s -> s.length() >= minWordLength && s.length() < maxWordLength).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
