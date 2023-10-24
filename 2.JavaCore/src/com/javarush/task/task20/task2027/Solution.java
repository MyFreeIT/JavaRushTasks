package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/

public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> foundWords = detectAllWords(crossword, "home", "same");
        for (Word word : foundWords) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    // Метод для поиска всех заданных слов в кроссворде
    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
// Проходим по каждому слову
        for (String word : words) {
            // Проходим по каждому элементу в кроссворде
            for (int i = 0; i < crossword.length; i++) {
                for (int j = 0; j < crossword[0].length; j++) {
                    // Если первая буква совпадает, ищем слово во всех направлениях
                    if (crossword[i][j] == word.charAt(0)) {
                        for (int di = -1; di <= 1; di++) {
                            for (int dj = -1; dj <= 1; dj++) {
                                // Пропускаем текущее положение
                                if (di == 0 && dj == 0) continue;
                                // Проверяем, содержит ли текущее направление слово
                                if (checkWord(crossword, word, i, j, di, dj)) {
                                    // Создаем объект Word и добавляем его в результат
                                    Word foundWord = new Word(word);
                                    foundWord.setStartPoint(j, i);
                                    foundWord.setEndPoint(j + (word.length() - 1) * dj, i + (word.length() - 1) * di);
                                    result.add(foundWord);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    // Метод для проверки, содержится ли слово в заданном направлении
    private static boolean checkWord(int[][] crossword, String word, int i, int j, int di, int dj) {
        // Проверяем выход за границы массива и совпадение символов
        for (int k = 0; k < word.length(); k++) {
            if (i < 0 || i >= crossword.length || j < 0 || j >= crossword[0].length || crossword[i][j] != word.charAt(k)) {
                return false;
            }
            // Переходим к следующей позиции
            i += di;
            j += dj;
        }
        return true;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}

//Задача (Hard)
//        Java Core, 10 уровень, 10 лекция
//
//Кроссворд
//
//        Нет, нам не придётся решать кроссворды. Нам нужно решить нетривиальную задачку про кроссворды.
//        Есть двумерный массив, а в нём — слова, слова, слова. По горизонтали, по вертикали, по диагонали…
//        Нужно найти все слова в массиве.
//
//        1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
//        2. Метод detectAllWords должен найти все слова из words в массиве crossword.
//        3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
//        text - это само слово, располагается между начальным и конечным элементами
//        4. Все слова есть в массиве.
//        5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в
//        обратном порядке.
//        6. Метод main не участвует в тестировании.
//
//        Requirements:
//        1. В классе Solution должен существовать метод detectAllWords.
//        2. В классе Solution должен существовать статический класс Word.
//        3. Класс Solution не должен содержать статические поля.
//        4. Метод detectAllWords должен быть статическим.
//        5. Метод detectAllWords должен быть публичным.
//        6. Метод detectAllWords должен возвращать список всех слов в кроссворде (согласно условию задачи).