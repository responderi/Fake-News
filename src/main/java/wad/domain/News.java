
package wad.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author rov
 */
@AllArgsConstructor
@Data
@Entity
public class News extends AbstractPersistable<Long> {
    
    private String title;
    private String lead;
    private String text;
    private LocalDateTime releaseDate;
    @ManyToMany
    private List<Author> authors;
    @ManyToMany
    private List<Category> categories;
    
    public News(){
        this.releaseDate = LocalDateTime.now();
    }
    
    public void addAuthor(Author author) {
        if (this.authors == null) {
            this.authors = new ArrayList<Author>();
        }
 
        this.authors.add(author);
    }
    
    public void addCategory(Category category) {
        if (this.categories == null) {
            this.categories = new ArrayList<Category>();
        }
 
        this.categories.add(category);
    }
}
