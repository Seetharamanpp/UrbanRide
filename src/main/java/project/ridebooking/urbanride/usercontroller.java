package project.ridebooking.urbanride;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import project.ridebooking.urbanride.models.UserDetails;

import java.util.Map;


@RestController
@RequestMapping("/user")
public class usercontroller {
    @Autowired
    userservices uservices;
    /*@PostMapping("/userdetails")
    ResponseEntity<UserDetails> details(@RequestBody UserDetails user){
        return new ResponseEntity<>(uservices.login(user), HttpStatus.CREATED);
    }*/
    /*ResponseEntity<String>choose_type(@RequestBody Map<String,String> body){
        String type=body.get("type");

    }*/
    @PostMapping("/signup")
    ResponseEntity<UserDetails>createuser(@RequestBody Map<String ,String>body){
        String type=body.get("type");
        String mobilenumber= body.get("mobilenumber");
        String password= body.get("password");
        UserDetails user=uservices.createUser(type,mobilenumber,password);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @PutMapping("/{id}/name")
    ResponseEntity<UserDetails>updatename(@PathVariable Long id,@RequestBody Map<String,String> body){
        String name= body.get("name");
        UserDetails updated=uservices.updateName(id,name);
        return ResponseEntity.ok(updated);
    }
}