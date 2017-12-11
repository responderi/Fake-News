package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Service
public class CategoryService {

    @Autowired
    private NewsRepository newsRepo;

    @Autowired
    private AuthorRepository authorRepo;
    
    @Autowired
    private CategoryRepository categoryRepo;
    
    @Transactional
    public void initialize() {
        if (categoryRepo.findAll().isEmpty()) {
            Category category = new Category();
            category.setName("General");
            categoryRepo.save(category);
        }
    }
    
    @Transactional
    public void add(Long newsId, Long categoryId) {
        News news = newsRepo.getOne(newsId);
        Category category = categoryRepo.getOne(categoryId);
        news.addCategory(category);
        category.addNews(news);
        categoryRepo.save(category);
        newsRepo.save(news);
    }
    
    @Transactional
    public void create(String name) {
        if (!categoryRepo.findAll().contains(categoryRepo.findByName(name))) {

            Category category = new Category();
            category.setName(name);
            categoryRepo.save(category);
        }
    }
}