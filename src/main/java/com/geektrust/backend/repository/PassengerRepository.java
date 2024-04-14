package com.geektrust.backend.repository;


import java.util.HashMap;
import java.util.Map;

import com.geektrust.backend.entities.Station;


public class PassengerRepository implements IPassengerRepository    {

    private final Map<String, Station> passengerTrackMap=new HashMap<>(); 

    @Override
    public Station findById(String Id) {
        // TODO Auto-generated method stub
        Station station=passengerTrackMap.get(Id);
        return station;
    }

    @Override
    public void saveCard(String cardId, Station fromStation) {
        // TODO Auto-generated method stub
        passengerTrackMap.put(cardId, fromStation);
        
    }

    @Override
    public boolean checkPassenger(String Id) {
        // TODO Auto-generated method stub
        if( passengerTrackMap.containsKey(Id)){
            return true;
        }else
       
        return false;
    }

    @Override
    public void removePassenger(String Id) {
        // TODO Auto-generated method stub
        passengerTrackMap.remove(Id);
        
    }
    
    

    
}
