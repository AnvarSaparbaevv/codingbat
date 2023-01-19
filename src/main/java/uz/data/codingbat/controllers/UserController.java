package uz.data.codingbat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.data.codingbat.entities.User;
import uz.data.codingbat.services.implementss.UserServiceImpl;
import uz.data.codingbat.templates.Result;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl service;

    @GetMapping
    public ResponseEntity<List<User>> getUserList(){
        return ResponseEntity.ok(service.getAllUSers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getByID(id));
    }

    @PostMapping
    public ResponseEntity<Result> addUser(@RequestBody User user){
        Result result = service.addUser(user);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateUser(@RequestBody User user,@PathVariable Integer id){
        Result result = service.updateUser(id, user);
        return ResponseEntity.status(result.getStatus()).body(result);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteUser(@PathVariable Integer id){
        Result result = service.deleteUser(id);
        return ResponseEntity.status(result.getStatus()).body(result);

    }
}
