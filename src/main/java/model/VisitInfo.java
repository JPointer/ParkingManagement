package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitInfo {
    private String username;
    private String city;
    private String street;
    private int number;
    private String begin;

    public VisitInfo(Visit visit){
        this.username = visit.getUser().getUsername();
        Parking parking = visit.getParking();
        this.city = parking.getCity();
        this.street = parking.getStreet();
        this.number = parking.getIdParking();

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        this.begin = sdf.format(visit.getBeginTime());
        System.out.println(visit.getBeginTime());
        System.out.println(begin);
    }

    public String getUsername() {
        return username;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getBegin() {
        return begin;
    }
}
