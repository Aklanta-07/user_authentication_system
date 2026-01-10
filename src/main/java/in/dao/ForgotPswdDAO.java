package in.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.dbconnection.DatabaseConnection;
import in.dto.Fields;

public class ForgotPswdDAO {
	
	boolean isPresent;
	
	public boolean findEmail(Fields field) throws SQLException {
		
		String sql = "SELECT * FROM user_credentials WHERE email = ?";
		
		try(Connection con = DatabaseConnection.getConnection();
				   PreparedStatement pstmt = con.prepareStatement(sql) ) {
			
			pstmt.setString(1, field.getEmail());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next() ) {
				isPresent = true;
			}
			
			return isPresent;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

}
