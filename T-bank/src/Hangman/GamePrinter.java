package Hangman;

import java.util.ArrayList;

public interface GamePrinter {
    void win(String answer);
    void loser(String answer);
    void success(String hiddenAnswer, char letter, ArrayList<Character> guessedLetters);
    void lose(String hiddenAnswer, int userAttempts, char letter, ArrayList<Character> notGuessedLetters);
}
