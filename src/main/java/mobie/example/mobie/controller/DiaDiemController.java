package mobie.example.mobie.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import mobie.example.mobie.dto.DiaDiemDTO;
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
	@GetMapping("/list")
	public ResponseDTO<List<DiaDiemDTO>> getAll(){
		List<DiaDiemDTO> diaDiemDTOs = diaDiemService.getAll();
		
		// Lặp qua danh sách kết quả và lấy danh sách ảnh cho mỗi địa điểm
	    for (DiaDiemDTO diaDiemDTO : diaDiemDTOs) {
	        List<String> imageURLs = imageService.getAll(diaDiemDTO.getId());
	        diaDiemDTO.setImageURLs(imageURLs);
	    }
	    
		return ResponseDTO.<List<DiaDiemDTO>>builder().code(200).data(diaDiemDTOs).build();
	}
	
	//get anh theo dd
//	@GetMapping("/listImage/{id}")
//	public ResponseDTO<List<String>> listLt(@PathVariable("id") int id){
//		List<String> imageURLs = imageService.getAll(id);
//		
//		return ResponseDTO.<List<String>>builder().code(200).data(imageURLs).build();
//	}
	
	
	
    // Định nghĩa định dạng ngày tháng
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	@PostMapping("/search")
	public ResponseDTO<List<DiaDiemDTO>> search(@RequestParam(required = false) String name,
	                                            @RequestParam(required = false) String startDate,
	                                            @RequestParam(required = false) String endDate,
	                                            @RequestParam(required = false) List<Integer> id_DichvuDs) throws ParseException {
	    List<DiaDiemDTO> result;
	    
	    // Chuyển đổi các tham số ngày tháng từ String sang Date
        Date start = null;
        Date end = null;
        if (startDate != null) {
            start = DATE_FORMAT.parse(startDate);
        }
        if (endDate != null) {
            end = DATE_FORMAT.parse(endDate);
        }
	    
	    if(name == null && start == null && end == null && (id_DichvuDs == null || id_DichvuDs.isEmpty())) {
	    	result = diaDiemService.getAll();
	    }else {
	    	if(id_DichvuDs == null || id_DichvuDs.isEmpty()) {
	    		result = diaDiemService.searchAllNotList(name, start, end);
	    	}else {
	    		result = diaDiemService.searchAll(name, start, end, id_DichvuDs);
	    	}
		}
	    
	    // Lặp qua danh sách kết quả và lấy danh sách ảnh cho mỗi địa điểm
	    for (DiaDiemDTO diaDiemDTO : result) {
	        List<String> imageURLs = imageService.getAll(diaDiemDTO.getId());
	        diaDiemDTO.setImageURLs(imageURLs);
	    }

	    return ResponseDTO.<List<DiaDiemDTO>>builder().code(HttpStatus.OK.value()).data(result).build();
	}
}
//	public ResponseDTO<List<DiaDiemImageDTO>> search(@RequestParam(required = false) String name,
//    @RequestParam(required = false) String startDate,
//    @RequestParam(required = false) String endDate,
//    @RequestParam(required = false) List<Integer> id_DichvuDs) throws ParseException {
//List<DiaDiemImageDTO> result = new ArrayList<>();
//
//// Chuyển đổi các tham số ngày tháng từ String sang Date
//Date start = null;
//Date end = null;
//if (startDate != null) {
//start = DATE_FORMAT.parse(startDate);
//}
//if (endDate != null) {
//end = DATE_FORMAT.parse(endDate);
//}
//
//if (name == null && start == null && end == null && (id_DichvuDs == null || id_DichvuDs.isEmpty())) {
//List<DiaDiemDTO> diaDiemDTOs = diaDiemService.getAll();
//for (DiaDiemDTO diaDiemDTO : diaDiemDTOs) {
//List<String> imageURLs = imageService.getAll(diaDiemDTO.getId());
//result.add(new DiaDiemImageDTO(diaDiemDTO, imageURLs));
//}
//} else {
//if (id_DichvuDs == null || id_DichvuDs.isEmpty()) {			// khong tim kiems dịch vu
//List<DiaDiemDTO> diaDiemDTOs = diaDiemService.searchAllNotList(name, start, end);
//for (DiaDiemDTO diaDiemDTO : diaDiemDTOs) {
//List<String> imageURLs = imageService.getAll(diaDiemDTO.getId());
//result.add(new DiaDiemImageDTO(diaDiemDTO, imageURLs));
//}
//} else {
//List<DiaDiemDTO> diaDiemDTOs = diaDiemService.searchAll(name, start, end, id_DichvuDs);
//for (DiaDiemDTO diaDiemDTO : diaDiemDTOs) {
//List<String> imageURLs = imageService.getAll(diaDiemDTO.getId());
//result.add(new DiaDiemImageDTO(diaDiemDTO, imageURLs));
//}
//}
//}
//
//return ResponseDTO.<List<DiaDiemImageDTO>>builder().code(HttpStatus.OK.value()).data(result).build();
//}


