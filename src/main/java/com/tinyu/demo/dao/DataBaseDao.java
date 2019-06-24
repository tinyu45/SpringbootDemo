package com.tinyu.demo.dao;

import java.sql.SQLException;

import org.json.JSONArray;

public class DataBaseDao {

	public static JSONArray showUser() throws SQLException {
		String sql="select * from user";
		return AccessDataBase.executeQuery(sql);
	}

	
}
