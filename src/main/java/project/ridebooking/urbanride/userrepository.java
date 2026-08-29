package project.ridebooking.urbanride;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.ridebooking.urbanride.models.UserDetails;

import java.util.List;

@Repository
public interface userrepository extends JpaRepository<UserDetails,Long> {
    boolean existsByMobileNumber(String mobileNumber);

    //List<UserDetails> id(Long id);

    //Long id(Long id);
}
