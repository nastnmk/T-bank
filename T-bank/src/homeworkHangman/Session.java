package homeworkHangman;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Session{
    private Scanner scanner;
    private Dictionary dictionary;
    private String answer; //сгенерированное слово
    private String hiddenAnswer; //маска для загаданного слова
    private Results results;
    private Input input;

    private final int maxAttempts;
    private int userAttempts;

    private ArrayList<Character> not_guessedLetters;
    private ArrayList<Character> guessedLetters;

    private String continueGame;


    public Session() {
        this.dictionary = new Dictionary();
        this.answer = dictionary.generateWord();
        this.maxAttempts = 5;
        this.userAttempts = maxAttempts;
        this.hiddenAnswer = "*".repeat(answer.length());
        this.not_guessedLetters = new ArrayList<>();
        this.guessedLetters = new ArrayList<>();
        this.results = new Results();
        this.input = new Input();
        this.scanner = new Scanner(System.in);
    }

    private boolean Check(Input input){ //проверяет, если буква есть в слове и заменяет, если да
        boolean isCorrect = false;
        for (int i = 0; i < hiddenAnswer.length(); i++){
            if (answer.charAt(i) == input.getLetter()){
                hiddenAnswer = hiddenAnswer.substring(0, i) + input.getLetter() + hiddenAnswer.substring(i + 1);
                isCorrect = true;
            }
        }
        if (isCorrect){
            results.Success(hiddenAnswer, input, guessedLetters);
        }
        else {
            userAttempts--;
            results.Lose(hiddenAnswer, userAttempts, input, not_guessedLetters);
        }
        return isCorrect;
    }

    public void gameOver(){
        results.Loser(answer);
    }

    public void win(){
        results.Win(answer);
    }

    public boolean isWordGuessed(){
        return hiddenAnswer.equals(answer);
    }
    void play(){
        try {
            while (userAttempts != 0) {
                input.write(); // записывает букву и проверяет на корректность
                while (input.ifWasUsed(input.getLetter(), guessedLetters, not_guessedLetters)) {
                    input.ifWasUsed(input.getLetter(), guessedLetters, not_guessedLetters);
                }
                Check(input);
                if (isWordGuessed()) {
                    win();
                    end();
                    return;
                }
            }
            gameOver();
            end();
        } catch (NoSuchElementException e) {
            System.out.println("\nВы завершили игру с помощью <Ctrl+D>. До свидания!");
            System.exit(0);
        }
    }

    void start(){
        System.out.println("Доброе пожаловать в игру 'ВИСЕЛИЦА'!!!");
        System.out.println("Изначально у вас есть " + userAttempts + " попыток");
        System.out.println("(если захотите выйти раньше нажмите <Ctrl+D>");
        System.out.println();
    }

    void end() {
        try {
            System.out.println("Вы хотите сыграть еще раз? Да/Нет");
            continueGame = scanner.next();
            if (continueGame.equals("Да")) {
                System.out.println("Новое слово уже загадано!");
                this.answer = dictionary.generateWord(); //генерируем новое слово
                this.hiddenAnswer = "*".repeat(answer.length()); //обновляем маску
                this.userAttempts = maxAttempts; //сбрасываем попытки
                this.guessedLetters.clear();
                this.not_guessedLetters.clear();
                play();
            } else if (continueGame.equalsIgnoreCase("Нет")) {
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
