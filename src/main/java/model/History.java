package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import java.sql.Date;

@Entity
@Table(name="history", schema="ParkingManager")
public class History implements Serializable{

    @Id
    @Column(name="date", unique = true, nullable = false)
    private Date date;

    @Column(name="daily", nullable = false)
    private double daily;

    public History(Date date, double daily) {
        this.date = date;
        this.daily = daily;
    }

    public History() {}

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public double getDaily() { return daily; }

    public void setDaily(double daily) { this.daily = daily; }
}
