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
import wad.domain.Category;
import wad.domain.News;
import wad.repository.AuthorRepository;
import wad.repository.CategoryRepository;
import wad.repository.NewsRepository;
import wad.service.NewsService;

/**
 *
 * @author rov
 */
@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepo;
    
    @Autowired
    private NewsService newsService;

    @GetMapping("/add")
    public String addPage() {
        return "add";
    }

    @PostMapping("/add")
    public String addNews(@RequestParam String title, @RequestParam String lead, @RequestParam String text) {
        Long id = this.newsService.add(title, lead, text);
        return "redirect:/modify/" + id;
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsRepo.getOne(id));
        return "modify";
    }

    @Transactional
    @DeleteMapping("/modify/{id}")
    public String delete(@PathVariable Long id) {
        this.newsService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/news/{id}")
    public String newsPage(@PathVariable Long id, Model model) {
        this.newsService.incrementReads(id);
        model.addAttribute("news", newsRepo.getOne(id));
        model.addAttribute("authors", newsRepo.getOne(id).getAuthors());
        model.addAttribute("categories", newsRepo.getOne(id).getCategories());
        return "news";
    }
}
