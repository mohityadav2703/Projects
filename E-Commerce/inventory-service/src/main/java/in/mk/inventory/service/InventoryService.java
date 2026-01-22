package in.mk.inventory.service;

import in.mk.inventory.dto.InventoryRequest;
import in.mk.inventory.dto.InventoryResponse;

public interface InventoryService {

	void addOrUpdateStock(InventoryRequest request);

	InventoryResponse checkStock(Long productId);

	void reserveStock(Long productId, int qty);

	void releaseStock(Long productId, int qty);
	
}
