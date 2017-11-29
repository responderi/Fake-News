
package wad.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
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
public class JpgObject extends AbstractPersistable<Long> {

    @Lob
    private byte[] content;
}
