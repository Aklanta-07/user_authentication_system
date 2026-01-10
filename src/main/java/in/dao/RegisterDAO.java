package in.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.dbconnection.DatabaseConnection;
import in.dto.Fields;

public class RegisterDAO {
	
	public boolean insertField(Fields field) {
		   String query = "INSERT INTO user_credentials (email, f_name, l_name, password, gender) VALUES (?, ?, ?, ?, ?)";
		   
		   try(Connection con = DatabaseConnection.getConnection();
			   PreparedStatement pstmt = con.prepareStatement(query)) {
			
			pstmt.setString(1, field.getEmail());
			pstmt.setString(2, field.getFirstName());
			pstmt.setString(3, field.getLastName());
			pstmt.setString(4, field.getPassword());
			pstmt.setString(5, field.getGender());
			
			int rowsInserted = pstmt.executeUpdate();
			
			return rowsInserted > 0;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		   
	   }
}
