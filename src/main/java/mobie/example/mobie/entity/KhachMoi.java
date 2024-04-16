package mobie.example.mobie.entity;


import javax.persistence.*;
import lombok.Data;


@Entity
@Data
public class KhachMoi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private boolean confirm;
	
	
	@ManyToOne
	private SuKien suKien;
}
