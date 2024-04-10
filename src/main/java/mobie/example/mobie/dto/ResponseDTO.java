package mobie.example.mobie.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDTO<T> {
	
	private int code;
	private String msg;
	
	@JsonInclude(Include.NON_NULL)
	private T data;
	
	public ResponseDTO(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
}
