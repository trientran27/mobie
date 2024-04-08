package mobie.example.mobie.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class DichVu_DiaDiemDTO {
	private Integer id;
	
	@Min(0)
	private int price;
	
	private DichVuDTO dichVu;
	 
	private DiaDiemDTO diaDiem;
}
