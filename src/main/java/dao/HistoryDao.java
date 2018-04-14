package dao;

import java.sql.Date;

public interface HistoryDao {

    void addBillToHistory(Date date, double price);

    double getMoneyForDay(Date date);

    void removeHistory(Date date);

}
