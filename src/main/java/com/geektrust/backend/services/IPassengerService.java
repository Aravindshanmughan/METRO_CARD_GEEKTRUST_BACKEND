package com.geektrust.backend.services;

import java.util.Map;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.Station;

public interface IPassengerService {
    
 
    void passengerCheckIn(Passenger passenger);

    Map<Station, Map<PassengerType, Integer>> stationTypeCountMap();

    Map<String, Integer> amountCheckMap();

    Map<String, Integer> discountCheckMap();

   
    
}
