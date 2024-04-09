package mobie.example.mobie.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DiaDiemDTO {
	private Integer id;
	
	@NotBlank
	private String name;
	
	private String address;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
	private Date createAt;
	
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
	private Date endAt;
    
	@Min(0)
	private int price;
	
	private String image;
	
	private String descrision;
	
	private String supplier_id;
}
