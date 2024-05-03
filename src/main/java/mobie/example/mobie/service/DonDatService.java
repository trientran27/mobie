package mobie.example.mobie.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mobie.example.mobie.dto.DonDatDTO;
import mobie.example.mobie.entity.DiaDiem;
import mobie.example.mobie.entity.DonDat;
import mobie.example.mobie.repo.DiaDiemRepo;
import mobie.example.mobie.repo.DonDatRepo;

public interface DonDatService {
	void create(DonDatDTO donDatDTO);
	
	void delete(int id);
	
	List<DonDatDTO> getBySkAndDdiem(int id_ddiem, int id_sk);
	
	List<DonDatDTO> getList(int id_sk);
	
	Integer getPricePlus(int id_sk);
}

@Service
class DonDatServiceImpl implements DonDatService{
	
	@Autowired
	DonDatRepo donDatRepo;
	
	@Autowired
	DiaDiemRepo diaDiemRepo;
	
	@Override
	@Transactional
	public void create(DonDatDTO donDatDTO) {
		DonDat donDat = new ModelMapper().map(donDatDTO, DonDat.class);
		
		DiaDiem diaDiem = diaDiemRepo.findById(donDat.getDiaDiem().getId()).orElse(null);
		
		donDat.setTongGia(diaDiem.getPrice());
		
		donDatRepo.save(donDat);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		donDatRepo.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<DonDatDTO> getList(int id_sk) {
		List<DonDat> donDats = donDatRepo.findBySuKienId(id_sk);
		return donDats.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<DonDatDTO> getBySkAndDdiem(int id_ddiem, int id_sk) {
		
		List<DonDat> donDats = donDatRepo.findByDiaDiemIdAndSuKienId(id_ddiem, id_sk);
		return donDats.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	
	//convert tu entity sang dto
	private DonDatDTO convert(DonDat donDat) {
		ModelMapper  modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);	//dam bao du lieu duoc gen chuan
		return modelMapper.map(donDat, DonDatDTO.class);
	}

	@Override
	@Transactional
	public Integer getPricePlus(int id_sk) {
		
		return donDatRepo.calculateTotalPriceBySuKienId(id_sk);
	}

	
	
}
