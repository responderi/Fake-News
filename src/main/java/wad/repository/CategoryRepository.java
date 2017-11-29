/*

 */
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Category;

/**
 *
 * @author rovarje
 */
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
