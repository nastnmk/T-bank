package homeworkHangman;

import java.util.ArrayList;

public interface Result {
    void Win(String answer);
    void Loser(String answer);
    void Success(String hiddenAnswer, Input input, ArrayList<Character> guessedLetters);
    void Lose(String hiddenAnswer, int userAttempts, Input input, ArrayList<Character> not_guessedLetters);
}
