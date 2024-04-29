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

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oldPassword = req.getParameter("oldPassword");
		int id = Integer.parseInt(req.getParameter("id"));
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		
		UserDao udao = new UserDao();
		User u = udao.fetchUserById(id);
		
		if(u.getPassword().equals(oldPassword))
		{
			if(newPassword.equals(confirmPassword))
			{
				udao.updateUserPasswordById(id, newPassword);
				resp.sendRedirect("home.jsp");
			}
			else
			{
				HttpSession session = req.getSession();
				session.setAttribute("failure", "Password Mismatch !!!");
				resp.sendRedirect("changePassword.jsp");
			}
		}
		else
		{
			HttpSession session = req.getSession();
			session.setAttribute("failure", "You have entered wrong password");
			resp.sendRedirect("changePassword.jsp");
		}
	}
}
