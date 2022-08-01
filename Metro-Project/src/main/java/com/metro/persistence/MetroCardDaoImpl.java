package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.metro.bean.MetroCard;
import com.metro.bean.Passenger;

public class MetroCardDaoImpl implements MetroCardDao {

	@Override
	public MetroCard searchCardById(int cardId) {
		
		MetroCard metroCard = null;
		Passenger passenger = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/metrosystem", "root",
				"wiley");
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM card where cardID=?");) {

			
			
			preparedStatement.setInt(1, cardId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("cardId");
				double balance = resultSet.getDouble("balance");
				int passengerId = resultSet.getInt("passengerId");
				

				metroCard = new MetroCard(id,passengerId, balance);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return metroCard;
		
		
	}

	@Override
	public int issueNewCard(MetroCard card) {
		
	   MetroCard card1= searchCardById(card.getCardId());
	    
	    if(card1!=null)
	    	return 0;
	    
	    
		
		int rows = 0;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/metrosystem", "root",
				"wiley");
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO  card(cardId,passengerId) values(?,?)");) {

			preparedStatement.setInt(1 , card.getCardId());
			preparedStatement.setInt(2 , card.getPassengerId());
		

			rows = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rows;
		
	}

	@Override
	public double checkBalance(int id) {
		
		   MetroCard card1= searchCardById(id);
		   if(card1==null)
		    	return -1;
		
		double res=0;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/metrosystem", "root",
				"wiley");
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
	public int AddBalance(int id, double balance) {
		
//		 MetroCard card1= searchCardById(id);
//		   if(card1==null)
//		    	return -1;
		
		int rows = 0;
		double res = 0;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/metrosystem", "root",
				"wiley");
				
				PreparedStatement preparedStatement1 = connection
						.prepareStatement("SELECT balance FROM card where cardID=?");) {

			
			
			preparedStatement1.setInt(1, id);

			ResultSet resultSet = preparedStatement1.executeQuery();

			if (resultSet.next()) {
			
				double balance1 = resultSet.getDouble("balance");
				
				

				res = balance1;
			}
			
				
				PreparedStatement preparedStatement2 = connection
						.prepareStatement("update card set balance = ? where cardid= ?"); {

		
			
			preparedStatement2.setDouble(1 , (balance+res));
			preparedStatement2.setInt(2 , id);
		

			rows = preparedStatement2.executeUpdate();
						}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rows;
		
		
	}

	@Override
	public int RefundCard(int cardId) {
		
       MetroCard card2= searchCardById(cardId);
	    
	    if(card2==null)
	    	return 0;
		

		int rows = 0;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/metrosystem", "root",
				"wiley");
				PreparedStatement preparedStatement = connection
						.prepareStatement("delete from  card where cardId=?");) {

			preparedStatement.setInt(1 , cardId);
		
		

			rows = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rows;
		
		
	}


}
