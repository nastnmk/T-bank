package homeworkHangman;

import java.util.ArrayList;

public class Results implements Result{
    @Override
    public void Win(String answer) {
        System.out.println("Вы выиграли! Загаданное слово: "+ answer );
    }

    @Override
    public void Loser(String answer) {
        System.out.println("Вы проиграли! Загаданное слово: "+ answer );
    }

    @Override
    public void Success(String hiddenAnswer, Input input, ArrayList<Character> guessedLetters) {
        System.out.println("Попадание!");
        System.out.println("Слово "+hiddenAnswer );
        System.out.println();
        guessedLetters.add(input.getLetter());
    }

    @Override
    public void Lose(String hiddenAnswer, int userAttempts, Input input, ArrayList<Character> not_guessedLetters) {
        System.out.println("Не та буква(");
        System.out.println("Слово "+hiddenAnswer );
        System.out.println("Осталось попыток: "+ userAttempts);
        not_guessedLetters.add(input.getLetter());
        System.out.println();
    }
}
