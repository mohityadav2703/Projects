package in.mk.product.service;

import java.util.List;

import in.mk.product.dto.ProductRequest;
import in.mk.product.dto.ProductResponse;

public interface ProductService {
	ProductResponse create(ProductRequest request);

	List<ProductResponse> getAll();

	List<ProductResponse> search(String keyword);
}
