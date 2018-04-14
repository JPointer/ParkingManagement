package model;


public class ParkingInfo {

    private int idParking;
    private String city;
    private String street;
    private int number;
    private int rate;
    private boolean occupied;

    public ParkingInfo(Parking parking, boolean occupied){
        this.idParking = parking.getIdParking();
        this.city = parking.getCity();
        this.street = parking.getStreet();
        this.number =  parking.getNumber();
        this.rate = parking.getRate().getIdRate();
        this.occupied = occupied;
    }

    public int getIdParking() { return idParking; }

    public boolean isOccupied() { return occupied; }

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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
