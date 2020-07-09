package com.api.dockerspringboot.controller;

import com.api.dockerspringboot.dto.ProductDTO;
import com.api.dockerspringboot.entity.Product;
import com.api.dockerspringboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProductController {

    private final Environment env;

    @Value("${message:aDefaultValue}")
    private String s1;

    @Value("#{environment.message}")
    private String s2;

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll(){
        return new ResponseEntity<Iterable<Product>>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<ProductDTO>(productService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@RequestBody Product product,@PathVariable Long id) {
        productService.update(product,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
