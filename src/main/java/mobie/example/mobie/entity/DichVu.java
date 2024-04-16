package mobie.example.mobie.entity;

import java.util.List;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class DichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String descrision;
	
	//quan he voi dv_dd(khoa ngoai nam o dv_dd)
	@OneToMany(mappedBy = "dichVu", cascade = CascadeType.ALL)	//khi dich vu xoa thif du lieu bang dv_dd cung xoa
	private List<DichVu_DiaDiem> dichVu_DiaDiems;
	
}
