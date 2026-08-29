package project.ridebooking.urbanride;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import project.ridebooking.urbanride.models.CaptainDetails;
import project.ridebooking.urbanride.models.UserDetails;

import java.util.Map;
@RestController
@RequestMapping("/captain")
public class captaincontroller {
    @Autowired
    captainservices cservices;
    @PostMapping("/captaindetails/{id}")
    ResponseEntity<CaptainDetails> details(@PathVariable Long id, @RequestBody Map<String,String>body){
        String vehicleNumber=body.get("vehiclenumber");
        String vehicleName= body.get("vehiclename");
        String licenceNumber= body.get("licencenumber");
        CaptainDetails captain=cservices.cdetails(id,vehicleNumber,vehicleName,licenceNumber);
        return ResponseEntity.ok(captain);
    }
}
