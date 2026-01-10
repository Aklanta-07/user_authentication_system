package in.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.dao.DashBoardDAO;
import in.dto.Address;

@WebServlet("/save-address")
public class AddressServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String email = req.getParameter("email");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String postalCode = req.getParameter("postalCode");
        
        Address address = new Address();
        address.setEmail(email);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setPostal_code(postalCode);
        
        DashBoardDAO dao = new DashBoardDAO();
        boolean isInserted = dao.insertAddress(address);
       
        try {
        if(isInserted) {
			HttpSession session = req.getSession();
			session.setAttribute("successMessage", "Address information saved successfully!");
			resp.sendRedirect("dashboard.jsp");
		} else {
			req.setAttribute("error", "Address already exists for this email!.");
		    req.getRequestDispatcher("dashboard.jsp?menu=address").forward(req, resp);
		}
        
        }catch(Exception e) {
          	 e.printStackTrace();
             resp.getWriter().println("Database error: " + e.getMessage());
        
        }
	}    
}


