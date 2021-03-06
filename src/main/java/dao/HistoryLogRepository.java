package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.model.HistoryLog;
import domain.model.Person;

public class HistoryLogRepository {
	
	private Connection connection;
	
	private String createTableSql = "CREATE TABLE historyLog("
			+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
			+ "date DATE,"
			+ "amount DECIMAL(7,2),"
			+ "accountfrom BIGINT,"
			+ "accountto BIGINT,"
			+ "rate DECIMAL(7,2),"
			+ "operation BIGINT"
			+ ")";
	
	private Statement createTable;

	private String insertSql = "INSERT INTO historyLog(date,amount,accountfrom,accountto,rate,operation) VALUES(?,?,?,?,?,?)";
	private String deleteSql = "DELETE FROM historyLog WHERE id = ?";
	
	private PreparedStatement insert;
	private PreparedStatement delete;
	
	public HistoryLogRepository(Connection connection) {
		this.connection = connection;
		
		try {
			createTable = connection.createStatement();
			
			boolean tableExists = false;
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("historylog")){
					tableExists=true;
					break;
				}
			}
			if(!tableExists)
				createTable.executeUpdate(createTableSql);
				
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(HistoryLog p){
		try{
			delete.setInt(1, p.getId());
			delete.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void add(HistoryLog p){
		try{
			
			insert.setString(1, p.getDate().toString());
			insert.setDouble(2, p.getAmount());
			insert.setInt(3, p.getFrom().getId());
			insert.setInt(4, p.getTo().getId());
			insert.setDouble(5, p.getRate());
			insert.setInt(6, p.getType().ordinal());
			insert.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}
	
}
