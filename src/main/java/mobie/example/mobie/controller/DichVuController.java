package mobie.example.mobie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mobie.example.mobie.dto.DichVuDTO;
import mobie.example.mobie.dto.ResponseDTO;
import mobie.example.mobie.service.DichVuService;

@RequestMapping("/dichvu")
@RestController
public class DichVuController {
	
	@Autowired
	DichVuService dichVuService;
	
	@GetMapping("/list")
	public ResponseDTO<List<DichVuDTO>> getAll(){
		List<DichVuDTO> dichVuDTOs = dichVuService.getall();
		
		return ResponseDTO.<List<DichVuDTO>>builder().code(200).data(dichVuDTOs).build();
	}
}
