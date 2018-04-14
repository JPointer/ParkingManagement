package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="rate", schema="ParkingManager")
public class Rate implements Serializable {

    @Id
    @Column(name="idRate", unique = true, nullable = false)
    private int idRate;

    @Column(name= "firstHourRegular", nullable = false)
    private double firstHourRegular;

    @Column(name= "secondHourRegular", nullable = false)
    private double secondHourRegular;

    @Column(name= "firstHourVip", nullable = false)
    private double firstHourVip;

    @Column(name= "secondHourVip", nullable = false)
    private double secondHourVip;

    @Column(name= "mulNextHourRegular", nullable = false)
    private double mulNextHourRegular;

    @Column(name= "mulNextHourVip", nullable = false)
    private double mulNextHourVip;

    @Column(name="currency", nullable = false, length = 10)
    private String currency;

    @OneToMany(mappedBy="rate")
    private Set<Parking> parkings = new HashSet<Parking>();

    public Rate(){}

    public Rate(int idRate, double firstHourRegular, double secondHourRegular, double firstHourVip,
                double secondHourVip, double mulNextHourRegular, double mulNextHourVip, String currency) {
        this.idRate = idRate;
        this.firstHourRegular = firstHourRegular;
        this.secondHourRegular = secondHourRegular;
        this.firstHourVip = firstHourVip;
        this.secondHourVip = secondHourVip;
        this.mulNextHourRegular = mulNextHourRegular;
        this.mulNextHourVip = mulNextHourVip;
        this.currency = currency;
    }

    public Rate(int idRate, double firstHourRegular, double secondHourRegular, double firstHourVip,
                double secondHourVip, double mulNextHourRegular, double mulNextHourVip,
                String currency, Set<Parking> parkings) {
        this.idRate = idRate;
        this.firstHourRegular = firstHourRegular;
        this.secondHourRegular = secondHourRegular;
        this.firstHourVip = firstHourVip;
        this.secondHourVip = secondHourVip;
        this.mulNextHourRegular = mulNextHourRegular;
        this.mulNextHourVip = mulNextHourVip;
        this.currency = currency;
        this.parkings = parkings;
    }

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public double getFirstHourRegular() {
        return firstHourRegular;
    }

    public void setFirstHourRegular(double firstHourRegular) {
        this.firstHourRegular = firstHourRegular;
    }

    public double getSecondHourRegular() {
        return secondHourRegular;
    }

    public void setSecondHourRegular(double secondHourRegular) {
        this.secondHourRegular = secondHourRegular;
    }

    public double getFirstHourVip() {
        return firstHourVip;
    }

    public void setFirstHourVip(double firstHourVip) {
        this.firstHourVip = firstHourVip;
    }

    public double getSecondHourVip() {
        return secondHourVip;
    }

    public void setSecondHourVip(double secondHourVip) {
        this.secondHourVip = secondHourVip;
    }

    public double getMulNextHourRegular() {
        return mulNextHourRegular;
    }

    public void setMulNextHourRegular(double mulNextHourRegular) {
        this.mulNextHourRegular = mulNextHourRegular;
    }

    public double getMulNextHourVip() {
        return mulNextHourVip;
    }

    public void setMulNextHourVip(double mulNextHourVip) {
        this.mulNextHourVip = mulNextHourVip;
    }

    public Set<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(Set<Parking> parkings) {
        this.parkings = parkings;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
