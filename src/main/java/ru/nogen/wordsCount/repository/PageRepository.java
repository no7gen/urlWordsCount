package ru.nogen.wordsCount.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nogen.wordsCount.model.Page;
import ru.nogen.wordsCount.model.PageRequest;

@Repository
public interface PageRepository extends CrudRepository<Page,Integer> {

    /** Поиск страницы url в БД
     *
     * @param url строка url страницы
     * @return {@link Page} объект страницы
     */
    Page findByUrl(String url);
}
