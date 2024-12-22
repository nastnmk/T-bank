package Hangman;

import java.util.ArrayList;

public class DefaultGamePrinter implements GamePrinter {
    @Override
    public void win(String answer) {
        System.out.println("Вы выиграли! Загаданное слово: "+ answer );
    }

    @Override
    public void loser(String answer) {
        System.out.println("Вы проиграли! Загаданное слово: "+ answer );
    }

    @Override
    public void success(String hiddenAnswer, char letter, ArrayList<Character> guessedLetters) {
        System.out.println("Попадание!");
        System.out.println("Слово "+hiddenAnswer );
        System.out.println();
        guessedLetters.add(letter);
    }

    @Override
    public void lose(String hiddenAnswer, int userAttempts, char letter, ArrayList<Character> notGuessedLetters) {
        System.out.println("Не та буква(");
        System.out.println("Слово "+hiddenAnswer );
        System.out.println("Осталось попыток: "+ userAttempts);
        notGuessedLetters.add(letter);
        System.out.println();
    }
}
