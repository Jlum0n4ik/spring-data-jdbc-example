package com.example.springjdbccrud.rest;

import com.example.springjdbccrud.model.User;
import com.example.springjdbccrud.model.UserSequenceValueCallBack;
import com.example.springjdbccrud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserRepository userRepository;
    private UserSequenceValueCallBack userSequenceValueCallBack;
    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers() {
        var users = userRepository.findAll();
        if(users != null) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
