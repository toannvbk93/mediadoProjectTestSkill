package projectTest.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projectTest.example.beans.UserTb;
import projectTest.example.conn.ConnectDB;
import projectTest.example.util.AdminUtil;
import projectTest.example.util.DBUtil;

public class AdminUser extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		action = action;
		if (action != null) {
			ConnectDB connection = new ConnectDB();
			Connection conn = null;
			DBUtil db = new DBUtil();
			conn = connection.ConnectDB();
			HttpSession httpSession = null;
			List<UserTb> listUser = new ArrayList<UserTb>();
			AdminUtil admin = new AdminUtil();
			int id = Integer.parseInt(request.getParameter("id"));
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			httpSession = request.getSession();
			if (action.equals("Edit")) {
				if (conn == null) {
					System.out.println("Connect database failed");
					return;
				}
				if (email != null && username != null && password != null) {
					try {
						db.UpdateAccount(conn, username, id, password);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			try {
				listUser = admin.selectAllUser(conn);
				if (listUser != null)
					request.setAttribute("listUser", listUser);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/view/adminSite.jsp").forward(request, response);
		}
	}

}
