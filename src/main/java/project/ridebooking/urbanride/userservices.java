package project.ridebooking.urbanride;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ridebooking.urbanride.models.UserDetails;
import project.ridebooking.urbanride.models.userType;

@Service
public class userservices {

    @Autowired
    private userrepository userrepo;

    public UserDetails createUser(String type, String mobileNumber, String password) {
        if (mobileNumber == null || password == null) {
            throw new IllegalArgumentException("mobilenumber and password are required");
        }
        if (userrepo.existsByMobileNumber(mobileNumber)) {
            throw new RuntimeException("mobile number already registered");
        }

        userType types = userType.valueOf(type);

        UserDetails user = new UserDetails();
        user.setType(types);
        user.setMobileNumber(mobileNumber);
        user.setPassword(password);
        return userrepo.save(user);
    }

    public UserDetails updateName(Long id, String name) {
        UserDetails user = userrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setName(name);
        return userrepo.save(user);
    }
}
