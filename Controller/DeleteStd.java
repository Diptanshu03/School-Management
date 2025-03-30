package com.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Service.StdService;

@WebServlet("/deleteStudent")
public class DeleteStd extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sid = Integer.parseInt(req.getParameter("id"));
		
		StdService s = new StdService();
		int n = s.delete(sid);
		
		if(n>0) {
			RequestDispatcher dis = req.getRequestDispatcher("home.html");
			dis.forward(req, resp);
		}else {
			RequestDispatcher dis = req.getRequestDispatcher("delete.html");
			PrintWriter pr = resp.getWriter();
			pr.print("<div> <h1> Invalid id given </h1> </div>");
			dis.include(req, resp);
		}
	}
	
	@Override
	public void destroy() {
		StdService.closeConnection();
		System.out.println("closed");
	}
}
