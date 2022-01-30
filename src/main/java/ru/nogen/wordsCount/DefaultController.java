package ru.nogen.wordsCount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nogen.wordsCount.model.Page;
import ru.nogen.wordsCount.repository.PageRepository;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    private PageRepository pageRepository;

    @RequestMapping("/")
    public String index(Model model){

        Iterable<Page> pageIterable = pageRepository.findAll();
        ArrayList<Page> pages = new ArrayList<>();
        for (Page page:pageIterable) {
            pages.add(page);
        }
        model.addAttribute("pages",pages);
        return "index";
    }
}
