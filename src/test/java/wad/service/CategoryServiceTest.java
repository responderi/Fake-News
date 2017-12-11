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
import wad.repository.CategoryRepository;
import wad.repository.NewsRepository;

/**
 *
 * @author rov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private CategoryRepository categoryRepo;

    @Test
    public void testIfInitializingWorks() {
        this.categoryService.initialize();
        assertFalse(this.categoryRepo.findAll().isEmpty());
    }
    
    @Test
    public void testThatCreatingDoesNotAddSame() {
        this.categoryService.initialize();
        this.categoryService.create("General");
        assertEquals(1, this.categoryRepo.count());
    }
    
}
