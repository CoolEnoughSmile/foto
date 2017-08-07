package com.coolenoughsmile.foto.liftActivity.model;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by CoolEnoughSmile on 2017/8/2.
 */

public class Order extends BmobObject implements Serializable {
    String departure;
    String destination;
    String appointment_time;
    String type;
    String phone;
    String remarke;
    String status;
    String objId;
    String username;
    String logo;

    public Order() {
    }

    public Order(String objId,String logo,String username,String departure, String destination, String appointment_time, String type, String phone, String remarke,String status) {
        this.departure = departure;
        this.destination = destination;
        this.appointment_time = appointment_time;
        this.type = type;
        this.phone = phone;
        this.remarke = remarke;
        this.status=status;
        this.objId=objId;
        this.username=username;
        this.logo=logo;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemarke() {
        return remarke;
    }

    public void setRemarke(String remarke) {
        this.remarke = remarke;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
