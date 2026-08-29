package project.ridebooking.urbanride;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import project.ridebooking.urbanride.models.RideDetail;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ride")
public class ridecontroller {
    @Autowired
    rideservices rservices;
    @PostMapping("/riderequest/{id}")
    ResponseEntity<RideDetail> details(@PathVariable Long id, @RequestBody Map<String,String>body) {
        String pick_up_location = body.get("pick_up_location");
        String drop_location = body.get("drop_location");
        RideDetail ride = rservices.ride_req(id,pick_up_location,drop_location);
        return ResponseEntity.ok(ride);
    }
    @GetMapping("/availablerides")
    public List<RideDetail>viewRides(){
        return rservices.availableRides();
    }
    @PostMapping("/accept/{ride_id}/{captain_id}")
    public RideDetail accept(@PathVariable Long ride_id,@PathVariable Long captain_id){
        return rservices.acceptRide(ride_id,captain_id);
    }
    @PostMapping("/complete/{rideId}/{captainId}")
    public RideDetail completeRide(@PathVariable Long rideId,@PathVariable Long captainId) {
        return rservices.completeRide(rideId, captainId);
    }
    @PostMapping("/cancel/captain/{rideId}/{captainId}")
    public RideDetail cancelByCaptain(@PathVariable Long rideId,@PathVariable Long captainId) {
        return rservices.cancelByCaptain(rideId, captainId);
    }
    @PostMapping("/cancel/customer/{rideId}/{customerId}")
    public RideDetail cancelByCustomer(@PathVariable Long rideId,@PathVariable Long customerId) {
        return rservices.cancelByCustomer(rideId, customerId);
    }

}
