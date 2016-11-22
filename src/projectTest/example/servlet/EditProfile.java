package projectTest.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectTest.example.conn.ConnectDB;
import projectTest.example.util.DBUtil;

public class EditProfile extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("userId").trim());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ConnectDB connect = new ConnectDB();
		DBUtil db = new DBUtil();
		Connection conn = null;
		conn = connect.ConnectDB();
		Boolean update = false;
		if (username != null && password != null) {
			try {
				update = db.UpdateAccount(conn, username, id, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (update == true) {
				request.getRequestDispatcher("goLogin").forward(request, response);
				return;
			} else {
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<body>");
				pw.println("<p>Update fail</p>");
				pw.println("Go to <a href='goRegister'>Register new account here</a>");
				pw.println("</body>");
				pw.println("</html>");
			}
		}
		try {
			connect.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
