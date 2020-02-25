package com.RecieptManger.demoApp.Repositiory;

import com.RecieptManger.demoApp.Entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends CrudRepository<Admin,Integer> {
}
