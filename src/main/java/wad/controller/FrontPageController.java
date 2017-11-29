
package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("news", newsRepo.findAll(PageRequest.of(0, 5, Sort.Direction.ASC, "releaseDate")));
        return "index";
    }
}
