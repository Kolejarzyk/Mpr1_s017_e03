package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	
	
}
