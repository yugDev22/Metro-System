package com.metro.service;

import com.metro.bean.MetroCard;

public interface MetroCardService {
	
	public MetroCard searchMetroCardById(int cardId);
	public void issueNewMetroCard(int id,int passengerId,double balance);
	public double checkCardBalance(int id);
	public void updateCardBalance(int id, double balance);
	public void deleteMetroCard(int cardId);
	
}
