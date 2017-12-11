package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import wad.domain.Category;
import wad.domain.News;
import wad.repository.CategoryRepository;
import wad.repository.NewsRepository;
import wad.service.CategoryService;

/**
 *
 * @author rov
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private NewsRepository newsRepo;
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/modify/category/{id}")
    public String categories(@PathVariable Long id, Model model) {
        this.categoryService.initialize();
        model.addAttribute("news", newsRepo.getOne(id));
        model.addAttribute("categories", categoryRepo.findAll());
        return "assignCategory";
    }

    @PostMapping("/modify/category/{id}")
    public String addCategory(@PathVariable Long id, @RequestParam Long categoryId) {
        this.categoryService.add(id, categoryId);
        return "redirect:/modify/{id}";
    }

    @GetMapping("/category/new/{id}")
    public String getCreateCategory(@PathVariable Long id, Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("news", newsRepo.getOne(id));
        return "newCategory";
    }

    @PostMapping("/category/new/{id}")
    public String createCategory(@PathVariable Long id, @RequestParam String name) {
        this.categoryService.create(name);
        return "redirect:/modify/category/{id}";
    }

}
