package com.geektrust.backend.services;

import com.geektrust.backend.entities.Passenger;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.Station;
import com.geektrust.backend.repository.CardRepository;
import com.geektrust.backend.repository.ICardRepository;
import com.geektrust.backend.repository.IPassengerRepository;
import com.geektrust.backend.repository.PassengerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PassengerCheckInTest {
    private  ICardRepository cardRepository=new CardRepository();
    private  IPassengerRepository passengerRepository=new PassengerRepository();
    private   ICardService cardService = new CardService(cardRepository);
    private  IPassengerService passengerService = new PassengerService(cardService, passengerRepository);

    @BeforeAll
    public void setup() {
        cardRepository.saveCard("MC1", 101);
        cardRepository.saveCard("MC2", 501);
        cardRepository.saveCard("MC3", 1001);
    }

   
    @Test
    public void testAdultCheckIn() {
        Passenger passenger = new Passenger("MC3", PassengerType.ADULT.name(), Station.CENTRAL.name());
        passengerService.passengerCheckIn(passenger);
        Assertions.assertNotEquals(0, passengerService.amountCheckMap().get(Station.CENTRAL.name()));
    }

    
    @Test
    public void testSeniorCheckIn() {
        Passenger passenger = new Passenger("MC2", PassengerType.SENIOR_CITIZEN.name(), Station.CENTRAL.name());
        passengerService.passengerCheckIn(passenger);
        Assertions.assertNotEquals(0, passengerService.amountCheckMap().get(Station.CENTRAL.name()));
    }

    @Test
    public void testKidCheckIn() {
        Passenger passenger = new Passenger("MC1", PassengerType.KID.name(), Station.CENTRAL.name());
        passengerService.passengerCheckIn(passenger);
        Assertions.assertNotEquals(0, passengerService.amountCheckMap().get(Station.CENTRAL.name()));
    }

   
    @Test
    public void testAdultCheckInReturn() {
        Passenger passenger = new Passenger("MC2", PassengerType.KID.name(), Station.CENTRAL.name());
        passengerService.passengerCheckIn(passenger);
      Assertions.assertEquals(0, passengerService.discountCheckMap().get(Station.AIRPORT.name()));
        Assertions.assertNotEquals(0, passengerService.amountCheckMap().get(Station.CENTRAL.name()));
    }
}
