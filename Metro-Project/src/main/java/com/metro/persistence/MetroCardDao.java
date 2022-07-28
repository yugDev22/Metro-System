package com.metro.persistence;

import com.metro.bean.MetroCard;

public interface MetroCardDao {
	
	public MetroCard searchCardById(int cardId);
	public void issueNewCard(int id,int passengerId,double balance);
	public double checkBalance(int id);
	public void updateBalance(int id, double balance);
	public void deleteCard(int cardId);
	
}
