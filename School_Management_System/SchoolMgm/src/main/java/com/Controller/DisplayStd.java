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

        pr.print("<!DOCTYPE html>");
        pr.print("<html lang='en'><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        pr.print("<title>Student List</title>");
        
        // Bootstrap, Animate.css and Bootstrap Icons
        pr.print("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        pr.print("<link href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css' rel='stylesheet'>");
        pr.print("<link href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css' rel='stylesheet'>");
        
        pr.print("<style>");
        pr.print("body { background: linear-gradient(135deg, #a1c4fd 0%, #c2e9fb 100%); }");
        pr.print(".card { background: rgba(255,255,255,0.95); border-radius: 20px; box-shadow: 0 8px 20px rgba(0,0,0,0.2); padding: 20px; }");
        pr.print(".btn-custom { border-radius: 50px; transition: transform 0.3s ease; }");
        pr.print(".btn-custom:hover { transform: scale(1.05); }");
        pr.print("</style>");
        
        pr.print("</head><body class='container py-5'>");
        
        pr.print("<div class='card animate__animated animate__fadeInUp'>");
        pr.print("<h1 class='text-center mb-4'><i class='bi bi-people-fill text-primary'></i> Student List</h1>");

        if (res != null && !res.isEmpty()) {
            pr.print("<div class='table-responsive'>");
            pr.print("<table class='table table-bordered table-striped align-middle'>");
            pr.print("<thead class='table-dark'><tr>");
            pr.print("<th>Student ID</th><th>Name</th><th>Age</th><th>Mobile</th><th>Gender</th><th>Actions</th>");
            pr.print("</tr></thead><tbody>");

            for (Student s : res) {
                pr.print("<tr>");
                pr.print("<td>" + s.getId() + "</td>");
                pr.print("<td>" + s.getName() + "</td>");
                pr.print("<td>" + s.getAge() + "</td>");
                pr.print("<td>" + s.getMobile() + "</td>");
                pr.print("<td>" + s.getGender() + "</td>");
                pr.print("<td class='d-flex gap-2'>");

                // Update button should redirect to update page (you may modify to open edit page directly later)
                pr.print("<a href='update.html' class='btn btn-warning btn-custom'><i class='bi bi-pencil-square'></i> Update</a>");
                
                // Delete button should redirect to delete page
                pr.print("<a href='delete.html' class='btn btn-danger btn-custom'><i class='bi bi-trash-fill'></i> Delete</a>");

                pr.print("</td>");
                pr.print("</tr>");
            }

            pr.print("</tbody></table>");
            pr.print("</div>");
        } else {
            pr.print("<p class='text-center text-danger animate__animated animate__shakeX'>No student records found.</p>");
        }

        // Go to Home Button
        pr.print("<div class='text-center mt-4'>");
        pr.print("<a href='home.html' class='btn btn-primary btn-custom'><i class='bi bi-house-door-fill'></i> Go to Home</a>");
        pr.print("</div>");

        pr.print("</div>"); // card div end
        pr.print("</body></html>");
    }
}
