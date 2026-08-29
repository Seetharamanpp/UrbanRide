//package project.ridebooking.urbanride.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Entity

public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    Boolean completed;

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}





@GetMapping("/h")

public String hello(){
    return "H";
}
@GetMapping("/{id}")
public String hell(@PathVariable long id){
    return "Hello java"+id;
}

@GetMapping("/hello")
public String helloo(@RequestParam String userId, @RequestParam String password) {
    return "Username:"+userId+ "Password" +password;
}

@PostMapping("/create")
ResponseEntity<Todo> createuser(@RequestBody Todo todo) {
    return new ResponseEntity<>(helloservices.createTodo(todo), HttpStatus.CREATED);
}

@PutMapping("/h")
public String helloo(@RequestBody String id) {
    //TODO: process PUT request

    return "update" +id;
}