package mobie.example.mobie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mobie.example.mobie.entity.DichVu_DiaDiem;

public interface DichVu_DiaDiemRepo extends JpaRepository<DichVu_DiaDiem, Integer> {
	
	//tinh tong gia dich vu dia diem
	@Query("SELECT SUM(d.price) FROM DichVu_DiaDiem d WHERE d.diaDiem.id = :id")
    int TotalPriceByDiaDiem(@Param("id") Integer id);
}
