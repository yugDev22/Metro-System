package com.metro.service;

import java.util.ArrayList;

import com.metro.bean.MetroCard;
import com.metro.bean.Transaction;
import com.metro.persistence.TransactionDao;
import com.metro.persistence.TransactionDaoImpl;

public class TransactionServiceImpl implements TransactionService {

	TransactionDao transactionDao = new TransactionDaoImpl();
	MetroCardService metroCardService = new MetroCardServiceImpl();
	@Override
	public ArrayList<Transaction> getAllTransactionsByCardId(int cardId) {
		if(metroCardService.searchMetroCardById(cardId)!=null) {
			return transactionDao.getTransactionsByCardId(cardId);
		}
		return null;
	}

	@Override
	public int addNewTransaction(Transaction transaction) {
		Transaction lastTran = transactionDao.getLastTransaction();
		String tid = "t0";
		if(lastTran!=null) {
			String lastId=lastTran.getTransactionId().split("t")[1];
			tid = "t"+Integer.toString(Integer.parseInt(lastId)+1);
		}
		transaction.setTransactionId(tid);
		if(transaction.getDestinationStationId()==null) {
			transaction.setDestinationStationId(0);
		}
		
		return transactionDao.addTransaction(transaction);
	}

	@Override
	public Transaction alreadySwipedIn(Integer cardId) {
		if(metroCardService.searchMetroCardById(cardId)!=null) {
			return transactionDao.alreadySwipedIn(cardId);
		}
		return null;
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		MetroCard card = metroCardService.searchMetroCardById(transaction.getCardId());
		int sourceStation = transaction.getBoardingStationId();
		int destStation = transaction.getDestinationStationId();
		double fare = Math.abs(destStation-sourceStation)*5;
		transaction.setFare(fare);
		if(fare>card.getBalance()){
			return null;
		}
		metroCardService.deductBalance(card.getCardId(), fare);
		return transactionDao.updateTransaction(transaction);
	}

}
