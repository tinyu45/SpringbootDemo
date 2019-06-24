package com.tinyu.demo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.stereotype.Component;
import com.zaxxer.hikari.HikariDataSource;
//实例化为spring对象
@Component 
@ConditionalOnProperty(value = "spring.datasource.enable", havingValue = "true", matchIfMissing = true) //
public class AccessDataBase {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseDriver.class);
	//数据源
	private static HikariDataSource ds;   
	@Autowired(required=false)//自动注入
	public AccessDataBase(HikariDataSource pool) {
		ds = pool;
	}
	
	
	//安全关闭结果集
		private static void safeClose(ResultSet rs) {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ex) {
				LOGGER.warn("Close ResultSet failed.", ex);
			}
		}

		//安全关闭预编译语句
		private static void safeClose(PreparedStatement stat) {
			try {
				if (stat != null)
					stat.close();
			} catch (Exception ex) {
				LOGGER.warn("Close ResultSet failed.", ex);
			}
		}

		//安全关闭数据库连接
		private static void safeClose(Connection conn) {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				LOGGER.warn("Close db connection failed.", ex);
			}
		}
		
		
		
		/**
		 * 遍历参数并拼接为字符串
		 * @param subSQL
		 * @param object
		 * @return
		 */
		private static StringBuffer foreachObject(String[] subSQL, Object... object) {
			StringBuffer returnSQL = new StringBuffer();
			for (int i=0; i < object.length; ++i) {
				if (object[i] instanceof String) {
					returnSQL.append(subSQL[i]).append("'").append(object[i]).append("'");
				} else {
					returnSQL.append(subSQL[i]).append(object[i]);
				}
			}

			return returnSQL;
		}
		
		
		/**
		 * 获得PreparedStatement向数据库提交的SQL语句
		 */
		private static String getPreparedSQL(String sql, Object ... objects) {
			if (objects==null || 1 > objects.length)
				return sql;
			String[] subSQL = sql.split("\\?");
			StringBuffer returnSQL = foreachObject(subSQL, objects);
			if (subSQL.length > objects.length) {
				returnSQL.append(subSQL[subSQL.length - 1]);
			}

			return returnSQL.toString();
		}
		
		
		
		/**
		 * 执行一个查询SQL语句
		 * @param sql	需要执行的SQL语句
		 * @param objects 传递的SQL条件
		 * @return 返回一个JSON数组
		 * @throws SQLException 
		 */
		public static JSONArray executeQuery(String sql, Object ... objects) throws SQLException {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(getPreparedSQL(sql, objects));
			}
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			// JSON数组
			JSONArray jsonArray=new JSONArray();
			try {
				conn = ds.getConnection();
				ps = conn.prepareStatement(sql);
				int i=1;
				for (Object obj: objects) {
					ps.setObject(i, obj);
					i++;
				}
				// 获取结果集
				rs = ps.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				//遍历ResultSet中的每条数据
				while (rs.next()) {
					JSONObject jsonObject = new JSONObject(); 
					for (int j = 1; j <=columnCount; j++) {
						String columnName = metaData.getColumnLabel(j);
						String columnValue = rs.getString(columnName);
						jsonObject.put(columnName, columnValue);
					}
					jsonArray.put(jsonObject); 
				}
			}  finally {
				safeClose(rs);
				safeClose(ps);
				safeClose(conn);
			}
			return jsonArray;
		}    
}
