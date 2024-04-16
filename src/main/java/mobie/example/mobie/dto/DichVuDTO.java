package mobie.example.mobie.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class DichVuDTO {
	
	private Integer id;
	
	@NotBlank
	private String name;
	
	private String descrision;
}
