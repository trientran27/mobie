package mobie.example.mobie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import mobie.example.mobie.dto.DiaDiemDTO;
import mobie.example.mobie.dto.DonDatDTO;
import mobie.example.mobie.dto.ResponseDTO;
import mobie.example.mobie.service.DiaDiemService;
import mobie.example.mobie.service.DonDatService;

@RestController
@RequestMapping("/dondat")
public class DonDatController {
	
	@Autowired
	DonDatService donDatService;
	
	@Autowired
	DiaDiemService diaDiemService;
	
	@PostMapping("/create")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDTO<DonDatDTO> create(@RequestBody DonDatDTO donDatDTO){
		donDatService.create(donDatDTO);
		
		//tra ve lic trinh dto voi id moi
		return ResponseDTO.<DonDatDTO>builder().code(200).data(donDatDTO).build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		donDatService.delete(id);
		
		return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
	}
//	
//	@GetMapping("/list/{id_sk}")
//	public ResponseDTO<List<DonDatDTO>> list(@PathVariable("id_sk") int id_sk){
//		List<DonDatDTO> donDatDTOs = donDatService.getList(id_sk);
//		
//		return ResponseDTO.<List<DonDatDTO>>builder().code(200).data(donDatDTOs).build();
//	}
	
	//get dia diem theo id don dat
	@GetMapping("/diadiem/{id}")
	public ResponseDTO<DiaDiemDTO> get(@PathVariable("id") int id){
		return ResponseDTO.<DiaDiemDTO>builder().code(200).data(diaDiemService.getByIdDd(id)).build();
	}
}
