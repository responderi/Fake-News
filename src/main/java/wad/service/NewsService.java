
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
public class NewsService {

    @Autowired
    private NewsRepository newsRepo;

    @Transactional
    public void delete(Long id){
        News toDelete = newsRepo.getOne(id);
        for (Author author : toDelete.getAuthors()) {
            author.getNews().remove(toDelete);
        }
        for (Category category : toDelete.getCategories()) {
            category.getNews().remove(toDelete);
        }
        newsRepo.delete(toDelete);
    }
    
    @Transactional
    public Long add(String title, String lead, String text) {
        News news = new News();
        news.setTitle(title);
        news.setLead(lead);
        news.setText(text);
        news.setRead(0);
        newsRepo.save(news);
        Long id = news.getId();
        return id;
    }
    
    @Transactional
    public void incrementReads(Long id) {
        News news = newsRepo.getOne(id);
        news.setRead(news.getRead() + 1);
        newsRepo.save(news);
    }
    
}
