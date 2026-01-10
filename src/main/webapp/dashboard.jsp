<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    // Check if user is logged in
    HttpSession sess = request.getSession(false);
    if(sess == null || sess.getAttribute("userEmail") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    
    String userEmail = (String) sess.getAttribute("userEmail");
    String userName = (String) sess.getAttribute("userName");
    
    // Get success message if any
    String successMsg = (String) sess.getAttribute("successMessage");
    if(successMsg != null) {
        sess.removeAttribute("successMessage");
    }
    
    String errorMsg = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }
        
        .dashboard-container {
            display: flex;
            min-height: 100vh;
        }
        
        /* Left Sidebar */
        .sidebar {
            width: 250px;
            background-color: #2c3e50;
            color: white;
            padding: 20px 0;
        }
        
        .sidebar h2 {
            padding: 0 20px 20px;
            border-bottom: 1px solid #34495e;
            margin-bottom: 20px;
        }
        
        .menu-item {
            padding: 15px 20px;
            cursor: pointer;
            transition: background 0.3s;
            border-left: 3px solid transparent;
        }
        
        .menu-item:hover {
            background-color: #34495e;
            border-left: 3px solid #3498db;
        }
        
        .menu-item.active {
            background-color: #34495e;
            border-left: 3px solid #3498db;
        }
        
        /* Right Content Area */
        .content-area {
            flex: 1;
            padding: 30px;
        }
        
        .header {
            background: white;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .welcome-message {
            font-size: 24px;
            color: #2c3e50;
        }
        
        .logout-btn {
            padding: 10px 20px;
            background-color: #e74c3c;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }
        
        .logout-btn:hover {
            background-color: #c0392b;
        }
        
        .form-container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            display: none;
        }
        
        .form-container.active {
            display: block;
        }
        
        .form-container h3 {
            margin-bottom: 20px;
            color: #2c3e50;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: bold;
        }
        
        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }
        
        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: #3498db;
        }
        
        .form-group input[readonly] {
            background-color: #f0f0f0;
            cursor: not-allowed;
        }
        
        .submit-btn {
            width: 100%;
            padding: 12px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }
        
        .submit-btn:hover {
            background-color: #2980b9;
        }
        
        .success-message {
            background-color: #d4edda;
            color: #155724;
            padding: 12px;
            border-radius: 4px;
            margin-bottom: 20px;
            border: 1px solid #c3e6cb;
        }
        
       .error-message {
            background-color: #f8d7da;
            color: #721c24;
            padding: 12px;
            border-radius: 4px;
            margin-bottom: 20px;
            border: 1px solid #f5c6cb;
}
        
        .default-content {
            background: white;
            padding: 40px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            text-align: center;
        }
        
        .default-content h2 {
            color: #2c3e50;
            margin-bottom: 10px;
        }
        
        .default-content p {
            color: #7f8c8d;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <!-- Left Sidebar -->
        <div class="sidebar">
            <h2>Menu</h2>
            <div class="menu-item" onclick="showForm('address')">Address</div>
            <div class="menu-item" onclick="showForm('education')">Education</div>
            <div class="menu-item" onclick="showForm('family')">Family</div>
        </div>
        
        <!-- Right Content Area -->
        <div class="content-area">
            <!-- Header -->
            <div class="header">
                <div class="welcome-message">
                    Welcome, <%= userName != null ? userName : userEmail %>! 👋
                </div>
                <a href="index.jsp" class="logout-btn">Logout</a>
            </div>
            
            <!-- Success Message -->
            <% if(successMsg != null) { %>
                <div class="success-message">
                    ✅ <%= successMsg %>
                </div>
            <% } %>
            
            <!-- Error Message -->
            <% if(errorMsg != null) { %>
               <div class="error-message">
                   ❌ <%= errorMsg %>
               </div>
            <% } %>
            
            <!-- Default Content -->
            <div id="default-content" class="default-content">
                <h2>Dashboard Home</h2>
                <p>Select a menu item from the left to view or add information.</p>
            </div>
            
            <!-- Address Form -->
            <div id="address-form" class="form-container">
                <h3>Address Information</h3>
                <form action="save-address" method="post">
                    <div class="form-group">
                        <label>Email:</label>
                        <input type="email" name="email" value="<%= userEmail %>" readonly required>
                    </div>
                    
                    <div class="form-group">
                        <label>Street Address:</label>
                        <input type="text" name="street" placeholder="Enter street address" required>
                    </div>
                    
                    <div class="form-group">
                        <label>City:</label>
                        <input type="text" name="city" placeholder="Enter city" required>
                    </div>
                    
                    <div class="form-group">
                        <label>State:</label>
                        <input type="text" name="state" placeholder="Enter state" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Postal Code:</label>
                        <input type="text" name="postalCode" placeholder="Enter postal code" required>
                    </div>
                    
                    <button type="submit" class="submit-btn">Submit Address</button>
                </form>
            </div>
            
            <!-- Education Form -->
            <div id="education-form" class="form-container">
                <h3>Education Information</h3>
                <form action="save-education" method="post">
                    <div class="form-group">
                        <label>Email:</label>
                        <input type="email" name="email" value="<%= userEmail %>" readonly required>
                    </div>
                    
                    <div class="form-group">
                        <label>Degree:</label>
                        <input type="text" name="degree" placeholder="e.g., Bachelor of Science" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Institution:</label>
                        <input type="text" name="institution" placeholder="Enter institution name" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Field of Study:</label>
                        <input type="text" name="fieldOfStudy" placeholder="e.g., Computer Science" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Year of Completion:</label>
                        <input type="number" name="yearOfCompletion" placeholder="e.g., 2020" min="1950" max="2030" required>
                    </div>
                    
                    <button type="submit" class="submit-btn">Submit Education</button>
                </form>
            </div>
            
            <!-- Family Form -->
            <div id="family-form" class="form-container">
                <h3>Family Information</h3>
                <form action="save-family" method="post">
                    <div class="form-group">
                        <label>Email:</label>
                        <input type="email" name="email" value="<%= userEmail %>" readonly required>
                    </div>
                    
                    <div class="form-group">
                        <label>Father's Name:</label>
                        <input type="text" name="fatherName" placeholder="Enter father's name" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Mother's Name:</label>
                        <input type="text" name="motherName" placeholder="Enter mother's name" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Marital Status:</label>
                        <select name="maritalStatus" required>
                            <option value="">Select status</option>
                            <option value="Single">Single</option>
                            <option value="Married">Married</option>
                            <option value="Divorced">Divorced</option>
                            <option value="Widowed">Widowed</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label>Number of Siblings:</label>
                        <input type="number" name="siblings" placeholder="Enter number of siblings" min="0" required>
                    </div>
                    
                    <button type="submit" class="submit-btn">Submit Family Info</button>
                </form>
            </div>
        </div>
    </div>
    
    <script>
        function showForm(formType) {
            // Hide all forms and default content
            document.getElementById('default-content').style.display = 'none';
            document.getElementById('address-form').classList.remove('active');
            document.getElementById('education-form').classList.remove('active');
            document.getElementById('family-form').classList.remove('active');
            
            // Remove active class from all menu items
            const menuItems = document.querySelectorAll('.menu-item');
            menuItems.forEach(item => item.classList.remove('active'));
            
            // Show selected form and set active menu
            document.getElementById(formType + '-form').classList.add('active');
            event.target.classList.add('active');
        }
    </script>
</body>
</html>