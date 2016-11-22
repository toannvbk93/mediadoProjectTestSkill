package projectTest.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projectTest.example.beans.AdminPost;
import projectTest.example.beans.UserTb;
import projectTest.example.conn.ConnectDB;
import projectTest.example.util.AdminUtil;

public class AdminLogin extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (email == null || password == null) {
			request.getRequestDispatcher("/WEB-INF/view/adminLogin.jsp").forward(request, response);
			return;
		}
		Connection conn = null;
		UserTb user = new UserTb();
		AdminUtil admin = new AdminUtil();
		ConnectDB connection = new ConnectDB();
		conn = connection.ConnectDB();
		List<UserTb> listUser = new ArrayList<UserTb>();
		List<AdminPost> listAdminPost = new ArrayList<AdminPost>();
		try {
			user = admin.adminLogin(conn, email, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user != null) {
			httpSession = request.getSession();
			try {
				listUser = admin.selectAllUser(conn);
				listAdminPost = admin.showAllPostDetail(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			httpSession.setAttribute("sessionUser", user);
			if (listUser != null && listAdminPost != null) {
				request.setAttribute("listUser", listUser);
				request.setAttribute("listAdminPost", listAdminPost);
			}
			request.getRequestDispatcher("/WEB-INF/view/adminSite.jsp").forward(request, response);
		}
		try {
			connection.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
