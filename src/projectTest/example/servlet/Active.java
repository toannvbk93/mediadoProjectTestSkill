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
/**
 * Active with database
 *
 * @author ToanNV
 *
 */
public class Active extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ConnectDB connect = new ConnectDB();
		DBUtil db = new DBUtil();
		Connection conn = null;
		conn = connect.ConnectDB();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (email != null && password != null) {
			if (conn != null) {
				try {
					db.activeAccount(conn, email, password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<body>");
				pw.println("<p>Active success</p>");
				pw.println("Go to <a href='goLogin'>Login here</a>");
				pw.println("</body>");
				pw.println("</html>");
				return;
			}
		}
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<p>Active fail</p>");
		pw.println("Go to <a href='goRegister'>Register here</a>");
		pw.println("</body>");
		pw.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
