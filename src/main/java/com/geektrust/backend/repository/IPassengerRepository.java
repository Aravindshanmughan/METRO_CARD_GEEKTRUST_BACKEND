package com.geektrust.backend.repository;


import com.geektrust.backend.entities.Station;

public interface IPassengerRepository  {
  public void saveCard(String cardId,Station fromStation);   
 Station findById(String Id);
 boolean checkPassenger(String Id);
 void removePassenger(String Id);
}
