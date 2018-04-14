package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user", schema="ParkingManager")
public class User implements Serializable {
    @Id
    @Column(name="username", unique = true,
            nullable = false, length = 45)
    private String username;

    @Column(name= "password", nullable = false, length = 60)
    private String password;

    @Column(name= "enabled", nullable = false)
    private boolean enabled;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="secondName", nullable = false)
    private String secondName;

    @Column(name="bill", nullable = false)
    private double bill;

    @OneToMany(mappedBy="user")
    private Set<UserRole> userRole = new HashSet<UserRole>();

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private Set<Visit> visits = new HashSet<Visit>();

    public User(){}

    public User(String username, String password, boolean enabled, String firstName,
                String secondName, double bill){
        this.username= username;
        this.password= password;
        this.enabled= enabled;
        this.firstName=firstName;
        this.secondName=secondName;
        this.bill=bill;
    }

    public User(String username, String password, boolean enabled, String firstName,
                String secondName, double bill, Set<UserRole> userRole){
        this.username=username;
        this.password=password;
        this.enabled=enabled;
        this.firstName=firstName;
        this.secondName=secondName;
        this.bill=bill;
        this.userRole=userRole;
    }

    public User(String username, String password, boolean enabled, String firstName,
                String secondName, double bill, Set<UserRole> userRole, Set<Visit> visits){
        this.username=username;
        this.password=password;
        this.enabled=enabled;
        this.firstName=firstName;
        this.secondName=secondName;
        this.bill=bill;
        this.userRole=userRole;
        this.visits=visits;

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

}
