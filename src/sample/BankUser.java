package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

//Property class
public class BankUser{

    public SimpleIntegerProperty userId = new SimpleIntegerProperty();
    public SimpleStringProperty userName = new SimpleStringProperty();
    public SimpleStringProperty userSurname = new SimpleStringProperty();
    public SimpleStringProperty userCity = new SimpleStringProperty();
    public SimpleStringProperty userStreet = new SimpleStringProperty();
    public SimpleStringProperty userHomenumber = new SimpleStringProperty();
    public SimpleLongProperty userPESEL = new SimpleLongProperty();
    public SimpleStringProperty userPhone = new SimpleStringProperty();
    public SimpleIntegerProperty userFunds = new SimpleIntegerProperty();

    public Integer getUserId() {
        return userId.get();
    }

    public String getUserName() {
        return userName.get();
    }

    public String getUserSurname() {
        return userSurname.get();
    }

    public String getUserCity() {
        return userCity.get();
    }

    public String getUserStreet() {
        return userStreet.get();
    }

    public String getUserHomenumber() {
        return userHomenumber.get();
    }

    public Long getUserPESEL() {
        return userPESEL.get();
    }

    public String getUserPhone() {
        return userPhone.get();
    }

    public Integer getUserFunds() {
        return userFunds.get();
    }
}
