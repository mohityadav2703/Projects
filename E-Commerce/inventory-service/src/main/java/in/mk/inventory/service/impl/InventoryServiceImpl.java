package in.mk.inventory.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.mk.inventory.dto.InventoryRequest;
import in.mk.inventory.dto.InventoryResponse;
import in.mk.inventory.entity.Inventory;
import in.mk.inventory.repository.InventoryRepository;
import in.mk.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void addOrUpdateStock(InventoryRequest request) {
        Inventory inv = repository.findByProductId(request.getProductId())
                .orElse(new Inventory(null, request.getProductId(), 0));

        inv.setQuantity(inv.getQuantity() + request.getQuantity());
        repository.save(inv);
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public InventoryResponse checkStock(Long productId) {
        Inventory inv = repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        return new InventoryResponse(productId, inv.getQuantity());
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public void reserveStock(Long productId, int qty) {
        Inventory inv = repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        if (inv.getQuantity() < qty) {
            throw new RuntimeException("Insufficient stock");
        }

        inv.setQuantity(inv.getQuantity() - qty);
        repository.save(inv);
    }


    @Override
    @PreAuthorize("hasRole('USER')")
    public void releaseStock(Long productId, int qty) {
        Inventory inv = repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Stock not found"));

        inv.setQuantity(inv.getQuantity() + qty);
    }
}
