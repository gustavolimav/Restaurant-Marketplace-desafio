package com.gustavolima.marketplace.controllers.DTOs;


public record ProductDTO(String description, String title, String ownerId, Integer price, String categoryId) {
}
