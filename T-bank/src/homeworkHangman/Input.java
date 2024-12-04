package homeworkHangman;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    private Scanner scanner;
    char letter;
    public Input() {
        scanner = new Scanner(System.in);
    }
    public void write(){
        System.out.println("Введите букву:");
        letter = scanner.next().charAt(0);
        if (!Character.isLetter(letter)) {
            System.out.println("Неправильный ввод.");
            System.out.println();
            write();
        }
    }
    public char getLetter(){
        return letter;
    }

    public boolean ifWasUsed(char input_letter, ArrayList<Character> guessedLetters, ArrayList<Character> not_guessedLetters){
        if (guessedLetters.contains(input_letter) || not_guessedLetters.contains((input_letter))){
            System.out.println("Ты уже вводил эту букву. Введи еще раз!");
            write();
            return true;
        }
        return false;
    }
}
