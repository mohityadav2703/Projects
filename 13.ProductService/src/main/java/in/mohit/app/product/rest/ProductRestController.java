package in.mohit.app.product.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.mohit.app.product.dto.ProductCategoryDTO;
import in.mohit.app.product.dto.ProductDTO;
import in.mohit.app.product.response.ApiResponse;
import in.mohit.app.product.service.ProductService;

@RestController
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@GetMapping("/getAllCategories")
	public ResponseEntity<ApiResponse<List<ProductCategoryDTO>>> allCategories(){
		List<ProductCategoryDTO> allCategories=productService.findAllCategories();
		
		ApiResponse<List<ProductCategoryDTO>> response = new ApiResponse<>();
		if(!allCategories.isEmpty()) {
			response.setStatus(200);
			response.setMessage("Fetched Categories Successfull");
			response.setData(allCategories);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("Failed to Fetch Categories");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/getAllProducts/{categoryId}")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductByCategoryId(@PathVariable(name="categoryId") Long categoryId){
		List<ProductDTO> allProducts=productService.findProductsByCategoryId(categoryId);
		ApiResponse<List<ProductDTO>> response= new ApiResponse<>();
		
		if(!allProducts.isEmpty()) {
			response.setStatus(200);
			response.setMessage("Fetched All Products Successfull");
			response.setData(allProducts);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("Failed to Fetch Product");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getProductById/{id}")
	public ResponseEntity<ApiResponse<ProductDTO>> getProductById(@PathVariable(name="id") Long id){
		ProductDTO product=productService.findByProductId(id);
		ApiResponse<ProductDTO> response = new ApiResponse<>();
		
		if(product!=null) {
			response.setStatus(200);
			response.setMessage("Fetched Product Successfull");
			response.setData(product);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("Failed to Fetch Product");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/getProductByName/{name}")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getProductByName(@PathVariable(name="name") String name){
		List<ProductDTO> product= productService.findByProductName(name);
		ApiResponse<List<ProductDTO>> response= new ApiResponse<>();
		if(!product.isEmpty()) {
			response.setStatus(200);
			response.setMessage("Fetched Product Successfull");
			response.setData(product);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatus(500);
			response.setMessage("Failed to Fetch Product");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
