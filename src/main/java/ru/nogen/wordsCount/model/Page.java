package ru.nogen.wordsCount.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
@Entity
@Table(name = "pages")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String url;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "page_id")
    private List<PageRequest> pageRequestList;

    /**Метод добавления {@link PageRequest} к url страницы {@link Page}
     *
     * @param pageRequest запрос
     */
    public void addRequest(PageRequest pageRequest){
        if(pageRequestList==null){
            pageRequestList = new ArrayList<PageRequest>();
        }
        pageRequestList.add(pageRequest);
    }

}
