package com.metro.service;

import com.metro.bean.MetroCard;

public interface MetroCardService {
	
	public MetroCard searchMetroCardById(int cardId);
	public int issueNewMetroCard(MetroCard card);
	public double checkCardBalance(int id);
	public int AddCardBalance(int id, double balance);
	public int RefundMetroCard(int cardId);
	
	
}
