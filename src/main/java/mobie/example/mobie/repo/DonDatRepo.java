package mobie.example.mobie.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mobie.example.mobie.entity.DonDat;

public interface DonDatRepo extends JpaRepository<DonDat, Integer> {
	
	//danh sach don dat thewo su kien
	@Query("SELECT d FROM DonDat d WHERE d.suKien.id = :suKienId")
    List<DonDat> findBySuKienId(@Param("suKienId") Integer suKienId);
	
	//tổng tiền của các đơn đặt theo sk
	@Query("SELECT SUM(d.tongGia) FROM DonDat d WHERE d.suKien.id = :suKienId")
	Integer calculateTotalPriceBySuKienId(@Param("suKienId") Integer suKienId);
}
