package com.RecieptManger.demoApp.Repositiory;

import com.RecieptManger.demoApp.Entity.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo  extends CrudRepository<Products, Integer> {

}
