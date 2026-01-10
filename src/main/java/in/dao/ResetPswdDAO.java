package in.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.dbconnection.DatabaseConnection;
import in.dto.Fields;

public class ResetPswdDAO {
	
	public boolean resetPassword(Fields field) {
		
		String sql = "UPDATE user_credentials SET password = ? WHERE email = ?";
		
		try(Connection con = DatabaseConnection.getConnection();
				   PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, field.getPassword());
			pstmt.setString(2, field.getEmail());
			
			int rowsAffected = pstmt.executeUpdate();
			
			return rowsAffected > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
					
		}
	}

}
