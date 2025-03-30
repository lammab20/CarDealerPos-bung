package at.htlkaindorf.uebung_5.repos;

import at.htlkaindorf.uebung_5.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findByModel(String model);

    List<Inventory> getInventoriesByHorsepowerAfter(int i);

}
