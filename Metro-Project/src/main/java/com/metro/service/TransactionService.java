package com.metro.service;

import java.util.ArrayList;

import com.metro.bean.Transaction;

public interface TransactionService {

	public ArrayList<Transaction> getAllTransactionsByCardId(int cardId);
	public int addNewTransaction(Transaction transaction);
}
