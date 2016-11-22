package projectTest.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectTest.example.conn.ConnectDB;
import projectTest.example.util.DBUtil;
import projectTest.example.util.EmailUtility;
import projectTest.example.util.Encode;

/**
 * Active with database
 *
 * @author ToanNV
 *
 */
public class Register extends HttpServlet {

	private String host;
	private String port;
	private String user;
	private String pass;
	private EmailUtility send;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// reads form fields
		String recipient = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Encode encode = new Encode();
		password = encode.getMD5(password);
		String subject = "Active account";
		String content = "http://localhost:8080/projectTest/active?email=" + recipient + "&password=" + password;
		String resultMessage = "";
		if (recipient != null && username != null & password != null) {
			try {
				// Start insert database
				DBUtil db = new DBUtil();
				ConnectDB connect = new ConnectDB();
				Connection conn = null;
				conn = connect.ConnectDB();
				if (conn != null) {
					try {
						db.insertUser(conn, username, password, recipient);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				try {
					connect.closeDB();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// end insert database
				// start send mail
				send = new EmailUtility();
				send.sendEmail(host, port, user, pass, recipient, subject, content);
				resultMessage = "The e-mail was sent successfully";
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<body>");
				pw.println("<p>You were registry success. Please active mail!!, befor login</p>");
				pw.println("Go to <a href='goLogin'>Login here</a>");
				pw.println("</body>");
				pw.println("</html>");
			} catch (Exception ex) {
				ex.printStackTrace();
				resultMessage = "There were an error: " + ex.getMessage();
				PrintWriter pw = response.getWriter();
				pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<body>");
				pw.println("<p>You were registry fail. Please resign up!</p>");
				pw.println("Go to <a href='registerForm'>Sign up here</a>");
				pw.println("</body>");
				pw.println("</html>");
			} finally {
				request.setAttribute("Message", resultMessage);
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
