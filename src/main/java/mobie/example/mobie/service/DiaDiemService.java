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
	
	//tim kiem dia diem theo ten
	List<DiaDiemDTO> searchName(@Valid String name);
	
	//tim kiem theo thoi gian bat dau
	List<DiaDiemDTO> searchStartDate(@Valid Date start);
	
	//tim kiem thoi gian ket thuc
	List<DiaDiemDTO> searchEndDate(@Valid Date end);
	
	//tim kiem khoang thoi gian
	List<DiaDiemDTO> searchAllDate(@Valid Date start, Date end);
	
	//tim kiem theo dich vu
	List<DiaDiemDTO> searchDv(@Valid List<Integer> id_DichvuDs);
	
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
	public List<DiaDiemDTO> searchName(@Valid String name) {
		List<DiaDiem> diaDiems = diaDiemRepo.findAllDiaDiemByName("%" + name + "%");
		return diaDiems.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<DiaDiemDTO> searchStartDate(@Valid Date start) {
		List<DiaDiem> diaDiems = diaDiemRepo.searchByDateStart(start);
		return diaDiems.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<DiaDiemDTO> searchEndDate(@Valid Date end) {
		List<DiaDiem> diaDiems = diaDiemRepo.searchByDateEnd(end);
		return diaDiems.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<DiaDiemDTO> searchAllDate(@Valid Date start, Date end) {
		List<DiaDiem> diaDiems = diaDiemRepo.searchByDate(start, end);
		return diaDiems.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<DiaDiemDTO> searchDv(@Valid List<Integer> id_DichvuDs) {
		List<DiaDiem> diaDiems = diaDiemRepo.findDiaDiemsByDichVuIds(id_DichvuDs);
		return diaDiems.stream().map(c -> convert(c)).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<DiaDiemDTO> searchAll(@Valid String name, Date start, Date end, List<Integer> id_DichvuDs) {
		List<DiaDiem> diaDiems = diaDiemRepo.searchDiaDiemByCriteria(name, id_DichvuDs, start, end);
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
