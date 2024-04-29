package com.org.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.org.dao.UserDao;
import com.org.dto.User;

@WebServlet("/adduser")
public class AddUser extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = new User();
		u.setName(req.getParameter("name"));
		u.setAge(Integer.parseInt(req.getParameter("age")));
		u.setEmail(req.getParameter("email"));
		u.setPassword(req.getParameter("password"));
		u.setMobile(Long.parseLong(req.getParameter("mobile")));
		
		UserDao udao = new UserDao();
		udao.saveUser(u);
		HttpSession session = req.getSession();
		session.setAttribute("success", "New User added successfully...");
		resp.sendRedirect("add.jsp");
	}
}
