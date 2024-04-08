package mobie.example.mobie.entity;

import java.util.Date;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class LichTrinh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Date createAt;
	
	private Date endAt;
	
	private String descrision;
	
	@ManyToOne
	private SuKien suKien;
	
}
