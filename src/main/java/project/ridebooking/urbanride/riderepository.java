package project.ridebooking.urbanride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.ridebooking.urbanride.models.RideDetail;
import project.ridebooking.urbanride.models.status;

import java.util.List;

@Repository
public interface riderepository extends JpaRepository<RideDetail, Long> {
    @Query("""
select count(r)>0 from RideDetail r where r.customer_id=:customerId and r.status IN ('requested','inprogress')
""")
    boolean hasActiveRide(@Param("customerId") Long customerId ,@Param("status")List<status>status);

    List<RideDetail> findByStatus(status status);

}