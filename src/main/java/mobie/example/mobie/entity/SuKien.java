package mobie.example.mobie.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class SuKien {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private Date createAt;
	
	private Date endAt;
	
	private String descrision;
	
	//1 su kien co nhieu lich trinh duoc tao
    @OneToMany(mappedBy = "suKien", cascade = CascadeType.ALL)
	private List<LichTrinh> lichTrinhs ;
    
    //quan he voi danh sach don dat(khoa phu ben don dat)
  	@OneToMany(mappedBy = "suKien")
  	private List<DonDat> donDats;
  	
  	//1 su kien co nhieu khach moi
  	@OneToMany(mappedBy = "suKien")
	private List<KhachMoi> khachMois ;

}
