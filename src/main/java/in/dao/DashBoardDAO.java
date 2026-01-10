package in.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.dbconnection.DatabaseConnection;
import in.dto.Address;
import in.dto.EducationDTO;
import in.dto.FamilyDTO;

public class DashBoardDAO {
	
	public boolean insertAddress(Address address) {
		
		String sql =
				"INSERT INTO user_address(email, street, city, state, postal_code) values (?, ?, ?, ?, ?)";
		
		try(Connection con = DatabaseConnection.getConnection();
				   PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, address.getEmail());
			pstmt.setString(2, address.getStreet());
			pstmt.setString(3, address.getCity());
			pstmt.setString(4, address.getState());
			pstmt.setString(5, address.getPostal_code());
			
            int rowsInserted = pstmt.executeUpdate();
			
			return rowsInserted > 0;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean insertEducation(EducationDTO education) {
		
		String sql =
				"INSERT INTO user_education(email, degree, institution, field_of_study, year_of_completion) values (?, ?, ?, ?, ?)";
		
		try(Connection con = DatabaseConnection.getConnection();
				   PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, education.getEmail());
			pstmt.setString(2, education.getDegree());
			pstmt.setString(3, education.getInstitution());
			pstmt.setString(4, education.getFieldOfStudy());
			pstmt.setString(5, education.getYearOfCompletion());
			
            int rowsInserted = pstmt.executeUpdate();
			
			return rowsInserted > 0;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean insertFamily(FamilyDTO family) {
		
		String sql =
				"INSERT INTO user_family(email, father_name, mother_name, marital_status, siblings) values (?, ?, ?, ?, ?)";
		
		try(Connection con = DatabaseConnection.getConnection();
				   PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, family.getEmail());
			pstmt.setString(2, family.getFatherName());
			pstmt.setString(3, family.getMotherName());
			pstmt.setString(4, family.getMaritialStatus());
			pstmt.setInt(5, family.getSiblings());
			
            int rowsInserted = pstmt.executeUpdate();
			
			return rowsInserted > 0;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

}
