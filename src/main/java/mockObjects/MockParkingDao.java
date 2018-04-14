package mockObjects;

import dao.ParkingDao;
import model.Parking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("mockParkingDao")
public class MockParkingDao implements ParkingDao {

    Set<Parking> parkings = new HashSet<Parking>();

    @Override
    public boolean addParking(Parking parking) {
        parkings.add(parking);
        return true;
    }

    @Override
    public Parking getParkingById(int idParking) {
        for(Parking p: parkings)
            if(p.getIdParking()==idParking)
                return p;
        return null;
    }

    @Override
    public List<Parking> getPakingList() {
        return new ArrayList<Parking>(parkings);
    }

    @Override
    public void removeParking(Parking parking) {
        parkings.remove(parking);
    }
}
