package mobie.example.mobie.dto;

import lombok.Data;

@Data
public class KhachMoiDTO {
	
	private Integer id;
	
	private boolean confirm;
	
	
	private SuKienDTO suKien;
}
