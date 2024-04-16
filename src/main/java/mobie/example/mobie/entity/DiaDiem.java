package mobie.example.mobie.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class DiaDiem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String address;
	
	private String latitude; //vi do
	
	private String longitude; //kinh do
	
	private Date createAt;
	
	private Date endAt;
	
	private int price;
	
	private String descrision;
	
	//khoa ngoai nam ben bang don dat
	@OneToMany(mappedBy = "diaDiem")
	private List<DonDat> donDats;
	
	
	//quan he voi dich dich vu dia diem(khoa phụ nam ben dv_dd)
	@OneToMany(mappedBy = "diaDiem", cascade = CascadeType.ALL) //khi dia diem xoa thi du lieu bang dv_dd cung xoa
	private List<DichVu_DiaDiem> dichVu_DiaDiems;
	
	
	//khoa phu nam tai image
	@OneToMany(mappedBy = "diaDiem", cascade = CascadeType.ALL)
	private List<Image> images;
	//
	@OneToMany(mappedBy = "diaDiem")
	private List<NhaCungCap> nhaCungCap;
}
