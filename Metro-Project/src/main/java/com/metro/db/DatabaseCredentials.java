package com.metro.db;

public class DatabaseCredentials {
	private static String URL = "jdbc:mysql://localhost:3306/Metrosystem";
	private static String USER = "root";
	private static String PWD = "wileyc256";
	
	public static String getURL() {
		return URL;
	}
	public static String getUSER() {
		return USER;
	}
	public static String getPWD() {
		return PWD;
	}
	
}
