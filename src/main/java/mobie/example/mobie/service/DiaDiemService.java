package mobie.example.mobie.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import mobie.example.mobie.dto.DiaDiemDTO;
import mobie.example.mobie.entity.DiaDiem;
import mobie.example.mobie.repo.DiaDiemRepo;

public interface DiaDiemService {
	void create(DiaDiemDTO diaDiemDTO);
	
	void update(DiaDiemDTO diaDiemDTO);
	
	void delete(int id);
	
	DiaDiemDTO getById(int id);
	
	List<DiaDiemDTO> getAll();
	
	//dia diem theo don dat
	DiaDiemDTO getByIdDd(int id_dd);
	
	//dia diem theo id sk
	List<DiaDiemDTO> getByIdSk(int id_sk);
	
	//tim kiem khi list null
	List<DiaDiemDTO> searchAllNotList(@Valid String name, Date start, Date end);

	
	//tim kiem tong hop
	List<DiaDiemDTO> searchAll(@Valid String name, Date start, Date end, List<Integer> id_DichvuDs);
		
}


@Service
class DiaDiemServiceImpl implements DiaDiemService{

	
	@Autowired
	DiaDiemRepo diaDiemRepo;

	@Override
	@Transactional
	public void create(DiaDiemDTO diaDiemDTO) {
		DiaDiem diaDiem = new ModelMapper().map(diaDiemDTO, DiaDiem.class);
		diaDiemRepo.save(diaDiem);
		
	}

	@Override
	@Transactional
	public void update(DiaDiemDTO diaDiemDTO) {
		DiaDiem diaDiem = diaDiemRepo.findById(diaDiemDTO.getId()).orElse(null);
		
		if(diaDiem != null) {
			diaDiem.setCreateAt(diaDiemDTO.getCreateAt());
			diaDiem.setEndAt(diaDiemDTO.getEndAt());
			
			diaDiem.setName(diaDiemDTO.getName());
			
			diaDiemRepo.save(diaDiem);
		}
	}

	@Override
	@Transactional
	public void delete(int id) {
		diaDiemRepo.deleteById(id);		
	}
	
	@Override
	@Transactional
	public List<DiaDiemDTO> getAll(){
		List<DiaDiem> diaDiems = diaDiemRepo.findAll();
		
		return diaDiems.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	//nen hien thi cac danh sách đia điểm đã đặt của sự kiện ở bảng đơn đặt(lấy mỗi tên)
	@Override
	@Transactional
	public DiaDiemDTO getByIdDd(int id_dd) {
		
		DiaDiem diaDiem = diaDiemRepo.findByDonDatId(id_dd); 
		return convert(diaDiem);
	}
	

	@Override
	@Transactional
	public List<DiaDiemDTO> getByIdSk(int id_sk) {
		List<DiaDiem> diaDiems = diaDiemRepo.findDiaDiemBySuKienId(id_sk);
		return diaDiems.stream().map(c -> convert(c)).collect(Collectors.toList());
	}
	
	
	@Override
	@Transactional
	public List<DiaDiemDTO> searchAllNotList(@Valid String name, Date start, Date end) {
		List<DiaDiem> diaDiems = diaDiemRepo.searchDiaDiemByNotList(name, start, end);
		return diaDiems.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<DiaDiemDTO> searchAll(@Valid String name, Date start, Date end, List<Integer> id_DichvuDs) {
		long number = 0;
		if(id_DichvuDs!=null) {
			number = id_DichvuDs.size();
		}
		List<DiaDiem> diaDiems = diaDiemRepo.searchDiaDiemByCriteria(name, id_DichvuDs, start, end, number);
		return diaDiems.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	
	//convert tu entity sang dto
	private DiaDiemDTO convert(DiaDiem diaDiem) {
		ModelMapper  modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);	//dam bao du lieu duoc gen chuan
		return modelMapper.map(diaDiem, DiaDiemDTO.class);
	}

	@Override
	@Transactional
	public DiaDiemDTO getById(int id) {
		DiaDiem diaDiem = diaDiemRepo.findById(id).orElseThrow(NoResultException :: new);
		return convert(diaDiem);
	}

}
