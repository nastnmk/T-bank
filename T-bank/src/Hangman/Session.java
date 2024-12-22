package Hangman;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Session{
    private Scanner scanner;
    private Dictionary dictionary;
    private String randomWord;
    private String answer; //сгенерированное слово
    private String hiddenAnswer; //маска для загаданного слова
    private DefaultGamePrinter results;
    private Input input;

    private final int maxAttempts;
    private int userAttempts;

    private ArrayList<Character> notGuessedLetters;
    private ArrayList<Character> guessedLetters;

    private String continueGame;


    public Session() {
        this.dictionary = new Dictionary();
        this.randomWord = dictionary.generateWord();
        this.answer = randomWord;
        this.maxAttempts = 5;
        this.userAttempts = maxAttempts;
        this.hiddenAnswer = "*".repeat(answer.length());
        this.notGuessedLetters = new ArrayList<>();
        this.guessedLetters = new ArrayList<>();
        this.results = new DefaultGamePrinter();
        this.input = new Input();
        this.scanner = new Scanner(System.in);
    }

    private boolean tryGuessLetter(char letter){ //проверяет, если буква есть в слове и заменяет, если да
        boolean isCorrect = false;
        for (int i = 0; i < hiddenAnswer.length(); i++){
            if (answer.charAt(i) == letter){
                hiddenAnswer = hiddenAnswer.substring(0, i) + letter + hiddenAnswer.substring(i + 1);
                isCorrect = true;
            }
        }
        if (isCorrect){
            results.success(hiddenAnswer, letter, guessedLetters);
        }
        else {
            userAttempts--;
            results.lose(hiddenAnswer, userAttempts, letter, notGuessedLetters);
        }
        return isCorrect;
    }

    public boolean isWordGuessed(){
        return hiddenAnswer.equals(answer);
    }

    public void play(){
        try {
            while (userAttempts != 0) {
                var letter = input.read();// записывает букву и проверяет на корректность
                while (isCorrectLetter(letter)) {
                    System.out.println("Ты уже вводил эту букву. Введи еще раз!");
                    letter = input.read();
                }
                tryGuessLetter(letter);
                if (isWordGuessed()) {
                    results.win(answer);
                    end();
                    return;
                }
            }
            results.loser(answer);
            end();
        } catch (NoSuchElementException e) {
            System.out.println("\nВы завершили игру с помощью <Ctrl+D>. До свидания!");
            System.exit(0);
        }
    }

    private boolean isCorrectLetter(char letter){
        return (guessedLetters.contains(letter) || notGuessedLetters.contains(letter));
    }

    void start(){
        System.out.println("Доброе пожаловать в игру 'ВИСЕЛИЦА'!!!");
        System.out.println("Изначально у вас есть " + userAttempts + " попыток");
        System.out.println("(если захотите выйти раньше нажмите <Ctrl+D>");
        System.out.println();
    }

    void end() {
        try {
            System.out.println("Вы хотите сыграть еще раз? да/нет");
            continueGame = scanner.next();
            if (continueGame.equals("да")) {
                System.out.println("Новое слово уже загадано!");
                this.answer = dictionary.generateWord(); //генерируем новое слово
                this.hiddenAnswer = "*".repeat(answer.length()); //обновляем маску
                this.userAttempts = maxAttempts; //сбрасываем попытки
                this.guessedLetters.clear();
                this.notGuessedLetters.clear();
                play();
            } else if (continueGame.equalsIgnoreCase("нет")) {
                System.out.println("Спасибо за игру!");
            } else {
                System.out.println("Неверный ввод. Завершаем игру.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("\nВы завершили игру с помощью <Ctrl+D>. До свидания!");
            System.exit(0);
        }
    }
}
