package org.mracus.corp;

public class Board {
    public void state(int errorCount, String word, String errorLetter, String currentLetter) {
        drawBoard(errorCount);
        System.out.println("Слово: " + word);
        System.out.printf("Ошибки (%s): %s\n", errorCount, errorLetter);
        System.out.println("Буква: " + currentLetter);
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
