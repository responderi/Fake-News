
package wad.domain;

import java.util.ArrayList;
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
public class Category extends AbstractPersistable<Long> {
    private String name;
    @ManyToMany(mappedBy="categories")
    private List<News> news;
    
    public void addNews(News news) {
        if (this.news == null) {
            this.news = new ArrayList<News>();
        }
 
        this.news.add(news);
    }
}
