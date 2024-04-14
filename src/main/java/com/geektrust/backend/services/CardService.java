package com.geektrust.backend.services;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.exceptions.CardNotFoundException;
import com.geektrust.backend.repository.ICardRepository;

public class CardService implements ICardService {

    private ICardRepository cardRepository;
    
   
    
    public CardService(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }



    @Override
    public int transactMetroCard(String cardId, int amount) {
        // TODO Auto-generated method stub
        MetroCard metroCard = cardRepository.findbyId(cardId).orElseThrow(()->new CardNotFoundException("Card Not Found"));
        int balance = metroCard.getBalance();
        int diff = balance - amount;
        if(diff<0){
        cardRepository.updateCardBalance(cardId, 0);
        return Math.abs(balance - amount);
        }else
        cardRepository.updateCardBalance(cardId, diff);
        return 0;
    }

    

  

}
        
    


    
    

