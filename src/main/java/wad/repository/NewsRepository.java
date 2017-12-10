/*

 */
package wad.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Category;
import wad.domain.News;

/**
 *
 * @author rovarje
 */
public interface NewsRepository extends JpaRepository<News, Long>{

}
