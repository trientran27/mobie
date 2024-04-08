package mobie.example.mobie.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mobie.example.mobie.entity.LichTrinh;

public interface LichTrinhRepo extends JpaRepository<LichTrinh, Integer> {
	
	//in danh sach lich trinh tao cho su kien
	@Query("SELECT l FROM LichTrinh l WHERE l.suKien.id = :suKienId")
    List<LichTrinh> findBySuKienId(@Param("suKienId") Integer suKienId);
}
