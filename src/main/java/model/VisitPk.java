package model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VisitPk implements Serializable{


    private User user;
    private Parking parking;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitPk that = (VisitPk) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (parking != null ? !parking.equals(that.parking) : that.parking != null)
            return false;

        return true;
    }
    public int hashCode(){
        int result;
        result = (user!=null? user.hashCode():0);
        result = 31 * result + (parking!=null? parking.hashCode():0);
        return result;
    }
}
