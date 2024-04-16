package mobie.example.mobie.dto;


import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class DonDatDTO {
	
	private Integer id;
	
//	@JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
//	private Date createAt;
	
	@Min(0)
	private int tongGia;
	
	private DiaDiemDTO diaDiem;
	
	private SuKienDTO suKien;
}
