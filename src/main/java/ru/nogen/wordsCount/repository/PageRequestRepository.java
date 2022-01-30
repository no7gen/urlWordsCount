package ru.nogen.wordsCount.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nogen.wordsCount.model.Page;
import ru.nogen.wordsCount.model.PageRequest;

import java.util.List;

@Repository
public interface PageRequestRepository extends CrudRepository<PageRequest,Integer> {

    /** Поиск всех запросов к url в БД
     *
     * @param pageId id url страницы
     * @return Список запросов {@link PageRequest}
     */
    @Query(value = "Select * from page_requests where page_id = ?1", nativeQuery = true)
    List<PageRequest> findBypage_id(int pageId);
}
