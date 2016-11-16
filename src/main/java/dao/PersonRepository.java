package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.model.Person;

public class PersonRepository {

	private Connection connection;
	
	private String createTableSql = "CREATE TABLE person("
			+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
			+ "name VARCHAR(20),"
			+ "surname VARCHAR(50)"
			+ ")";
	
	private Statement createTable;
	
	
	private String insertSql = "INSERT INTO person(name,surname) VALUES(?,?)";
	private String deleteSql = "DELETE FROM Person WHERE id = ?";
	private String updateSql = "UPDATE PERSON set name=?, surname=? WHERE id=?";
	private String selectByIdSql = "SELECT * FROM person WHERE id=?";
	private String selectAllSql = "SELECT * FROM person";
	
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement update;
	private PreparedStatement selectById;
	private PreparedStatement selectAll;
	
	public PersonRepository(Connection connection) {
		this.connection = connection;
		
		try {
			createTable = connection.createStatement();
			
			boolean tableExists = false;
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			while(rs.next()){
				if(rs.getString("TABLE_NAME").equalsIgnoreCase("person")){
					tableExists=true;
					break;
				}
			}
			if(!tableExists)
				createTable.executeUpdate(createTableSql);
			insert = connection.prepareStatement(insertSql);
			delete = connection.prepareStatement(deleteSql);	
			update = connection.prepareStatement(updateSql);
			selectById = connection.prepareStatement(selectByIdSql);
			selectAll = connection.prepareStatement(selectAllSql);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Person get(int personId){
		try{
			
			selectById.setInt(1, personId);
			ResultSet rs = selectById.executeQuery();
			while(rs.next()){
				Person result = new Person();
				result.setId(personId);
				result.setName(rs.getString("name"));
				result.setSurname(rs.getString("surname"));
				return result;
			}
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}

	public List<Person> getAll(){
		try{
			List<Person> result = new ArrayList<Person>();
			ResultSet rs = selectAll.executeQuery();
			while(rs.next()){
				Person p = new Person();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setSurname(rs.getString("surname"));
				result.add(p);
			}
			return result;
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public void delete(Person p){
		try{
			delete.setInt(1, p.getId());
			delete.executeUpdate();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	
	public void add(Person p){
		try{
			
			insert.setString(1, p.getName());
			insert.setString(2, p.getSurname());
			insert.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}
	
	public void update(Person p){
		try{
			
			update.setString(1, p.getName());
			update.setString(2, p.getSurname());
			update.setInt(3, p.getId());
			update.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}