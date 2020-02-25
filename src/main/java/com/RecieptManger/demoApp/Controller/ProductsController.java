package com.RecieptManger.demoApp.Controller;

import com.RecieptManger.demoApp.Entity.Admin;
import com.RecieptManger.demoApp.Entity.Products;
import com.RecieptManger.demoApp.Repositiory.AdminRepo;
import com.RecieptManger.demoApp.Repositiory.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductsController {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    AdminRepo adminRepo;

    @GetMapping("/admin/{id}/products")
    public List<Products> retrieveAll(){
        return (List<Products>) productRepo.findAll();
    }

    @PostMapping("/admin/{id}/products")
    public ResponseEntity<Object> addProduct(@PathVariable int id, @RequestBody Products products) throws Exception{
        Optional<Admin> admin = adminRepo.findById(id);
        if(!admin.isPresent()){
            throw new Exception();
        }
        Admin admin1 = admin.get();
        products.setAdmin(admin1);

        productRepo.save(products);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(products.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/admin/{id}/products/{pid}")
    public Optional<Products> retrieveProduct(@PathVariable int id, @PathVariable int pid) throws Exception{
        Optional<Admin> admin = adminRepo.findById(id);

        if(!admin.isPresent()){
            throw new Exception();
        }
        Optional<Products> products = productRepo.findById(pid);
        if(!products.isPresent()){
            throw new Exception();
        }

        if(!products.get().getAdmin().getId().equals(admin.get().getId()))
        {
            throw new Exception();
        }

        return products;
    }



}
