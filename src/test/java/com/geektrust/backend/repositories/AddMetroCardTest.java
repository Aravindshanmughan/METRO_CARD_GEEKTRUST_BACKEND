package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.repository.CardRepository;
import com.geektrust.backend.repository.ICardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddMetroCardTest {

    private  ICardRepository cardRepository=new CardRepository();
  
    
    
    @Test
    public void testAddMetroCard() {
        MetroCard metroCard = new MetroCard("MC1", 101);
        cardRepository.saveCard("MC1", 101);
        Assertions.assertEquals(cardRepository.findAll().get(0).getBalance(), metroCard.getBalance());
        Assertions.assertEquals(cardRepository.findAll().get(0).getCardNo(), metroCard.getCardNo());
    }
    
}
