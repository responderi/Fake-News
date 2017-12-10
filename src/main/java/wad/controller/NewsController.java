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
        news.setRead(0);
        newsRepo.save(news);
        Long id = news.getId();
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
    public String newsPage(@PathVariable Long id, Model model) {
        News news = newsRepo.getOne(id);
        news.setRead(news.getRead() + 1);
        newsRepo.save(news);
        model.addAttribute("news", newsRepo.getOne(id));
        model.addAttribute("authors", newsRepo.getOne(id).getAuthors());
        model.addAttribute("categories", newsRepo.getOne(id).getCategories());
        return "news";
    }

    @GetMapping("/modify/author/{id}")
    public String authors(@PathVariable Long id, Model model) {
        model.addAttribute("news", newsRepo.getOne(id));
        return "addAuthors";
    }

    @PostMapping("/modify/author/{id}")
    public String addAuthor(@PathVariable Long id, @RequestParam String name) {

        News news = newsRepo.getOne(id);
        Author author = new Author();
        author.setName(name);
        news.addAuthor(author);
        author.addNews(news);
        authorRepo.save(author);
        newsRepo.save(news);
        return "redirect:/modify/{id}";
    }

    @GetMapping("/modify/category/{id}")
    public String categories(@PathVariable Long id, Model model) {
        if (categoryRepo.findAll().isEmpty()) {
            Category category = new Category();
            category.setName("General");
            categoryRepo.save(category);
        }
        model.addAttribute("news", newsRepo.getOne(id));
        model.addAttribute("categories", categoryRepo.findAll());
        return "assignCategory";
    }

    @PostMapping("/modify/category/{id}")
    public String addCategory(@PathVariable Long id, @RequestParam Long categoryId) {
        News news = newsRepo.getOne(id);
        Category category = categoryRepo.getOne(categoryId);
        news.addCategory(category);
        category.addNews(news);
        categoryRepo.save(category);
        newsRepo.save(news);
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
        if (!categoryRepo.findAll().contains(categoryRepo.findByName(name))) {

            Category category = new Category();
            category.setName(name);
            categoryRepo.save(category);
        }
        return "redirect:/modify/category/{id}";
    }

}
