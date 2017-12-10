package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import wad.domain.Author;
import wad.domain.Category;
import wad.domain.News;
import wad.repository.AuthorRepository;
import wad.repository.CategoryRepository;
import wad.repository.NewsRepository;

/**
 *
 * @author rov
 */
@Controller
public class NewsController {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private NewsRepository newsRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @GetMapping("/add")
    public String addPage() {
        return "add";
    }

    @PostMapping("/add")
    public String addNews(@RequestParam String title, @RequestParam String lead, @RequestParam String text) {

        News news = new News();
        news.setTitle(title);
        news.setLead(lead);
        news.setText(text);

        newsRepo.save(news);
        Long id = news.getId();
        return "redirect:/news/{" + id + "}/modify";
    }

    @GetMapping("/news/{id}/modify")
    public String modify(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsRepo.getOne(id));
        return "news";
    }

    @Transactional
    @DeleteMapping("/news/{id}/modify")
    public String delete(@PathVariable Long id) {
        News toDelete = newsRepo.getOne(id);
        for (Author author : toDelete.getAuthors()) {
            author.getNews().remove(toDelete);
        }
        for (Category category : toDelete.getCategories()) {
            category.getNews().remove(toDelete);
        }
        newsRepo.delete(toDelete);
        return "redirect:/";
    }

    @GetMapping("/news/{id}")
    public String listFront(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsRepo.getOne(id));
        return "news";
    }

}
