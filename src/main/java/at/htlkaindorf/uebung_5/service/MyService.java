package at.htlkaindorf.uebung_5.service;

import at.htlkaindorf.uebung_5.entities.CarDealership;
import at.htlkaindorf.uebung_5.entities.Inventory;
import at.htlkaindorf.uebung_5.jwt.JwtAuthorizedToken;
import at.htlkaindorf.uebung_5.jwt.JwtUtilities;
import at.htlkaindorf.uebung_5.repos.CarDealershipRepository;
import at.htlkaindorf.uebung_5.repos.InventoryRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SimpleTimeZone;

@Service
public class MyService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtilities jwtUtilities;
    private final InventoryRepository inventoryRepository;
    private final CarDealershipRepository carDealershipRepository;

    public MyService(AuthenticationManager authenticationManager, JwtUtilities jwtUtilities, InventoryRepository inventoryRepository, CarDealershipRepository carDealershipRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtilities = jwtUtilities;
        this.inventoryRepository = inventoryRepository;
        this.carDealershipRepository = carDealershipRepository;
    }

    public String signIn(String username, String password){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return jwtUtilities.generateToken(username);
    }

    public String getTotalCars() {
        List<Inventory> inventory = inventoryRepository.findAll();

        int allCars = inventory.size();

        StringBuilder sb = new StringBuilder();

        sb.append("Total number of Cars: { \n");
        sb.append(allCars+"\n}");

        return sb.toString();
    }

    public Inventory getModel(String model){
        Inventory inventory = inventoryRepository.findByModel(model);

        return inventory;
    }

    public String getDealerNames(){
        List<CarDealership> carDealership = carDealershipRepository.findAll();

        Set<CarDealership> carDealershipsSet = new HashSet<>();

        carDealershipsSet.addAll(carDealership);

        StringBuilder sb = new StringBuilder();

        sb.append("Autohäuser: ");
        sb.append(carDealershipsSet);

        return sb.toString();
    }

    public String getHighPsCars(){
        List<Inventory> inventoryList = inventoryRepository.getInventoriesByHorsepowerAfter(600);

        StringBuilder sb = new StringBuilder();

        sb.append(inventoryList);
        return sb.toString();
    }
}
