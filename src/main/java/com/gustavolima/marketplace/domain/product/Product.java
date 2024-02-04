package com.gustavolima.marketplace.domain.product;

import com.gustavolima.marketplace.controllers.DTOs.ProductDTO;
import com.gustavolima.marketplace.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    private String categoryId;
    private String description;
    @Id
    private String id;
    private String ownerId;
    private Integer price;
    private String title;

    public Product(ProductDTO productDTO) {
        this.categoryId = productDTO.categoryId();
        this.description = productDTO.description();
        this.ownerId = productDTO.ownerId();
        this.price = productDTO.price();
        this.title = productDTO.title();
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("categoryId", this.categoryId);
        jsonObject.put("description", this.description);
        jsonObject.put("id", this.id);
        jsonObject.put("ownerId", this.ownerId);
        jsonObject.put("title", this.title);

        return jsonObject.toString();
    }
}
