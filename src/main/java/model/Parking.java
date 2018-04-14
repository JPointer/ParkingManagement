package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="parking", schema="ParkingManager")
public class Parking implements Serializable {
    @Id
    @Column(name="idParking", unique = true, nullable = false)
    private int idParking;

    @Column(name= "city", nullable = false, length = 45)
    private String city;

    @Column(name= "street", nullable = false, length = 45)
    private String street;

    @Column(name= "number", nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "idRate", nullable = false)
    private Rate rate;

    @OneToMany( mappedBy = "parking", cascade=CascadeType.ALL)
    private Set<Visit> visits = new HashSet<Visit>();

    public Parking(){}

    public Parking(int idParking, String city, String street, int number) {
        this.idParking = idParking;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Parking(int idParking, String city, String street, int number, Rate rate) {
        this.idParking = idParking;
        this.city = city;
        this.street = street;
        this.number = number;
        this.rate = rate;
    }
    public Parking(int idParking, String city, String street, int number, Rate rate, Set<Visit> visits) {
        this.idParking = idParking;
        this.city = city;
        this.street = street;
        this.number = number;
        this.rate = rate;
        this.visits = visits;
    }

    public int getIdParking() {
        return idParking;
    }

    public void setIdParking(int idParking) {
        this.idParking = idParking;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }
}
