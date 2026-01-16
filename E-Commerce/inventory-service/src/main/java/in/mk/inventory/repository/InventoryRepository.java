package in.mk.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mk.inventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductId(Long productId);
}
