package mockObjects;

import dao.VisitDao;
import model.Visit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("mockVisitDao")
public class MockVisitDao implements VisitDao {

    private Set<Visit> visits = new HashSet<Visit>();

    @Override
    public boolean addVisit(Visit visit) {
        visits.add(visit);
        return true;
    }

    @Override
    public Visit getVisitById(String username, int idParking) {
        for(Visit v: visits)
            if(v.getUser().getUsername().equals(username) && v.getParking().getIdParking()==idParking)
                return v;
        return null;
    }

    @Override
    public boolean removeVisit(Visit visit) {
        return visits.remove(visit);
    }

    @Override
    public List<Visit> getVisitList() {
        return new ArrayList<Visit>(visits);
    }
}
