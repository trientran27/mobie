package mobie.example.mobie.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String imageURL;
    
    @ManyToOne
    private DiaDiem diaDiem;
}

