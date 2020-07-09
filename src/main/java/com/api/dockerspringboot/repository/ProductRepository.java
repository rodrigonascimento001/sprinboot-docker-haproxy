package com.api.dockerspringboot.repository;

import com.api.dockerspringboot.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long>{

}