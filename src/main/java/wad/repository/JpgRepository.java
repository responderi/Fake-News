/*

 */
package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.JpgObject;

/**
 *
 * @author rovarje
 */
public interface JpgRepository extends JpaRepository<JpgObject, Long>{
    
}