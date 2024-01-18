package com.gustavolima.marketplace.domain.category;

import com.gustavolima.marketplace.controllers.DTOs.CategoryDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    private String id;
    private String description;
    private String ownerId;
    private String title;

    public Category(CategoryDTO categoryDTO) {
        this.description = categoryDTO.description();
        this.ownerId = categoryDTO.ownerId();
        this.title = categoryDTO.title();
    }
}
