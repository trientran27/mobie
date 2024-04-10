package mobie.example.mobie.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mobie.example.mobie.entity.DiaDiem;

public interface DiaDiemRepo extends JpaRepository<DiaDiem, Integer> {
	
	//tim kiem dia diem da dat theo don dat
	@Query("SELECT d.diaDiem FROM DonDat d WHERE d.id = :donDatId")
    DiaDiem findByDonDatId(@Param("donDatId") Integer donDatId);
	
	
	//tim kiem dia diem theo ten
	@Query("SELECT d FROM DiaDiem d WHERE d.name LIKE :x ")
	List<DiaDiem> findAllDiaDiemByName(@Param("x") String x);
	
	//tim kiem dia diem theo khoang thoi gian bat dau
	@Query("SELECT d FROM DiaDiem d " + "WHERE d.createAt <= :start and d.endAt >= :start")
	List<DiaDiem> searchByDateStart(@Param("start") Date start);
	
	//tim kiem dia diem theo khoang thoi gian ket thuc
	@Query("SELECT d FROM DiaDiem d " + "WHERE d.endAt <= :end and d.endAt >= :end")
	List<DiaDiem> searchByDateEnd(@Param("end") Date end);
	
	//tim kiem dia diem theo khoang thoi gian bat dau va ket thuc
	@Query("SELECT d FROM DiaDiem d " + "WHERE d.createAt <= :start and d.endAt >= :end")
	List<DiaDiem> searchByDate(@Param("start") Date start, @Param("end") Date end);

	
	//tim kiem dia diem theo dich vụ dia diem
	@Query("SELECT d FROM DiaDiem d " +
			"JOIN DichVu_DiaDiem dvd ON d.id = dvd.diaDiem.id " +
			"JOIN DichVu dv ON dvd.dichVu.id = dv.id " +
			"WHERE dv.id IN :dichVuIds")
	List<DiaDiem> findDiaDiemsByDichVuIds(@Param("dichVuIds") List<Integer> dichVuIds);

	//tong hop
	@Query("SELECT d FROM DiaDiem d " +
	        "JOIN DichVu_DiaDiem dvd ON d.id = dvd.diaDiem.id " +
	        "JOIN DichVu dv ON dvd.dichVu.id = dv.id " +
	        "WHERE d.name LIKE :name " +
	        "AND dv.id IN :dichVuIds " +
	        "AND (d.createAt BETWEEN :start AND :end) " +
	        "AND (d.endAt BETWEEN :start AND :end)")
	List<DiaDiem> searchDiaDiemByCriteria(
	        @Param("name") String name,
	        @Param("dichVuIds") List<Integer> dichVuIds,
	        @Param("start") Date start,
	        @Param("end") Date end);

	
}
