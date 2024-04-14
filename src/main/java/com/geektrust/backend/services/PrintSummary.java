package com.geektrust.backend.services;

import java.util.Map;
import java.util.PriorityQueue;
import com.geektrust.backend.dtos.PassengerCount;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.Station;

public class PrintSummary implements IPrintSummary {

    IPassengerService passengerService;

    

    public PrintSummary(IPassengerService passengerService) {
        this.passengerService = passengerService;
    }



    @Override
    public void printSummary() {
        // TODO Auto-generated method stub

        Map<String, Integer> amountCheckMap = passengerService.amountCheckMap();
        Map<String, Integer> discountCheckMap = passengerService.discountCheckMap();
        Map<Station, Map<PassengerType, Integer>> stationTypeCountMap = passengerService.stationTypeCountMap();

       
        for (Station station : Station.values()) {
            System.out.println("TOTAL_COLLECTION " + station.name() + " " + amountCheckMap.get(station.name()) + " " + discountCheckMap.get(station.name()));
            System.out.println("PASSENGER_TYPE_SUMMARY");
            PriorityQueue<PassengerCount> sortedCount = convertToQueue(stationTypeCountMap.get(station));
            while (!sortedCount.isEmpty()) {
                PassengerCount passengerCount = sortedCount.poll();
                System.out.println(passengerCount.getPassengerType().name() + " " + passengerCount.getCount());
            }
        }
    }

    private PriorityQueue<PassengerCount> convertToQueue(Map<PassengerType, Integer> map) {
        PriorityQueue<PassengerCount> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<PassengerType, Integer> entry : map.entrySet()) {
            priorityQueue.add(new PassengerCount(entry.getKey(), entry.getValue()));
        }
        return priorityQueue;
    }
     
        
    
}
    

