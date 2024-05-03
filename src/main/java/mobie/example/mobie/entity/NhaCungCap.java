package mobie.example.mobie.entity;


import java.util.List;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class NhaCungCap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@OneToMany(mappedBy = "nhaCungCap")
	private List<DiaDiem> diaDiems;
}
