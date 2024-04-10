package mobie.example.mobie.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import mobie.example.mobie.dto.DiaDiemDTO;
import mobie.example.mobie.dto.ImageDTO;
import mobie.example.mobie.dto.ResponseDTO;
import mobie.example.mobie.service.DiaDiemService;
import mobie.example.mobie.service.ImageService;

@RestController
@RequestMapping("/diadiem")
public class DiaDiemController {
	@Autowired
	DiaDiemService diaDiemService;
	
	@Autowired
	ImageService imageService;
	
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDTO<DiaDiemDTO> create(@RequestBody DiaDiemDTO diaDiemDTO){
		
		diaDiemService.create(diaDiemDTO);
		
		//tra ve dia diem dto voi id moi
		return ResponseDTO.<DiaDiemDTO>builder().code(200).data(diaDiemDTO).build();
	}
	
	@PutMapping("/update")
	public ResponseDTO<DiaDiemDTO> update(@RequestBody DiaDiemDTO diaDiemDTO){
		diaDiemService.update(diaDiemDTO);
		return ResponseDTO.<DiaDiemDTO>builder().code(200).data(diaDiemDTO).build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		diaDiemService.delete(id);
		
		return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
	}
	
	@GetMapping("/{id}")
	public ResponseDTO<DiaDiemDTO> get(@PathVariable("id") int id){
		return ResponseDTO.<DiaDiemDTO>builder().code(200).data(diaDiemService.getById(id)).build();
	}
	
	//get lich trinh theo sk
	@GetMapping("/listImage/{id}")
	public ResponseDTO<List<ImageDTO>> listLt(@PathVariable("id") int id){
		List<ImageDTO> imageDTOs = imageService.getAll(id);
		
		return ResponseDTO.<List<ImageDTO>>builder().code(200).data(imageDTOs).build();
	}
	
	 
	
//	@PostMapping("/search")
//	public ResponseDTO<List<DiaDiemDTO>> searchName(@RequestBody DiaDiemDTO diaDiemDTO){
//		List<DiaDiemDTO> diaDiemDTOs = diaDiemService.searchName(diaDiemDTO.getName());
//		
//		return ResponseDTO.<List<DiaDiemDTO>>builder().code(200).data(diaDiemDTOs).build();
//	}
	@GetMapping("/search")
	public ResponseDTO<List<DiaDiemDTO>> search(@RequestParam(required = false) String name,
	                                            @RequestParam(required = false) Date start,
	                                            @RequestParam(required = false) Date end,
	                                            @RequestParam(required = false) List<Integer> id_DichvuDs) {
	    List<DiaDiemDTO> result;
	    
	    if(name == null && start == null && end == null && (id_DichvuDs == null || id_DichvuDs.isEmpty())) {
	    	result = diaDiemService.getAll();
	    }else {
			result = diaDiemService.searchAll(name, start, end, id_DichvuDs);
		}

	    return ResponseDTO.<List<DiaDiemDTO>>builder().code(HttpStatus.OK.value()).data(result).build();
	}

}
