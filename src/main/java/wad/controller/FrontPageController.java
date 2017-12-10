
package wad.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class FrontPageController {

    
    @Autowired
    private CategoryRepository categoryRepo;
    
    @Autowired
    private NewsRepository newsRepo;
    
    @Autowired
    private AuthorRepository authorRepo;
    
    @GetMapping("/")
    public String listFront(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("newsList", newsRepo.findAll(PageRequest.of(0, 5, Sort.Direction.DESC, "releaseDate")));
        return "index";
    }
    
    @GetMapping("/published")
    public String listPublished(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("newsList", newsRepo.findAll(PageRequest.of(0, 10000, Sort.Direction.DESC, "releaseDate")));
        return "index";
    }
    
    @GetMapping("/popular")
    public String listPopular(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("newsList", newsRepo.findAll(PageRequest.of(0, 10000, Sort.Direction.DESC,"read")));
        return "index";
    }
    
    @GetMapping("/category/{name}")
    public String listCategory(@PathVariable String name, Model model) {
        Category category = categoryRepo.findByName(name);
        List<News> list = new ArrayList<>();
        for(News news : newsRepo.findAll()) {
            if(news.getCategories().contains(category)) {
                list.add(news);
            }
        }
        model.addAttribute("newsList", list);
        model.addAttribute("category", categoryRepo.findByName(name));
        return "listByCategory";
    }
    
}
