package mobie.example.mobie.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import mobie.example.mobie.entity.DonDat;

public interface DonDatRepo extends JpaRepository<DonDat, Integer> {
	
	
}
