package in.servlet;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.dao.DashBoardDAO;
import in.dto.EducationDTO;

@WebServlet("/save-education")
public class EducationServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 String email = req.getParameter("email");
	     String degree = req.getParameter("degree");
	     String institution = req.getParameter("institution");
	     String fieldOfStudy = req.getParameter("fieldOfStudy");
         String yearOfCompletion = req.getParameter("yearOfCompletion");
	        
        
        EducationDTO dto = new EducationDTO();
        dto.setEmail(email);
        dto.setDegree(degree);
        dto.setInstitution(institution);
        dto.setFieldOfStudy(fieldOfStudy);
        dto.setYearOfCompletion(yearOfCompletion);
        
        DashBoardDAO dao = new DashBoardDAO();
        boolean isInserted = dao.insertEducation(dto);
        
        try {
        	
        	if(isInserted) {
            	HttpSession session = req.getSession();
            	session.setAttribute("successMessage", "Education information saved successfully!");
            	resp.sendRedirect("dashboard.jsp");
            } else {
            	req.setAttribute("error", "Education info. already exists for this email!.");
    		    req.getRequestDispatcher("dashboard.jsp?menu=education").forward(req, resp);
            }
        	
        }
        catch(Exception e) {
        	 e.printStackTrace();
             resp.getWriter().println("Database error: " + e.getMessage());
        }
        
        }

}
