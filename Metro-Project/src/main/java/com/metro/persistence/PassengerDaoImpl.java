package com.metro.persistence;

import com.metro.bean.MetroCard;
import com.metro.bean.Passenger;
import com.metro.db.DatabaseCredentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PassengerDaoImpl implements PassengerDao{
	
	private static String URL = "jdbc:mysql://127.0.0.1:3306/metrosystem";
	private static String USER = "root";
	private static String PWD = "wiley";

	@Override
	public Passenger searchPassenger(Integer id) {
		Passenger passenger=null;
		try(Connection connection=DriverManager.getConnection(URL, USER,
				PWD);
				PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM PASSENGER WHERE PASSENGERID = ?");){
			
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int pid=resultSet.getInt("passengerid");
				String pname=resultSet.getString("passengername");
				String phoneNo=resultSet.getString("passengerphone");
				String mail=resultSet.getString("passengeremail");
				int age=resultSet.getInt("passengerage");
				
				passenger= new Passenger(pid,pname,phoneNo,mail,age);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passenger;
	}

	@Override
	public Passenger addPassenger(Passenger passenger) {
		int rows = 0;
		try (Connection connection = DriverManager.getConnection(URL, USER,
				PWD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO PASSENGER values(?,?,?,?,?)");) {

			preparedStatement.setInt(1, passenger.getPassengerId());
			preparedStatement.setString(2,passenger.getPassengerName());
			preparedStatement.setString(3,passenger.getPassengerPhoneNumber());
			preparedStatement.setString(4,passenger.getPassengerEmail());
			preparedStatement.setInt(5,passenger.getAge());
			

			rows = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rows>0) {
			return passenger;
		}
		return null;
	}

	@Override
	public Passenger updatePassengerDetails(Passenger passenger) {
		int rows = 0;
		try (Connection connection = DriverManager.getConnection(URL,USER,
				PWD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE PASSENGER SET PASSENGERNAME=?,PASSENGERPHONE=?,PASSENGEREMAIL=?,PASSENGERAGE=? WHERE PASSENGERID=?");) {

			
			preparedStatement.setString(1,passenger.getPassengerName());
			preparedStatement.setString(2,passenger.getPassengerPhoneNumber());
			preparedStatement.setString(3,passenger.getPassengerEmail());
			preparedStatement.setInt(4,passenger.getAge());
			preparedStatement.setInt(5, passenger.getPassengerId());

			rows = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(rows>0) {
			return passenger;
		}
		return null;

	}

	@Override
	public Passenger getLastPassenger() {
		Passenger passenger=null;
		try (Connection connection = DriverManager.getConnection(URL, USER,
				PWD); Statement statement = connection.createStatement();) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM card ORDER BY cardId DESC LIMIT 1");
			if (resultSet.next()) {
				Integer pid = resultSet.getInt("passengerId");
				String pname = resultSet.getString("passengerName");
				String phone  = resultSet.getString("passengerPhoneNumber");
				String email  = resultSet.getString("passengerEmail");
				Integer page = resultSet.getInt("passengerAge");
				passenger = new Passenger(pid, pname, phone, email, page);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passenger;
	}



}
