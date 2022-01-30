package ru.nogen.wordsCount.utils;

import ru.nogen.wordsCount.model.Page;

public class TextParser {



    /** Метод обработки строки
     * <br>Заменяем в строке служебные символы и цифры на ":"
     * <br>Приводим строку к нижнему регистру
     * @param str строка
     * @return String
     */
    // Так как задача посчитать слова, то нужно было бы заменить все не буквенные символы на разделитель
    // text = text.replaceAll("[^(a-zA-Zа-яёА-ЯЁ)]+",":");
    // Но в условии написано использовать список разделителей
    // Поэтому вырезаем из текста набор разделителей
    // Потом вырезаем цифры и приводим все слова к нижнему регистру
    public String prepareStringToSplit(String str){
        str=str.replaceAll("[\\n\\r\\t\\s%!\"#\\$&'\\(\\)\\*\\+,-\\./:;<=>\\?@\\[\\]\\^_`\\{\\|\\}~«»©°—\\−№’]+",":");
        str=str.replaceAll("[0-9]+",":");
        return str.toLowerCase();
    }

/** Метод разбиения строки по разделителю ":"
 *
 * @param str строка
 * @return String[] массив слов
 */
    public String[] splitText(String str){
        String[] splitedTextArr = prepareStringToSplit(str).split(":");
        return splitedTextArr;
    }
}
