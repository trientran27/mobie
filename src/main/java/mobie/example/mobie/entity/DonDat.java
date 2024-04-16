package mobie.example.mobie.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class DonDat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	private Date createAt;
	
	private int tongGia;
	
	
	@ManyToOne
	private DiaDiem diaDiem;
	
	@ManyToOne
	private SuKien suKien;
}
