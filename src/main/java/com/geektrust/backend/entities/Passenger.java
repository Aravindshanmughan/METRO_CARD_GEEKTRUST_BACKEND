package com.geektrust.backend.entities;

public class Passenger {

    private PassengerType passengerType;

    private String cardNo;

    private Station fromStation;

    public Passenger(String cardNo,  String passengerType, String fromStation) {
        this.passengerType =  PassengerType.valueOf(passengerType);
        this.cardNo = cardNo;
        this.fromStation = Station.valueOf(fromStation);
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public Station getFromStation() {
        return fromStation;
    }




   
    }

    
    
    

