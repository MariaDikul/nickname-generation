package ru.netology;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger counterForTree = new AtomicInteger(0);
    public static AtomicInteger counterForFour = new AtomicInteger(0);
    public static AtomicInteger counterForFive = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread threadForPalindrome = new Thread(() -> {
            for (String text : texts) {
                int counter = 0;
                for (int i = 0; i < text.length() - 1; i++) {
                    if (text.charAt(i) == text.charAt(i + 1)) {
                        counter++;
                    }
                }
                if (text.charAt(0) == text.charAt(text.length() - 1) &&
                        text.charAt(1) == text.charAt(text.length() - 2) && counter +1 != text.length()) {
                    switch (text.length()) {
                        case 3:
                            counterForTree.getAndIncrement();
                            break;
                        case 4:
                            counterForFour.getAndIncrement();
                            break;
                        case 5:
                            counterForFive.getAndIncrement();
                            break;
                    }
                }
            }
        });

        Thread threadForMono = new Thread(() -> {
            for (String text : texts) {
                int counter = 0;
                for (int i = 0; i < text.length() - 1; i++) {
                    if (text.charAt(i) == text.charAt(i + 1)) {
                        counter++;
                    }
                }
                if (counter + 1 == text.length()) {
                    switch (text.length()) {
                        case 3:
                            counterForTree.getAndIncrement();
                            break;
                        case 4:
                            counterForFour.getAndIncrement();
                            break;
                        case 5:
                            counterForFive.getAndIncrement();
                            break;
                    }
                }
            }
        });

        Thread threadForAlphabet = new Thread(() -> {
            for (String text : texts) {
                int counter = 0;
                int counter2 = 0;
                for (int i = 0; i < text.length() - 1; i++) {
                    if (text.charAt(i) <= text.charAt(i + 1)) {
                        counter++;
                    }
                    if (text.charAt(i) == text.charAt(i + 1)) {
                        counter2++;
                    }
                }
                if (counter + 1 == text.length() && counter2 + 1 != text.length()) {
                    switch (text.length()) {
                        case 3:
                            counterForTree.getAndIncrement();
                            break;
                        case 4:
                            counterForFour.getAndIncrement();
                            break;
                        case 5:
                            counterForFive.getAndIncrement();
                            break;
                    }
                }
            }
        });

        threadForPalindrome.start();
        threadForMono.start();
        threadForAlphabet.start();

        threadForPalindrome.join();
        threadForMono.join();
        threadForAlphabet.join();

        System.out.println("Красивых слов с длиной 3: " + counterForTree);
        System.out.println("Красивых слов с длиной 4: " + counterForFour);
        System.out.println("Красивых слов с длиной 5: " + counterForFive);

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}