package com.metro.service;

import java.util.ArrayList;

import com.metro.bean.MetroCard;
import com.metro.persistence.MetroCardDao;
import com.metro.persistence.MetroCardDaoImpl;

public class MetroCardServiceImpl implements MetroCardService {

	private MetroCardDao metroCardDao = new MetroCardDaoImpl();

	@Override
	public MetroCard searchMetroCardById(Integer cardId) {
		return metroCardDao.searchCardById(cardId);

	}

	@Override
	public MetroCard issueNewMetroCard(MetroCard card) {
		if(searchMetroCardById(card.getCardId())==null) {
			if(card.getBalance()>0) {
				card.setCardId(getCardId());
				return metroCardDao.issueNewCard(card);
			}
		}
		return null;

	}

	@Override
	public Double checkCardBalance(Integer id) {
		if(searchMetroCardById(id)!=null) {
			return metroCardDao.checkBalance(id);
		}
		return null;
	}

	@Override
	public Integer AddCardBalance(Integer id, Double balance) {
		if(searchMetroCardById(id)!=null) {
			if(balance>0)
				return metroCardDao.AddBalance(id, balance);
		}
		return null;

	}

	@Override
	public MetroCard RefundMetroCard(Integer cardId) {
		if(searchMetroCardById(cardId)!=null) {
			return metroCardDao.RefundCard(cardId);
		}
		return null;

	}

	@Override
	public Integer getCardId() {
		MetroCard card = metroCardDao.getLastCard();
		if(card!=null) {
			return card.getCardId()+1;
		}
		return 1001;
	}

	@Override
	public ArrayList<MetroCard> getAllCards(Integer passengerId) {
		return metroCardDao.getAllMetroCards(passengerId);
	}

	@Override
	public int deductBalance(Integer cardId, double balance) {
		return metroCardDao.deductBalance(cardId, balance);
	}
	

}
