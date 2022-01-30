package ru.nogen.wordsCount.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "page_requests")
public class PageRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="request_time")
    private Date requestTime;

    @OneToMany(fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "page_request_id")
    private List<Word> wordsList;

    @Column(name="http_status_code")
    private int httpStatusCode;

    /**Метод добавления {@link Word} к запросу {@link PageRequest}
     *
     * @param word слово(слово, количество вхождений)
     */
    public void addWord2Request(Word word){
        if(wordsList==null){
            wordsList = new ArrayList<Word>();
        }
        wordsList.add(word);
    }

}
