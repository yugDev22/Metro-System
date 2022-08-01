package com.metro.service;

import com.metro.bean.MetroCard;
import com.metro.persistence.MetroCardDao;
import com.metro.persistence.MetroCardDaoImpl;

public class MetroCardServiceImpl implements MetroCardService{

	private MetroCardDao metroCardDao = new MetroCardDaoImpl();

	@Override
	public MetroCard searchMetroCardById(int cardId) {
		
   return metroCardDao.searchCardById(cardId);
		
	}

	@Override
	public int issueNewMetroCard(MetroCard card) {
		return metroCardDao.issueNewCard(card);
		
		
	}

	@Override
	public double checkCardBalance(int id) {
		
		return metroCardDao.checkBalance(id);
	}

	@Override
	public int AddCardBalance(int id, double balance) {
		return metroCardDao.AddBalance(id, balance);
		
		
	}

	@Override
	public int RefundMetroCard(int cardId) {
		return metroCardDao.RefundCard(cardId);
	
		
	}

}
