package mobie.example.mobie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobie.example.mobie.dto.DichVuDTO;
import mobie.example.mobie.entity.DichVu;
import mobie.example.mobie.repo.DichVuRepo;

public interface DichVuService {
	List<DichVuDTO> getall();
}

@Service
class DichVuServiceImpl implements DichVuService{
	
	@Autowired
	DichVuRepo dichVuRepo;
	
	@Override
	@Transactional
	public List<DichVuDTO> getall() {
		
		List<DichVu> dichVus = dichVuRepo.findAll();
		return dichVus.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	
	//convert tu entity sang dto
	private DichVuDTO convert(DichVu dichVu) {
		ModelMapper  modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);	//dam bao du lieu duoc gen chuan
		return modelMapper.map(dichVu, DichVuDTO.class);
	}
	
}
