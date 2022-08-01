package com.metro.service;

import java.util.ArrayList;

import com.metro.bean.Transaction;
import com.metro.persistence.TransactionDao;
import com.metro.persistence.TransactionDaoImpl;

public class TransactionServiceImpl implements TransactionService {

	TransactionDao transactionDao = new TransactionDaoImpl();
	@Override
	public ArrayList<Transaction> getAllTransactionsByCardId(int cardId) {
		return transactionDao.getTransactionsByCardId(cardId);
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
		return transactionDao.addTransaction(transaction);
	}

}
