package mobie.example.mobie.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class DichVu_DiaDiem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private DichVu dichVu;
	
	@ManyToOne 
	private DiaDiem diaDiem;
	

}
