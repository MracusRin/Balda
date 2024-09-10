package org.mracus.corp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private final int MIN_WORD_LENGTH = 6;
    private final int MAX_WORD_LENGTH = 7;
    private final String pathToDictionary = "src/main/resources/ru_dictionary.txt";
    private List<String> dictionary = new ArrayList<>();

    public Dictionary() {
        readDictionary();
    }

    public String getWord() {
        int wordRndNum = new Random().nextInt(dictionary.size());
        return dictionary.get(wordRndNum).toUpperCase();
    }

    private void readDictionary() {
        Path file = Path.of(pathToDictionary);
        try {
            dictionary = Files.readAllLines(file).stream()
                    .filter(s -> s.length() >= MIN_WORD_LENGTH && s.length() < MAX_WORD_LENGTH).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
