package in.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.dao.DashBoardDAO;
import in.dto.EducationDTO;
import in.dto.FamilyDTO;

@WebServlet("/save-family")
public class FamilyServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 String email = req.getParameter("email");
	     String fatherName = req.getParameter("fatherName");
	     String motherName = req.getParameter("motherName");
	     String maritalStatus = req.getParameter("maritalStatus");
	     Integer siblings = Integer.parseInt(req.getParameter("siblings"));
        
        FamilyDTO dto = new FamilyDTO();
        dto.setEmail(email);
        dto.setFatherName(fatherName);
        dto.setMotherName(motherName);
        dto.setMaritialStatus(maritalStatus);
        dto.setSiblings(siblings);
        
        DashBoardDAO dao = new DashBoardDAO();
        boolean isInserted = dao.insertFamily(dto);
        
        try {
        	
        	if(isInserted) {
            	HttpSession session = req.getSession();
            	session.setAttribute("successMessage", "Family information saved successfully!");
            	resp.sendRedirect("dashboard.jsp");
            } else {
            	req.setAttribute("error", "Family info. already exists for this email!.");
    		    req.getRequestDispatcher("dashboard.jsp?menu=family").forward(req, resp);
            }
        	
        }catch(Exception e) {
        	 e.printStackTrace();
             resp.getWriter().println("Database error: " + e.getMessage());
        }
	}
}
