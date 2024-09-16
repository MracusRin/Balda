package org.mracus.corp.balda;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final GameDictionary gameDictionary;
    private final String[] args;
    private StringBuilder errorLetters;
    private StringBuilder shadowWord;
    private String currentWord;
    private char currentLetter;
    private int errorCount;
    private int wordLength;

    private Game(Board board, GameDictionary gameDictionary, String... args) {
        this.board = board;
        this.gameDictionary = gameDictionary;
        this.args = args;
    }

    private void init() {
        difficulty();
        this.currentWord = gameDictionary.getWord(wordLength);
        this.shadowWord = new StringBuilder("_".repeat(currentWord.length()));
        this.errorLetters = new StringBuilder();
        this.errorCount = 0;
        this.currentLetter = 0;
    }

    private void difficulty() {
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        while (result < 1 || result > 10) {
            System.out.print("Введите уровень сложности от 1 до 10: ");
            try {
                result = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                result = 0;
            }
        }
        wordLength = result + 4;
    }

    private void nextLetter() {
        String input = "";
        Scanner scanner = new Scanner(System.in);
        while (input.length() != 1) {
            System.out.print("Введите одну букву: ");
            input = scanner.nextLine();
        }
        this.currentLetter = input.toUpperCase().charAt(0);
    }

    private void checkLetter() {
        boolean hasError = true;
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.charAt(i) == currentLetter) {
                shadowWord.replace(i, i + 1, String.valueOf(currentLetter));
                hasError = false;
            }
        }
        if (hasError) {
            errorAdder(currentLetter);
        }

    }

    private void errorAdder(char c) {
        errorCount++;
        if (errorLetters.isEmpty()) {
            errorLetters.append(c);
        } else {
            errorLetters.append(", ").append(c);
        }
    }

    private boolean isFullWord() {
        return shadowWord.toString().replaceAll("[А-Я]", "").isEmpty();
    }

    private void gameLoop() {
        init();
        debugLog();
        while (true) {
            board.printState(shadowWord, errorLetters, errorCount, currentLetter);
            if (errorCount == 6) {
                System.out.println("Ты проиграл. Было загадано слово: " + currentWord);
                break;
            }
            if (isFullWord()) {
                System.out.println("Ты победил. Ты отгадал слово: " + currentWord);
                break;
            }
            nextLetter();
            checkLetter();
        }
    }

    private void beginGame() {
        Scanner scanner = new Scanner(System.in);
        String result;
        while (true) {
            System.out.print("Игра в Балду. Начать новую игру? y/n: ");
            result = scanner.nextLine();
            if (result.equals("y") || result.equals("н")) {
                gameLoop();
            } else if (result.equals("n") || result.equals("т")) {
                break;
            }
        }
    }

    private void debugLog() {
        if (args.length != 0 && args[0].equals("debug")) {
            System.out.printf("[WARNING] debug mode on. Current word: %s\n", currentWord);
        }
    }

    public static void start(String... args) {
        Game game = new Game(new Board(), new GameDictionary(), args);
        game.beginGame();
    }
}
