package com.metro.persistence;

import com.metro.bean.MetroCard;

public interface MetroCardDao {
	public MetroCard searchCardById(int cardId);
	public int issueNewCard(MetroCard card);
	public double checkBalance(int id);
	public int AddBalance(int id, double balance);
	public int RefundCard(int cardId);
	
}
