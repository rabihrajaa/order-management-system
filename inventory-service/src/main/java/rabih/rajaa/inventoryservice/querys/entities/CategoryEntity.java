package rabih.rajaa.inventoryservice.querys.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private Instant createdAt;
}
