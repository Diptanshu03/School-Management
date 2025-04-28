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

@WebServlet("/updateStudent")
public class UpdateStd extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String sname = req.getParameter("name");
		String sage = req.getParameter("age");
		String phno = req.getParameter("mobile");
		String gender = req.getParameter("gender");
		
		int sid = Integer.parseInt(id);
		int age = Integer.parseInt(sage);
		
		StdService s = new StdService();
		int n = s.update(sid, sname, age, phno, gender);
		
		if(n>0) {
			RequestDispatcher dis = req.getRequestDispatcher("home.html");
			dis.forward(req, resp);
		}else {
			RequestDispatcher dis = req.getRequestDispatcher("update.html");
			PrintWriter pr = resp.getWriter();
			pr.print("<div> <h1> Data not updated </h1> </div>");
			dis.include(req, resp);
		}
		
	}

	@Override
	public void destroy() {
		StdService.closeConnection();
		System.out.println("closed");
	}
}
