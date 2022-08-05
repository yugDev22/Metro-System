package com.metro.persistence;

import java.util.ArrayList;

import com.metro.bean.MetroCard;

public interface MetroCardDao {
	public MetroCard searchCardById(Integer cardId);
	public MetroCard issueNewCard(MetroCard card);
	public Double checkBalance(Integer id);
	public Integer AddBalance(Integer id, Double balance);
	public MetroCard RefundCard(Integer cardId);
	public MetroCard getLastCard();
	public ArrayList<MetroCard> getAllMetroCards(Integer passengerId);
	public int deductBalance(Integer cardId, double fare);
}
