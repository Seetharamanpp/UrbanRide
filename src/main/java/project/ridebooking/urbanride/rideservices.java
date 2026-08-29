package project.ridebooking.urbanride;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ridebooking.urbanride.models.RideDetail;
import project.ridebooking.urbanride.models.UserDetails;
import project.ridebooking.urbanride.models.status;
import project.ridebooking.urbanride.models.userType;

import java.util.List;

@Service
public class rideservices {
    @Autowired
    userrepository userrepository;
    @Autowired
    riderepository riderepository;
    public RideDetail ride_req(Long id,String pick_up_location,String drop_location){
        UserDetails customer=userrepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID"));
        if(customer.getType()!= userType.customer) {
            throw new RuntimeException("Invalid Customer ID");
        }
        /*if(customer.getId() && status.requested){
            throw new RuntimeException("User book one ride at the time");
        }*/
        if(riderepository.hasActiveRide(customer.getId(),List.of(status.requested,status.in_progress))){
            throw new RuntimeException("User can book only one ride at a time");
        }

        RideDetail request=new RideDetail();
        request.setCustomer_id(customer.getId());
        request.setPickUp(pick_up_location);
        request.setDrop(drop_location);
        request.setFare(100);
        request.setStatus(status.requested);
        return riderepository.save(request);
    }
    public List<RideDetail>availableRides(){
        return riderepository.findByStatus(status.requested);
    }
    @Transactional
    public RideDetail acceptRide(Long ride_id,Long captain_id){
        RideDetail ride=riderepository.findById(ride_id).orElseThrow(() ->new RuntimeException("Invalid ride ID"));
        if(ride.getStatus()!=status.requested){
            throw new RuntimeException("Ride already booked");
        }
        UserDetails user=userrepository.findById(captain_id).orElseThrow(()->new RuntimeException("Invalid ID"));
        if(user.getType()!=userType.captain){
            throw new RuntimeException("Not a captain");
        }
        ride.setCaptain_id(captain_id);
        ride.setStatus(status.in_progress);
        return riderepository.save(ride);
    }
    public RideDetail completeRide(Long rideId, Long captainId) {
        RideDetail ride = riderepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));
        if (!ride.getCaptain_id().equals(captainId)) {
            throw new RuntimeException("This captain is not assigned to the ride");
        }
        if (!ride.getStatus().equals(status.in_progress)) {
            throw new RuntimeException("Ride cannot be completed");
        }
        ride.setStatus(status.completed);
        return riderepository.save(ride);
    }
    public RideDetail cancelByCaptain(Long rideId, Long captainId) {

        RideDetail ride = riderepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (!ride.getCaptain_id().equals(captainId)) {
            throw new RuntimeException("Unauthorized captain");
        }

        ride.setStatus(status.cancelled_by_captain);
        return riderepository.save(ride);
    }
    public RideDetail cancelByCustomer(Long rideId, Long customerId) {

        RideDetail ride = riderepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (!ride.getCustomer_id().equals(customerId)) {
            throw new RuntimeException("Unauthorized customer");
        }

        ride.setStatus(status.cancelled_by_customer);
        return riderepository.save(ride);
    }
}

