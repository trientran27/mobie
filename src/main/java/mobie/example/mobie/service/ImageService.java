package mobie.example.mobie.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobie.example.mobie.dto.ImageDTO;
import mobie.example.mobie.entity.Image;
import mobie.example.mobie.repo.ImageRepo;

public interface ImageService {
	
	void create(ImageDTO imageDTO);
	
	void delete(int id);
	
	List<String> getAll(int id_dd);
}

@Service
class ImageServiceImpl implements ImageService{
	
	@Autowired
	ImageRepo imageRepo;

	@Override
	@Transactional
	public void create(ImageDTO imageDTO) {
		Image image = new ModelMapper().map(imageDTO, Image.class);
		imageRepo.save(image);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		imageRepo.deleteById(id);
	}

	@Override
	@Transactional
	public List<String> getAll(int id_dd) {
		List<String> images = imageRepo.findImagesByDiaDiemId(id_dd);
		return images;
	}
	
	//convert tu entity sang dto
//	private ImageDTO convert(Image image) {
//		ModelMapper  modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);	//dam bao du lieu duoc gen chuan
//		return modelMapper.map(image, ImageDTO.class);
//	}
}