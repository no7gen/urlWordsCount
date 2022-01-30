package ru.nogen.wordsCount.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String word;

    @Column(name="word_count")
    private long wordCount;

    /**
     * Конструктор - создание нового объекта {@link Word}
     * @param word слово
     * @param wordCount количество повторов слова
     */
    public Word(String word, long wordCount) {
        this.word = word;
        this.wordCount = wordCount;
    }

    public Word() {}
}
