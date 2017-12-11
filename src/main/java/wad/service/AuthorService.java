package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.domain.Author;
import wad.domain.News;
import wad.repository.AuthorRepository;
import wad.repository.NewsRepository;

/**
 *
 * @author rov
 */
@Service
public class AuthorService {

    @Autowired
    private NewsRepository newsRepo;

    @Autowired
    private AuthorRepository authorRepo;
    
    @Transactional
    public void add(Long id, String name) {
        News news = newsRepo.getOne(id);
        Author author = new Author();
        author.setName(name);
        news.addAuthor(author);
        author.addNews(news);
        authorRepo.save(author);
        newsRepo.save(news);
    }
}
