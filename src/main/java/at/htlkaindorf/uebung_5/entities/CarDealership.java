package at.htlkaindorf.uebung_5.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CarDealership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String location;

    @OneToMany(mappedBy = "carDealership", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Inventory> inventory;

    @OneToOne(cascade = CascadeType.PERSIST)
    private MyUser salesManager;

    @Override
    public String toString() {
        return  name;
    }
}
