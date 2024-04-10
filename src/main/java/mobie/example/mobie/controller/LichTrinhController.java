package mobie.example.mobie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import mobie.example.mobie.dto.LichTrinhDTO;
import mobie.example.mobie.dto.ResponseDTO;
import mobie.example.mobie.service.LichTrinhService;

@RestController
@RequestMapping("/lichtrinh")
public class LichTrinhController {
	
	@Autowired
	LichTrinhService lichTrinhService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDTO<LichTrinhDTO> create(@RequestBody LichTrinhDTO lichTrinhDTO){
		lichTrinhService.create(lichTrinhDTO);
		
		//tra ve lic trinh dto voi id moi
		return ResponseDTO.<LichTrinhDTO>builder().code(200).data(lichTrinhDTO).build();
	}
	
	@GetMapping("/{id}")
	public ResponseDTO<LichTrinhDTO> get(@PathVariable("id") int id){
		return ResponseDTO.<LichTrinhDTO>builder().code(200).data(lichTrinhService.getById(id)).build();
	}
	
	@PutMapping("/update")
	public ResponseDTO<LichTrinhDTO> update(@RequestBody LichTrinhDTO lichTrinhDTO){
		lichTrinhService.update(lichTrinhDTO);
		return ResponseDTO.<LichTrinhDTO>builder().code(200).data(lichTrinhDTO).build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		lichTrinhService.delete(id);  
		
		return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
	}
	
//	@GetMapping("/list/{id_sk}")
//	public ResponseDTO<List<LichTrinhDTO>> list(@PathVariable("id_sk") int id_sk){
//		List<LichTrinhDTO> lichTrinhDTOs = lichTrinhService.getAllByIdSk(id_sk);
//		
//		return ResponseDTO.<List<LichTrinhDTO>>builder().code(200).data(lichTrinhDTOs).build();
//	}
	
}

