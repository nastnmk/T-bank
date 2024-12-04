package homeworkHangman;

import java.util.Random;

public class Dictionary {
    private static final String[] words = {"ноутбук", "животное", "друзья", "банан", "олимпиада"};
    public String generateWord(){
        String randomWord;
        Random random = new Random();
        int index = random.nextInt(words.length);
        randomWord = words[index];
        return randomWord;
    }
}
