package com.api.dockerspringboot.dto;

import com.api.dockerspringboot.entity.Product;
import com.api.dockerspringboot.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private long id;
    private String name;
    private CategoryEnum category;
    private double price;

    public Product toEntity() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setCategory(this.category);
        product.setPrice(this.price);

        return product;
    }
}
