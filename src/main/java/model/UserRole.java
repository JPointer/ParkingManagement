package model;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="role", schema="ParkingManager")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    private Integer userRoleId;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name= "role", nullable= false, length = 45)
    private String role;

    public UserRole(){}

    public UserRole(User user, String role){
        this.user=user;
        this.role = role;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
