package ru.nogen.wordsCount;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nogen.wordsCount.model.Page;
import ru.nogen.wordsCount.model.PageRequest;
import ru.nogen.wordsCount.model.Word;
import ru.nogen.wordsCount.repository.PageRepository;
import ru.nogen.wordsCount.repository.PageRequestRepository;
import ru.nogen.wordsCount.repository.WordRepository;
import ru.nogen.wordsCount.utils.TextParser;
import ru.nogen.wordsCount.utils.WordsCounter;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

@RestController
public class PageController {

    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private PageRequestRepository pageRequestRepository;
    @Autowired
    private WordRepository wordRepository;

    // Возвращаем list всех запрошенных ранее страниц (URL) из БД
    @GetMapping("/pages/")
    public List<Page> list(){

        Iterable<Page> pageIterable = pageRepository.findAll();
        ArrayList<Page> pages = new ArrayList<Page>();
        for (Page page:pageIterable) {
            pages.add(page);
        }

        return pages;
    }

    // Возвращаем list всех ранее произведенных запросов конкретной страницы по Id url из БД
    @GetMapping("/page/requests/{pageId}")
    public List<PageRequest> getPagerequestList(@PathVariable int pageId){

        Iterable<PageRequest> pageRequestIterable = pageRequestRepository.findBypage_id(pageId);
        ArrayList<PageRequest> pageRequests = new ArrayList<PageRequest>();
        for (PageRequest pageRequest:pageRequestIterable) {
            pageRequests.add(pageRequest);
        }

        return pageRequests;
    }

    // Возвращаем list всех слов из БД для конкретного ранее произведенного запроса по id запроса
    @GetMapping("/page/request/word/{pageRequestId}")
    public List<Word> getWords4Request(@PathVariable int pageRequestId){
        Iterable<Word> wordIterable = wordRepository.findByPage_request_id(pageRequestId);
        ArrayList<Word> words = new ArrayList<Word>();
        for (Word word:wordIterable) {
            words.add(word);
        }

        return words;
    }

// Запрос страницы по введенному пользователем адресу, сохранение данных в БД
    @PostMapping("/pages/")
    public int addPage(Page page){

        int httpStatusCode=0;
        Map<String,Long> words = new HashMap<String,Long>();

    // Запрашиваем страницу
        try {
            Connection connection = Jsoup.connect(page.getUrl());
            //specify user agent
            connection.userAgent("Mozilla/5.0");
            Document doc = connection.get();
            // Если удачно, то чохраняем статус запроса
            httpStatusCode = doc.connection().response().statusCode();
            // Разбиваем текст полученной страницы на слова и формируем Map<Слово,Количество>
            String[] splitedWords = new TextParser().splitText(doc.body().text());
            words = new WordsCounter().countWords(splitedWords);
    // Отлавливаем плохие статусы
        } catch (HttpStatusException e) {
            httpStatusCode = e.getStatusCode();
            e.printStackTrace();
    // Если формат URL не верный вернем -1
        }catch (final IllegalArgumentException e) {
            e.printStackTrace();
            return -1;
    // Если хост не верный вернем -2
        }catch (UnknownHostException e) {
            e.printStackTrace();
            return -2;
    // Остальные ошибки - надо отлаживать если появятся
        }catch (IOException e) {
            e.printStackTrace();
        }
    // Создаем запрос
        PageRequest newPageRequest = new PageRequest();
        newPageRequest.setRequestTime(new Date());
        newPageRequest.setHttpStatusCode(httpStatusCode);
    // Наполняем запрос словами с количеством их повторов
        for (Map.Entry<String, Long> entry:words.entrySet()) {
            newPageRequest.addWord2Request(new Word(entry.getKey(), entry.getValue()));
        }
    // Ищем URL страницы в БД
        Page newPage = pageRepository.findByUrl(page.getUrl());
    //Если URL еще не запрашивали, то создаём страницу
        if(newPage == null) {
            newPage = new Page();
            newPage.setUrl(page.getUrl());
        }
    // Добавляем к странице запрос и сохраняем в БД
        newPage.addRequest(newPageRequest);;
        newPage = pageRepository.save(newPage);
    // Возвращаем идентификатор страницы
        return newPage.getId();
    }
}
