package Hangman;

import java.util.Random;

public class Dictionary {
    private static final String[] words = {"ноутбук", "животное", "друзья", "банан", "олимпиада"};
    Random random = new Random();
    public String generateWord(){
        int index = random.nextInt(words.length);
        return  words[index];
    }
}
