package mobie.example.mobie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mobie.example.mobie.dto.DonDatDTO;
import mobie.example.mobie.dto.LichTrinhDTO;
import mobie.example.mobie.dto.ResponseDTO;
import mobie.example.mobie.service.DonDatService;
import mobie.example.mobie.service.LichTrinhService;

@RestController
@RequestMapping("/sukien")
public class SuKienController {
	
	@Autowired
	DonDatService donDatService;
	
	@Autowired
	LichTrinhService lichTrinhService;
	
	//get don dat theo su kien

	@GetMapping("/listdd/{id}")
	public ResponseDTO<List<DonDatDTO>> listDd(@PathVariable("id") int id){
		List<DonDatDTO> donDatDTOs = donDatService.getList(id);
		
		return ResponseDTO.<List<DonDatDTO>>builder().code(200).data(donDatDTOs).build();
	}
	
	//get lich trinh theo sk
	@GetMapping("/listlt/{id}")
	public ResponseDTO<List<LichTrinhDTO>> listLt(@PathVariable("id") int id){
		List<LichTrinhDTO> lichTrinhDTOs = lichTrinhService.getAllByIdSk(id);
		
		return ResponseDTO.<List<LichTrinhDTO>>builder().code(200).data(lichTrinhDTOs).build();
	}
}
