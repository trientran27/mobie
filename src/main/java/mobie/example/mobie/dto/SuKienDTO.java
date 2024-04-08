package mobie.example.mobie.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;

@Data
public class SuKienDTO {
	
    private Integer id;
	
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
	private Date createAt;
	
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm" , timezone = "Asia/Ho_Chi_Minh")
	private Date endAt;
	
	private String descrision;
	

}
