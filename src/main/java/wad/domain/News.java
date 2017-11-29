
package wad.domain;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author rov
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class News extends AbstractPersistable<Long> {
    
    private String title;
    private String lead;
    private String text;
    private LocalDate releaseDate;
    @ManyToMany
    private List<Author> authors;
    @ManyToMany
    private List<Category> categories;
}
