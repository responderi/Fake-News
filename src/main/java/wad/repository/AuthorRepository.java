/*

 */
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Author;

/**
 *
 * @author rovarje
 */
public interface AuthorRepository extends JpaRepository<Author, Long>{
    
}
