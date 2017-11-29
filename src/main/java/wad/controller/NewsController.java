
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String addQuestion(@RequestParam String title, @RequestParam String lead, @RequestParam String text) {
 
        News news = new News();
        news.setTitle(title);
        news.setLead(lead);
        news.setText(text);
 
        newsRepo.save(news);
 
        return "redirect:/";
    }
}
