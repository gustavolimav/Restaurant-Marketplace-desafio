package com.gustavolima.marketplace.domain.product;

import com.gustavolima.marketplace.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    private String id;
    private String description;
    private String title;
    private Integer price;
    private String ownerId;
    private Category category;
}
