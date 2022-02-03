package ru.nogen.wordsCount.utils;

import java.util.HashMap;
import java.util.Map;

public class WordsCounter {

    /** Метод подсчета вхождения слова в массив
     *
     * @param words String[] массив слов
     * @return Map(слово, количество повторов)
     */
    public Map<String,Long> countWords(String[] words){

        Map<String,Long> wordsMap= new HashMap<String,Long>();
        for (String word:words) {
            Long cnt;
            if(word.length()>0){
                if(wordsMap.containsKey(word)){
                    cnt = wordsMap.get(word) + 1;
                    wordsMap.put(word,cnt);
                }else{
                    wordsMap.put(word,1L);
                }
            }
        }
        return wordsMap;
    }
}
