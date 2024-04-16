package mobie.example.mobie.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LichTrinhDTO {
	private Integer id;
	
	@NotBlank
	private String name;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
	private Date createAt;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
	private Date endAt;
	
	private String descrision;
	
	private SuKienDTO suKien;
}
