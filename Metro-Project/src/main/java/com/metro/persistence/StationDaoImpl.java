package com.metro.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.metro.bean.Station;


public class StationDaoImpl implements StationDao {

	@Override
	public Station searchStationById(int stationId) {
		Station station = null;
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Metrosystem", "root",
				"wileyc256");
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM stations where stationId=?");) {

			preparedStatement.setInt(1, stationId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("stationId");
				String name = resultSet.getString("stationName");
				int prevStationId = resultSet.getInt("previousStationId");
				int nextStationId = resultSet.getInt("nextStationId");


				station = new Station(id, name, prevStationId, nextStationId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return station;
	}

	@Override
	public int addStation(Station station) {
		int rows = 0;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Metrosystem", "root",
					"wileyc256");
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO stations values(?,?,?,?)");

			preparedStatement.setInt(1, station.getStationId());
			preparedStatement.setString(2, station.getStationName());
			preparedStatement.setInt(3, station.getPrevStationId());
			preparedStatement.setInt(4, station.getNextStationId());
			
			if(station.getPrevStationId()!=-1)
			{
				PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE stations set nextStationId=? where stationId=?");
				preparedStatement2.setInt(1, station.getStationId());
				preparedStatement2.setInt(2, station.getPrevStationId());
				preparedStatement2.executeUpdate();
			}
			
			rows = preparedStatement.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return rows;
	}

	@Override
	public ArrayList<Station> getAllStations() 
	{
		ArrayList<Station> stationList = new ArrayList<Station>();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Metrosystem", "root",
				"wileyc256"); Statement statement = connection.createStatement();) {

			ResultSet resultSet = statement.executeQuery("SELECT * FROM stations");

			while (resultSet.next()) {
				int id = resultSet.getInt("stationId");
				String name = resultSet.getString("stationName");
				int prevStationId = resultSet.getInt("previousStationId");
				int nextStationId = resultSet.getInt("nextStationId");

				stationList.add(new Station(id, name, prevStationId, nextStationId));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stationList;
	}

}
