package dao;

import model.Visit;

import java.util.List;

public interface VisitDao {

    boolean addVisit(Visit visit);

    Visit getVisitById(String username, int idParking);

    boolean removeVisit(Visit visit);

    List<Visit> getVisitList();
}
