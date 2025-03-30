package at.htlkaindorf.uebung_5.repos;

import at.htlkaindorf.uebung_5.entities.CarDealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDealershipRepository extends JpaRepository<CarDealership, Integer> {
}
