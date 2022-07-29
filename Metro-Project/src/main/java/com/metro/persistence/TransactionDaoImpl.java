package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.metro.bean.Transaction;

public class TransactionDaoImpl implements TransactionDao {

	@Override
	public ArrayList<Transaction> getTransactionsByCardId(int cardId) {
		ArrayList<Transaction> transactionArray = new ArrayList<Transaction>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Metrosystem", "root",
				"wileyc256");
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
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Metrosystem","root","wileyc256");
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO transaction VALUES(?,?,?,?,?,?,?)");) {
			preparedStatement.setString(1, transaction.getTransactionId());
			preparedStatement.setInt(2, transaction.getCardId());
			preparedStatement.setInt(3, transaction.getBoardingStationId());
			preparedStatement.setInt(4, transaction.getDestinationStationId());
			preparedStatement.setDouble(5, transaction.getFare());
			preparedStatement.setObject(6, transaction.getSwipeInTime());
			preparedStatement.setObject(7, transaction.getSwipeOutTime());

			rows = preparedStatement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

}
