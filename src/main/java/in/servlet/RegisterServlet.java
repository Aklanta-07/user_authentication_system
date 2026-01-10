package in.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.dao.RegisterDAO;
import in.dto.Fields;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
	    req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		String email = req.getParameter("email");
		String firstName = req.getParameter("f-name");
		String lastName = req.getParameter("l-name");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		
		Fields field = new Fields();
		field.setEmail(email);
		field.setFirstName(firstName);
		field.setLastName(lastName);
		field.setPassword(password);
		field.setGender(gender);
		
		RegisterDAO dao = new RegisterDAO();
		
		boolean isInserted = dao.insertField(field);
		
		out.println("<html>");
		out.println("<title> Result </title>");
		out.println("<body>");
		
		if(isInserted) {
			 req.setAttribute("successMsg", "Registration Successfull - Login➡️!.");
    		 req.getRequestDispatcher("index.jsp").forward(req, res);
	         
		} else {
			 out.println("<h2>Error!</h2>");
	         out.println("<p>Failed to add in database.</p>");
	         out.println("<br><a href='register.html'>Try Again</a>");
		}
		
		 out.println("<br><a href='index.jsp'>Login</a>");
	     out.println("</body>");
		 
	}
}
