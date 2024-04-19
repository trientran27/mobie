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
	
	
//	 tim kiem cac truong khong phai la danh sach
	@Query("SELECT d FROM DiaDiem d WHERE (:name IS NULL OR d.name LIKE %:name%) " +
		       "AND (:start IS NULL OR d.createAt <= :start) " +
		       "AND (:end IS NULL OR d.endAt >= :end)")
	 List<DiaDiem> searchDiaDiemByNotList(@Param("name") String name, @Param("start") Date start, @Param("end") Date end);
		
	
	//tong hơp
	@Query("SELECT DISTINCT d " +
		       "FROM DiaDiem d " +
		       "JOIN DichVu_DiaDiem dvd ON d.id = dvd.diaDiem.id " +
		       "JOIN dvd.dichVu dv " +
		       "WHERE (:name IS NULL OR d.name LIKE %:name%) " +
		       "AND (:start IS NULL OR d.createAt <= :start) " +
		       "AND (:end IS NULL OR d.endAt >= :end) " +
		       "AND dv.id IN :id_DichvuDs " +
		       "GROUP BY d.id " +
		       "HAVING COUNT(DISTINCT dvd.dichVu.id) = :number")
	List<DiaDiem> searchDiaDiemByCriteria(
	        @Param("name") String name,
	        @Param("id_DichvuDs") List<Integer> id_DichvuDs,
	        @Param("start") Date start,
	        @Param("end") Date end,
	        @Param("number") Long number);
	
 
	
}
