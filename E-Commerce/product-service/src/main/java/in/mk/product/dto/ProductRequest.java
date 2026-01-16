package in.mk.product.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String title;
    private Double unitPrice;
    private String imgUrl;
    private Integer unitInStock;
    private String category;
}
