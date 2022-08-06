package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.metro.bean.Transaction;
import com.metro.db.DatabaseCredentials;

public class TransactionDaoImpl implements TransactionDao {

	private static String URL = DatabaseCredentials.getURL();
	private static String USER = DatabaseCredentials.getUSER();
	private static String PWD = DatabaseCredentials.getPWD();
	@Override
	public ArrayList<Transaction> getTransactionsByCardId(int cardId) {
		ArrayList<Transaction> transactionArray = new ArrayList<Transaction>();
		try (Connection connection = DriverManager.getConnection(URL,USER,
				PWD);
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM transaction WHERE cardID=?");) {
			statement.setInt(1, cardId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				String transactionId = resultSet.getString("transactionId");
				int boardingStationId = resultSet.getInt("boardingStationId");
				int destinationStationId = resultSet.getInt("destinationStationId");
				double fare = resultSet.getDouble("fare");
				int mcardId = resultSet.getInt("cardId");
				LocalDateTime swipeInDateTime = (LocalDateTime) resultSet.getObject("swipeInDatetime");
				LocalDateTime swipeOutDateTime = (LocalDateTime) resultSet.getObject("swipeOutDatetime");
				Transaction transaction = new Transaction(transactionId, mcardId, boardingStationId, destinationStationId, fare, swipeInDateTime, swipeOutDateTime);
				transactionArray.add(transaction);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionArray;
	}

	@Override
	public int addTransaction(Transaction transaction) {
		int rows=0;
		try (Connection connection = DriverManager.getConnection(URL,USER,PWD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO transaction VALUES(?,?,?,?,?,?,?)");) {
			preparedStatement.setString(1, transaction.getTransactionId());
			preparedStatement.setInt(2, transaction.getBoardingStationId());
			preparedStatement.setInt(3, transaction.getDestinationStationId());
			preparedStatement.setDouble(4, transaction.getFare());
			preparedStatement.setInt(5, transaction.getCardId());
			preparedStatement.setObject(6, transaction.getSwipeInTime());
			preparedStatement.setObject(7, transaction.getSwipeOutTime());

			rows = preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public Transaction getLastTransaction() {
		Transaction transaction=null;
		try (Connection connection = DriverManager.getConnection(URL,USER,PWD);
				Statement statement = connection
						.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM transaction ORDER BY transactionId DESC LIMIT 1");
			if(resultSet.next()) {
				String transactionId = resultSet.getString("transactionId");
				int boardingStationId = resultSet.getInt("boardingStationId");
				int destinationStationId = resultSet.getInt("destinationStationId");
				double fare = resultSet.getDouble("fare");
				int mcardId = resultSet.getInt("cardId");
				LocalDateTime swipeInDateTime = (LocalDateTime) resultSet.getObject("swipeInDatetime");
				LocalDateTime swipeOutDateTime = (LocalDateTime) resultSet.getObject("swipeOutDatetime");
				transaction = new Transaction(transactionId, mcardId, boardingStationId, destinationStationId, fare, swipeInDateTime, swipeOutDateTime);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return transaction;
	}

	@Override
	public Transaction alreadySwipedIn(Integer cardId) {
		Transaction swipedIn=null;
		try (Connection connection = DriverManager.getConnection(URL,USER,PWD);
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM transaction WHERE cardID=? AND destinationStationId=0");) {
			statement.setInt(1, cardId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String transactionId = resultSet.getString("transactionId");
				int boardingStationId = resultSet.getInt("boardingStationId");
				int destinationStationId = resultSet.getInt("destinationStationId");
				double fare = resultSet.getDouble("fare");
				int mcardId = resultSet.getInt("cardId");
				LocalDateTime swipeInDateTime = (LocalDateTime) resultSet.getObject("swipeInDatetime");
				LocalDateTime swipeOutDateTime = (LocalDateTime) resultSet.getObject("swipeOutDatetime");
				swipedIn = new Transaction(transactionId, mcardId, boardingStationId, destinationStationId, fare, swipeInDateTime, swipeOutDateTime);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return swipedIn;
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) {
		int rows=0;
		try (Connection connection = DriverManager.getConnection(URL,USER,PWD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE transaction SET destinationStationId=?,fare=?,swipeOutDatetime=? WHERE transactionId=?");) {
			
			preparedStatement.setInt(1, transaction.getDestinationStationId());
			preparedStatement.setDouble(2, transaction.getFare());
			preparedStatement.setObject(3, transaction.getSwipeOutTime());
			preparedStatement.setString(4, transaction.getTransactionId());

			rows = preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		if(rows>0) {
			return transaction;
		}
		return null;
	}

}
