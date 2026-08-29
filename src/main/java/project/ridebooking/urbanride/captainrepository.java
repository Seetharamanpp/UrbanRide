package project.ridebooking.urbanride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.ridebooking.urbanride.models.CaptainDetails;

@Repository
public interface captainrepository extends JpaRepository<CaptainDetails, Long> {
}