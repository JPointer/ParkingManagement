package dao;

import model.Parking;

import java.util.List;

public interface ParkingDao {

    boolean addParking(Parking parking);

    Parking getParkingById(int idParking);

    List<Parking> getPakingList();

    void removeParking(Parking parking);
}
