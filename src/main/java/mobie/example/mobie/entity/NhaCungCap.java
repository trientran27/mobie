package mobie.example.mobie.entity;


import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class NhaCungCap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private DiaDiem diaDiem;
}
