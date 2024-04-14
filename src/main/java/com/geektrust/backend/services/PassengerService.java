package com.geektrust.backend.services;

import java.util.HashMap;
import java.util.Map;
import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.repository.IPassengerRepository;


public class PassengerService implements IPassengerService {






private static final double SERVICE_FEE_PERCENTAGE = 0.02;
private ICardService cardService;

private IPassengerRepository passengerRepository;

Map<String, Integer> amountCheckMap;
Map<String, Integer> discountCheckMap;
Map<Station, Map<PassengerType, Integer>> stationTypeCountMap;



public PassengerService(ICardService cardService,IPassengerRepository passengerRepository) {
    this.cardService=cardService;
    this.passengerRepository=passengerRepository;
    amountCheckMap = new HashMap<>();
    discountCheckMap = new HashMap<>();
    stationTypeCountMap = new HashMap<>();
   
    initializeStationMaps();
}

private void initializeStationMaps() {
    for (Station station : Station.values()) {
        amountCheckMap.put(station.name(),0);
        discountCheckMap.put(station.name(), 0);
        stationTypeCountMap.put(station, new HashMap<>());
    }
}

   
   






    @Override
    public void passengerCheckIn(Passenger passenger) {
        // TODO Auto-generated method stub
        String cardId = passenger.getCardNo();
       
        if(passengerRepository.checkPassenger(cardId)){
            int amount=calculateDiscountedRate(passenger);

            int collection = amountCheckMap.get(passenger.getFromStation().name()) + amount;
            int discount = discountCheckMap.get(passenger.getFromStation().name()) + amount;
            discountCheckMap.put(passenger.getFromStation().name(), discount);

            int remaining = cardService.transactMetroCard(cardId,amount);//checking if balance available for transaction
            if(amount!=0){
            collection+= remaining * SERVICE_FEE_PERCENTAGE;//0.02 pecenttransaction fees for loading amount to card
            }

            amountCheckMap.put(passenger.getFromStation().name(), collection);
            passengerRepository.removePassenger(cardId);
        }else{
        int amount = getPassengerTypeFare(passenger.getPassengerType());
        int collection = amountCheckMap.get(passenger.getFromStation().name()) + amount;

        int remaining = cardService.transactMetroCard(cardId, amount);
            if (amount != 0) {
                collection += remaining * SERVICE_FEE_PERCENTAGE;
            }
            amountCheckMap.put(passenger.getFromStation().name(), collection);
            passengerRepository.saveCard(cardId, passenger.getFromStation());
            
        }

        updatePassengerCount(stationTypeCountMap.get(passenger.getFromStation()), passenger.getPassengerType(), passenger.getFromStation());
    }

    private void updatePassengerCount(Map<PassengerType, Integer> tempMap, PassengerType passengerType, Station station) {
        if (tempMap.containsKey(passengerType)) {
            int val = tempMap.get(passengerType) + 1;
            tempMap.put(passengerType, val);
        } else {
            tempMap.put(passengerType, 1);
        }
        stationTypeCountMap.put(station, tempMap);
    }






    @Override
    public Map<Station, Map<PassengerType, Integer>> stationTypeCountMap() {
        // TODO Auto-generated method stub
        return this.stationTypeCountMap;
    }






    @Override
    public Map<String, Integer> amountCheckMap() {
        // TODO Auto-generated method stub
        return this.amountCheckMap;
    }






    @Override
    public Map<String, Integer> discountCheckMap() {
        // TODO Auto-generated method stub
        return this.discountCheckMap;
    }


  
    private int getPassengerTypeFare(PassengerType passengerType) {
        switch (passengerType) {
            case ADULT:
                return 200; // Fare for adult passenger
            case SENIOR_CITIZEN:
                return 100; // Fare for senior citizen passenger
            case KID:
                return 50;  // Fare for kid passenger
            default:
                return 0;   // Default fare (if passenger type not recognized)
        }
    }


    private int calculateDiscountedRate(Passenger passenger){

       PassengerType passengerType= passenger.getPassengerType();
       int passengerFare=getPassengerTypeFare(passengerType);
        double amount = passengerFare*0.5;
        return (int) amount;
    }
    
   

    
}
