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
import wad.domain.News;
import wad.repository.AuthorRepository;
import wad.repository.NewsRepository;
import wad.service.AuthorService;

/**
 *
 * @author rov
 */
@Controller
public class AuthorController {

    @Autowired
    private NewsRepository newsRepo;
    
    @Autowired
    private AuthorService authorService;

    @GetMapping("/modify/author/{id}")
    public String authors(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsRepo.getOne(id));
        return "addAuthors";
    }

    @PostMapping("/modify/author/{id}")
    public String addAuthor(@PathVariable Long id, @RequestParam String name) {
        this.authorService.add(id, name);
        return "redirect:/modify/{id}";
    }
}