package com.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Entity.Student;
import com.Service.StdService;

@WebServlet("/displayStudents")
public class DisplayStd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StdService std = new StdService();
        List<Student> res = std.display();
        
        resp.setContentType("text/html");
        PrintWriter pr = resp.getWriter();
        pr.print("<html><head><title>Student List</title>");
        pr.print("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
        pr.print("</head><body class='container mt-5'>");
        pr.print("<h1 class='text-center mb-4'>Student List</h1>");
        
        if (res != null && !res.isEmpty()) {
            pr.print("<table class='table table-bordered table-striped'>");
            pr.print("<thead class='table-dark'><tr>");
            pr.print("<th>Student ID</th><th>Student Name</th><th>Age</th><th>Mobile</th><th>Gender</th><th>Actions</th>");
            pr.print("</tr></thead><tbody>");
            
            for (Student s : res) {
                pr.print("<tr>");
                pr.print("<td>" + s.getId() + "</td>");
                pr.print("<td>" + s.getName() + "</td>");
                pr.print("<td>" + s.getAge() + "</td>");
                pr.print("<td>" + s.getMobile() + "</td>");
                pr.print("<td>" + s.getGender() + "</td>");
                pr.print("<td>");
                
                // Update button in a form
                pr.print("<form action='updateStudent' method='GET' class='d-inline'>");
                pr.print("<a href='update.html' class='btn btn-warning wow animate__animated animate__pulse' data-wow-delay='0.3s'>Update</a>");
                pr.print("</form> ");
                
                // Delete button in a form
                pr.print("<form action='deleteStudent' method='POST' class='d-inline' onsubmit='return confirm(\"Are you sure?\")'>");
                pr.print("<a href='delete.html' class='btn btn-danger wow animate__animated animate__pulse' data-wow-delay='0.3s'>Delete</a>");
                pr.print("</form>");
                
                pr.print("</td>");
                pr.print("</tr>");
            }
            
            pr.print("</tbody></table>");
        } else {
            pr.print("<p class='text-center text-danger'>No student records found.</p>");
        }
        
        // Home button
        pr.print("<div class='text-center mt-4'>");
        pr.print("<a href='home.html' class='btn btn-primary wow animate__animated animate__pulse' data-wow-delay='0.3s'>Go to Home</a>");
        pr.print("</div>");
        
        pr.print("</body></html>");
    }
}
