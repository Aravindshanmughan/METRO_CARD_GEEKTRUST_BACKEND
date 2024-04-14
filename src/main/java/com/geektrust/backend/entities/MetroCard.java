package com.geektrust.backend.entities;

public class MetroCard {

   private final String cardNo;
   
    private  int balance;



public MetroCard(String cardNo, int balance) {
    this.cardNo = cardNo;
    this.balance = balance;
}

public String getCardNo() {
    return cardNo;
}

public int getBalance() {
    return balance;
}

public void setBalance(int balance) {
    this.balance = balance;
}






}



    

