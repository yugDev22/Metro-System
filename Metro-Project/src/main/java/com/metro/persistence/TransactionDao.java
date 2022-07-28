package com.metro.persistence;

import java.util.ArrayList;

import com.metro.bean.Transaction;

public interface TransactionDao {
	
	public ArrayList<Transaction> getTransactionsByCardId(int cardId);
	public int addTransaction(Transaction transaction);
}
