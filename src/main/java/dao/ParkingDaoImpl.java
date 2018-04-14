package dao;

import model.Parking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("parkingDaoImpl")
public class ParkingDaoImpl extends GenDao<Parking> implements ParkingDao {

    @Override
    public boolean addParking(Parking parking) {
        return addObject(parking);
    }

    @Override
    public Parking getParkingById(int idParking) {
        return getObjectById(idParking,"from Parking where idParking=?");
    }

    @Override
    public List<Parking> getPakingList() {
        return getObjectsList("from Parking");
    }

    @Override
    public void removeParking(Parking parking) {
        removeObject(parking);
    }
}
