package com.geektrust.backend.services;

import com.geektrust.backend.repository.CardRepository;
import com.geektrust.backend.repository.ICardRepository;
import com.geektrust.backend.repository.IPassengerRepository;
import com.geektrust.backend.repository.PassengerRepository;
import org.junit.jupiter.api.Test;

public class PrintSummaryTest {
    
    private  ICardRepository cardRepository=new CardRepository();
    private  IPassengerRepository passengerRepository=new PassengerRepository();
    private   ICardService cardService = new CardService(cardRepository);
    private  IPassengerService passengerService = new PassengerService(cardService, passengerRepository);
    private IPrintSummary printSummaryService=new PrintSummary(passengerService);

    @Test
    public void testPrintSummaryCall () {
        printSummaryService.printSummary();
    }

}
