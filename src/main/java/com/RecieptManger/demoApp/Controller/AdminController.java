package com.RecieptManger.demoApp.Controller;

import com.RecieptManger.demoApp.Entity.Admin;
import com.RecieptManger.demoApp.Repositiory.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    private AdminRepo adminRepo;

    @GetMapping("/admin")
    public List<Admin> retrieveAllAdmins(){return (List<Admin>) adminRepo.findAll();}

    @GetMapping("/admin/{id}")
    public Optional<Admin> retrieveAdminById(@PathVariable int id) throws Exception{
        Optional<Admin> admin = adminRepo.findById(id);
        if(!admin.isPresent()){
            throw new Exception();
        }else {
            return admin;
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<Object> addAdmin(@RequestBody Admin admin){
        Admin newAdmin = adminRepo.save(admin);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAdmin.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
