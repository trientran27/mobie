package mobie.example.mobie.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mobie.example.mobie.entity.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {
	
	//in danh sach lich trinh tao cho su kien
	@Query("SELECT i.imageURL FROM Image i WHERE i.diaDiem.id = :diaDiemId")
	List<String> findImagesByDiaDiemId(@Param("diaDiemId") Integer diaDiemId);
}
