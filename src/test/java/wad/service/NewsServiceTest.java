/*

 */
package wad.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wad.domain.News;
import wad.repository.NewsRepository;

/**
 *
 * @author rov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsRepository newsRepo;

    @Test
    public void testIfAddingNewsWorks() {
        this.newsService.add("title", "lead", "text");
        assertFalse(newsRepo.findAll().isEmpty());
    }
    
}
