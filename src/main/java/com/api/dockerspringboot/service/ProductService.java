package com.api.dockerspringboot.service;

import com.api.dockerspringboot.dto.ProductDTO;
import com.api.dockerspringboot.entity.Product;
import com.api.dockerspringboot.error.ResourceNotFoundException;
import com.api.dockerspringboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public Iterable<Product> findAll(){
       return productRepository.findAll();
    }

    public ProductDTO findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        return (obj.map(x -> x.toDto()).orElseThrow(() -> new ResourceNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName())));
    }

    @Transactional
    public Product save(Product product) {
        return  productRepository.save(product);
    }

    public void delete(Long id){
        verifyIfProductExists(id);
        productRepository.deleteById(id);
    }

    public void update(Product productNew, Long id) {
        productRepository.findById(id).map(p -> {
            p.setCategory(productNew.getCategory() );
            p.setName(productNew.getName());
            p.setPrice(productNew.getPrice());
            return productRepository.save(p);
        }).orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
    }

    private void verifyIfProductExists(Long id){
        if (productRepository.findById(id) == null)
            throw new ResourceNotFoundException("Student not found for ID: "+id);
    }


}
