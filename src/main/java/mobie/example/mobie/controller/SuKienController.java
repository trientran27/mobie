package mobie.example.mobie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mobie.example.mobie.dto.DiaDiemDTO;
import mobie.example.mobie.dto.DonDatDTO;
import mobie.example.mobie.dto.LichTrinhDTO;
import mobie.example.mobie.dto.ResponseDTO;
import mobie.example.mobie.service.DiaDiemService;
import mobie.example.mobie.service.DonDatService;
import mobie.example.mobie.service.LichTrinhService;

@RestController
@RequestMapping("/sukien")
public class SuKienController {
	
	@Autowired
	DonDatService donDatService;
	
	@Autowired
	LichTrinhService lichTrinhService;
	
	@Autowired
	DiaDiemService diaDiemService;
	
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
	
	//get dia diem theo id_sk cho guest
	@GetMapping("/listddiem/{id}")
	public List<DiaDiemDTO> listDdiem(@PathVariable("id") int id){
		List<DiaDiemDTO> diaDiemDTOs = diaDiemService.getByIdSk(id);
		
//		return ResponseDTO.<List<DiaDiemDTO>>builder().code(200).data(diaDiemDTOs).build();
		return diaDiemDTOs;
	}
	
	//get dia diem theo id_sk
	@GetMapping("/listddiem2/{id}")
	public ResponseDTO<List<DiaDiemDTO> > listDdiem2(@PathVariable("id") int id){
		List<DiaDiemDTO> diaDiemDTOs = diaDiemService.getByIdSk(id);
			
			return ResponseDTO.<List<DiaDiemDTO>>builder().code(200).data(diaDiemDTOs).build();
	}
	
	//get tong gia dơn dat của su kien
	@GetMapping("/totalsum/{id}")
	public ResponseDTO<Integer> totalsumDonDat(@PathVariable("id") int id){
		return ResponseDTO.<Integer>builder().code(200).data(donDatService.getPricePlus(id)).build();
	}
}
