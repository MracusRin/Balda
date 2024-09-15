package org.mracus.corp.balda;

public class Board {
    public void printState(StringBuilder shadowWord, StringBuilder errorLetters, int errorCount, char currentLetter) {
        drawBoard(errorCount);
        System.out.println("Слово: " + shadowWord);
        System.out.printf("Ошибки (%s): %s\n", errorCount, errorLetters);
        if (currentLetter != 0) {
            System.out.println("Буква: " + currentLetter);
        }
    }

    private void drawBoard(int count) {
        String[] pattern = {"(_)", "|", "/", "\\", "/", "\\"};
        String[] placeHolder = {" ", " ", " ", " ", " ", " "};
        for (int i = 0; i < count; i++) {
            placeHolder[i] = pattern[i];
        }
        System.out.printf("""
                     _________
                     |       |
                     |      %s
                     |      %s%s%s
                     |      %s %s
                     |
                   __|__
                __/_____\\________
                """, placeHolder[0], placeHolder[2], placeHolder[1], placeHolder[3], placeHolder[4], placeHolder[5]);
    }
}
