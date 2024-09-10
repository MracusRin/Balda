package org.mracus.corp;

import java.util.Scanner;

public class Game {
    private final Board board = new Board();
    private final StringBuilder shadowWord;
    private final StringBuilder errorLetters = new StringBuilder();
    private final String word;
    private char currentLetter = ' ';
    private int errorCount = 0;

    private Game() {
        this.word = new Dictionary().getWord();
        shadowWord = new StringBuilder("_".repeat(word.length()));
    }

    private void nextLetter() {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (input.length() != 1) {
            System.out.print("Введите одну букву: ");
            input = scanner.next();
        }
        this.currentLetter = input.toUpperCase().charAt(0);
    }

    private void checkLetter() {
        boolean hasError = true;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == currentLetter) {
                shadowWord.replace(i, i + 1, String.valueOf(currentLetter));
                hasError = false;
            }
        }
        if (hasError) {
            errorAdder(currentLetter);
            errorCount++;
        }

    }

    private void errorAdder(char c) {
        if (errorLetters.isEmpty()) {
            errorLetters.append(c);
        } else {
            errorLetters.append(", ").append(c);
        }
    }

    private boolean isFullWord() {
        return shadowWord.toString().replaceAll("[А-Я]", "").isEmpty();
    }

    private void mainLoop() {
        // TODO: не забудь убрать подсказку
        System.out.println(word);
        while (true) {
            board.state(shadowWord, errorLetters, errorCount, currentLetter);
            if (errorCount == 6) {
                System.out.println("Ты проиграл. Конец игры");
                break;
            }
            if (isFullWord()) {
                System.out.println("Ты победил. Конец игры");
                break;
            }
            nextLetter();
            checkLetter();
        }
    }

    public static void start() {
        Game game = new Game();
        game.mainLoop();
    }
}
