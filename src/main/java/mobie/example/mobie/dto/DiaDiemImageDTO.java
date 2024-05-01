package mobie.example.mobie.dto;

import java.util.List;

import lombok.Data;

@Data
public class DiaDiemImageDTO {
	
	private DiaDiemDTO diaDiemDTO;
	
	private List<String> imageURLs;
	
	public DiaDiemImageDTO(DiaDiemDTO diaDiemDTO, List<String> imageURLs) {
        this.diaDiemDTO = diaDiemDTO;
        this.imageURLs = imageURLs;
    }
	
}
