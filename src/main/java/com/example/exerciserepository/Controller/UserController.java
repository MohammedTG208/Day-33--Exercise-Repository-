package com.example.exerciserepository.Controller;


import com.example.exerciserepository.Model.User;
import com.example.exerciserepository.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;

    @GetMapping("/getall")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUser());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            userService.addNewUser(user);
            return ResponseEntity.status(201).body("User added successfully");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User deleted successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }else {
            userService.updateUser(user,id);
            return ResponseEntity.status(201).body("User updated successfully");
        }
    }
    @GetMapping("/check/{email}/{pass}")
    public ResponseEntity checkUser(@PathVariable String email, @PathVariable String pass){

        return ResponseEntity.status(200).body(userService.getUserByEmailAndPassword(email, pass));
    }

    @GetMapping("/get/by/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.getByEmail(email));
    }

    @GetMapping("/get/role/{r}")
    public ResponseEntity getUserByRole(@PathVariable String r){
        return ResponseEntity.status(200).body(userService.getUserByRoles(r));
    }

    @GetMapping("/get/age/{age}")
    public ResponseEntity getUserByAge(@PathVariable int age){
        return ResponseEntity.status(200).body(userService.getAllUserByAge(age));
    }
}
