package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="visit", schema="ParkingManager")
@IdClass(VisitPk.class)
public class Visit implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "idParking", referencedColumnName = "idParking")
    private Parking parking;

    @Column(name="beginTime", nullable = false)
    private Timestamp beginTime;

    public Visit(){}

    public Visit(User user, Parking parking, Timestamp beginTime){
        this.user = user;
        this.parking = parking;
        this.beginTime = beginTime;
    }
    public User getUser(){ return user;}

    public void setUser(User user){this.user=user;}

    public Parking getParking(){ return parking;}

    public void setParking(Parking parking){ this.parking=parking;}

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }
}
