package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.metro.bean.MetroCard;
import com.metro.bean.Passenger;
import com.metro.db.DatabaseCredentials;

public class MetroCardDaoImpl implements MetroCardDao {
	
	private static String URL = "jdbc:mysql://127.0.0.1:3306/metrosystem";
	private static String USER = "root";
	private static String PWD = "wiley";

	@Override
	public MetroCard searchCardById(Integer cardId) {

		MetroCard metroCard = null;
		try (Connection connection = DriverManager.getConnection(URL, USER,
				PWD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM card where cardID=?");) {

			preparedStatement.setInt(1, cardId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("cardId");
				double balance = resultSet.getDouble("balance");
				int passengerId = resultSet.getInt("passengerId");

				metroCard = new MetroCard(id, passengerId, balance);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return metroCard;

	}

	@Override
	public MetroCard issueNewCard(MetroCard card) {
		int rows = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER,
				PWD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO  card(cardId,balance,passengerId) values(?,?,?)");) {

			preparedStatement.setInt(1, card.getCardId());
			preparedStatement.setDouble(2, card.getBalance());
			preparedStatement.setInt(3, card.getPassengerId());

			rows = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (rows > 0) {
			return card;
		}
		return null;

	}

	@Override
	public Double checkBalance(Integer id) {
		double res = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER,
				PWD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT balance FROM card where cardID=?");) {

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				double balance = resultSet.getDouble("balance");
				res = balance;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;

	}

	@Override
	public Integer AddBalance(Integer id, Double balance) {
		int rows = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER,
				PWD);
				PreparedStatement preparedStatement1 = connection.prepareStatement("update card set balance=balance+? where cardId=?");) {

			preparedStatement1.setDouble(1, balance);
			preparedStatement1.setInt(2, id);

			rows = preparedStatement1.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;

	}

	@Override
	public MetroCard RefundCard(Integer cardId) {

		MetroCard card2 = searchCardById(cardId);

		if (card2 == null)
			return null;

		int rows = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER,
				PWD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("delete from  card where cardId=?");) {

			preparedStatement.setInt(1, cardId);

			rows = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rows>0) {
			return card2;
		}
		return null;

	}

	@Override
	public MetroCard getLastCard() {
		MetroCard card = null;

		try (Connection connection = DriverManager.getConnection(URL,USER,
				PWD); Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM card ORDER BY cardId DESC LIMIT 1");
			if (resultSet.next()) {
				Integer cardId = resultSet.getInt("cardId");
				Double balance = resultSet.getDouble("balance");
				Integer passengerId = resultSet.getInt("passengerId");
				card = new MetroCard(cardId, passengerId, balance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return card;
	}

	@Override
	public ArrayList<MetroCard> getAllMetroCards(Integer passengerId) {
		ArrayList<MetroCard> cardArr = new ArrayList<MetroCard>();
		try (Connection connection = DriverManager.getConnection(URL, USER,
				PWD); PreparedStatement statement = connection.prepareStatement("SELECT * FROM card WHERE passengerId=?");) {
			statement.setInt(1,passengerId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Integer cardId = resultSet.getInt("cardId");
				Double balance = resultSet.getDouble("balance");
				Integer passId = resultSet.getInt("passengerId");
				MetroCard card = new MetroCard(cardId, passId, balance);
				cardArr.add(card);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cardArr;
	}

	@Override
	public int deductBalance(Integer cardId, double fare) {
		int rows = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER,
				PWD);
				PreparedStatement preparedStatement1 = connection.prepareStatement("update card set balance=balance-? where cardId=?");) {

			preparedStatement1.setDouble(1, fare);
			preparedStatement1.setInt(2, cardId);

			rows = preparedStatement1.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rows;

	}

}
