package com.geektrust.backend.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.exceptions.CardNotFoundException;

public class CardRepository implements ICardRepository  {
    
    private final Map<String, MetroCard> metroCardMap=new HashMap<>();    
  
    @Override
    public void saveCard(String cardId, int balance) {
        // TODO Auto-generated method stub
        MetroCard metroCard = new MetroCard(cardId, balance);
        metroCardMap.put(cardId, metroCard);
        
    }
 

   

    @Override
    public Optional<MetroCard> findbyId(String cardId) throws CardNotFoundException {
        // TODO Auto-generated method stub
        MetroCard card = metroCardMap.get(cardId);
        return Optional.ofNullable(card);
        
    }




    @Override
    public List<MetroCard> findAll() {
        // TODO Auto-generated method stub
        List<MetroCard> list = new ArrayList<>();
        for (Map.Entry<String, MetroCard> entry : metroCardMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
        
    }




    @Override
    public void updateCardBalance(String cardId,int balance) {
        // TODO Auto-generated method stub
        MetroCard metroCard=metroCardMap.get(cardId);
        metroCard.setBalance(balance);
        metroCardMap.put(cardId, metroCard);
        

        
    }
   

   

    
 
}
