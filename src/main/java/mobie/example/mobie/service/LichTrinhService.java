package mobie.example.mobie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.NoResultException;
import mobie.example.mobie.dto.LichTrinhDTO;
import mobie.example.mobie.entity.LichTrinh;
import mobie.example.mobie.repo.LichTrinhRepo;

public interface LichTrinhService {
	void create(LichTrinhDTO lichTrinhDTO);
	
	void update(LichTrinhDTO lichTrinhDTO);
	
	void delete(int id);
	
	LichTrinhDTO getById(int id);
	
	List<LichTrinhDTO> getAllByIdSk(int id_sk);
}

@Service
class LichTrinhServiceImpl implements LichTrinhService{
	@Autowired
	LichTrinhRepo lichTrinhRepo;

	@Override
	@Transactional
	public void create(LichTrinhDTO lichTrinhDTO) {
		LichTrinh lichTrinh = new ModelMapper().map(lichTrinhDTO, LichTrinh.class);	//convert tu dto sang entity
		lichTrinhRepo.save(lichTrinh);
		
	}

	@Override
	@Transactional
	public void update(LichTrinhDTO lichTrinhDTO) {
		LichTrinh lichTrinh = lichTrinhRepo.findById(lichTrinhDTO.getId()).orElse(null);
		
		if(lichTrinh != null) {
			lichTrinh.setName(lichTrinhDTO.getName());
			lichTrinh.setCreateAt(lichTrinhDTO.getCreateAt());
			lichTrinh.setEndAt(lichTrinhDTO.getEndAt());
			
			lichTrinhRepo.save(lichTrinh);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		lichTrinhRepo.deleteById(id);
	}

	@Override
	@Transactional
	public List<LichTrinhDTO> getAllByIdSk(int id_sk) {
		
		List<LichTrinh> lichTrinhs = lichTrinhRepo.findBySuKienId(id_sk);
		return lichTrinhs.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	
	//convert tu entity sang dto
	private LichTrinhDTO convert(LichTrinh lichTrinh) {
		ModelMapper  modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);	//dam bao du lieu duoc gen chuan
		return modelMapper.map(lichTrinh, LichTrinhDTO.class);
	}

	@Override
	@Transactional
	public LichTrinhDTO getById(int id) {
		LichTrinh lichTrinh = lichTrinhRepo.findById(id).orElseThrow(NoResultException :: new);
		return convert(lichTrinh);
	}
	
}