package project.ridebooking.urbanride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ridebooking.urbanride.models.CaptainDetails;
import project.ridebooking.urbanride.models.UserDetails;
import project.ridebooking.urbanride.models.userType;

@Service
public class captainservices {
    @Autowired
    userrepository userrepository;
    @Autowired
    captainrepository captainrepository;
    public CaptainDetails cdetails(Long id,String vehicleNo,String vehicleName,String licenceNumber){
        UserDetails user=userrepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid ID"));
        if(user.getType()!= userType.captain){
            throw new RuntimeException("Invalid captain ID");
        }

        CaptainDetails captain=new CaptainDetails();
        captain.setUser(user);
        String captainname=captain.getUser().getName();
        System.out.println(captainname);
        captain.setVehicleNo(vehicleNo);
        captain.setVehicleName(vehicleName);
        captain.setLicenceNumber(licenceNumber);
        return captainrepository.save(captain);
    }
}

