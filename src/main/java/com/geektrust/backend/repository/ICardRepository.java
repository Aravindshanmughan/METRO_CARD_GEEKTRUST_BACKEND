package com.geektrust.backend.repository;

import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.MetroCard;


public interface ICardRepository  {

    Optional<MetroCard> findbyId(String cardNo);
    void saveCard(String cardId,int balance);
    List<MetroCard> findAll();
    void updateCardBalance(String cardId,int balance);
    


    
}
