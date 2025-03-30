package at.htlkaindorf.uebung_5.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String model;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufactureDate;

    private int horsepower;
    private String type;
    private int price;
    private int topSpeed;

    @ManyToOne
    @JsonBackReference
    private CarDealership carDealership;


    @Override
    public String toString() {
        return "Car: \n" +
                "       horsepower= " + horsepower + '\n' +
                "       model= " + model + '\n' +
                "       type= " + type + '\n' +
                "       price= " + price + '\n'+
                "       topSpeed= " + topSpeed + '\n'+
                "       carDealership= " + carDealership + '\n'+
                '\n';
    }
}
