package mobie.example.mobie.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import jakarta.validation.Valid;
import mobie.example.mobie.dto.ImageDTO;
import mobie.example.mobie.dto.ResponseDTO;
import mobie.example.mobie.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	@Autowired
    private Cloudinary cloudinary; // Autowire Cloudinary bean vào controller
	
	@Value("${upload.folder}")
	private String UPLOAD_FOLDER;
	
	
	@PostMapping("/create")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseDTO<ImageDTO> create(@ModelAttribute @Valid ImageDTO imageDTO){
        //khong luu file vao db
        if(!imageDTO.getFile().isEmpty()) {
            try {
                // Tên tệp ảnh trong Cloudinary
                //String filename = imageDTO.getFile().getOriginalFilename();
                
                // Tải ảnh lên Cloudinary và nhận URL của ảnh đã tải lên
                Map uploadResult = cloudinary.uploader().upload(imageDTO.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));

                // Lấy URL của ảnh từ phản hồi của Cloudinary
                String imageURL = (String) uploadResult.get("secure_url");

                // Lưu URL của ảnh vào biến imageURL
                imageDTO.setImageURL(imageURL);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        imageService.create(imageDTO);

        //tra ve dia diem dto voi id moi
        return ResponseDTO.<ImageDTO>builder().code(200).data(imageDTO).build();
    }
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseDTO<Void> delete(@PathVariable("id") int id){
		imageService.delete(id);
		
		return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
	}
	
	
	
	
	//luu vao o cung
	
	
//	@PostMapping("/create")
//	@ResponseStatus(code = HttpStatus.OK)
//	public ResponseDTO<ImageDTO> create(@ModelAttribute @Valid ImageDTO imageDTO){
//		//khong luu file vao db
//		if(!imageDTO.getFile().isEmpty()) {
//			try {
//				// khong luu file vao db
//				// luu vao thu muc (folder) nao do
//				File folder = new File(UPLOAD_FOLDER);
//				if (!folder.exists())
//					folder.mkdirs();	//chua co thi tao folder
//				// ten file nguoi dung upload
//				String filename = imageDTO.getFile().getOriginalFilename();
//				// file nam ben trong folder
//				File saveFile = new File(folder.getPath() + File.separator + filename);
//				// copy toan bo vao saveFile
//				imageDTO.getFile().transferTo(saveFile);
//				// luu xuong db ten file
//				imageDTO.setImageURL(filename);	//lay ten file luu vao data base
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		imageService.create(imageDTO);
//		
//		//tra ve dia diem dto voi id moi
//		return ResponseDTO.<ImageDTO>builder().code(200).data(imageDTO).build();
//	}
	
	
	
}
