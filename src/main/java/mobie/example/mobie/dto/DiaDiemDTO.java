package mobie.example.mobie.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class DiaDiemDTO {
	private Integer id;
	
	@NotBlank
	private String name;
	
	private String address;
	
	private String latitude; //vi do
	
	private String longitude; //kinh do
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
//	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date createAt;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
	private Date endAt;
    
	@Min(0)
	private int price;
	
	private String descrision;
	
}
