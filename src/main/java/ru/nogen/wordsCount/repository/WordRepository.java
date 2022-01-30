package ru.nogen.wordsCount.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nogen.wordsCount.model.Page;
import ru.nogen.wordsCount.model.PageRequest;
import ru.nogen.wordsCount.model.Word;

import java.util.List;

@Repository
public interface WordRepository extends CrudRepository<Word,Integer> {

    /** Поиск слов страницы для запроса в БД
     *
     * @param pageRequestId id запроса url страницы
     * @return Список слов с количеством повторов {@link Word}
     */
    @Query(value = "Select * from words where page_request_id = ?1 order by word_count desc, word", nativeQuery = true)
    List<Word> findByPage_request_id(int pageRequestId);
}
