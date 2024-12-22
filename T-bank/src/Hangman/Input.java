package Hangman;

import java.util.Scanner;

public class Input {
    private final Scanner scanner;
    public Input()  {
        scanner = new Scanner(System.in);
    }
    public char read(){
        System.out.println("Введите букву:");
        var letter = scanner.next().charAt(0);
        if (!Character.isLetter(letter)) {
            System.out.println("Неправильный ввод.");
            System.out.println();
            return read();
        }
        return letter;
    }
}
