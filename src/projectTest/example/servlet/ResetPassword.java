package projectTest.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectTest.example.util.EmailUtility;
import projectTest.example.util.Encode;

public class ResetPassword extends HttpServlet {
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
		String password = request.getParameter("password");
		Encode encode = new Encode();
		password = encode.getMD5(password.trim());
		String subject = "Reset password";
		String content = "http://localhost:8080/projectTest/activeReset?email=" + recipient + "&password=" + password;
		String resultMessage = "";
		if (recipient != null && password != null) {
			try {
				// start send mail
				send = new EmailUtility();
				send.sendEmail(host, port, user, pass, recipient, subject, content);
				resultMessage = "The e-mail was sent successfully";
				System.out.println("OK send email ok");
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<body>");
				pw.println("<p>Please check email to reset password</p>");
				pw.println("End go to <a href='goLogin'>Login here</a>");
				pw.println("</body>");
				pw.println("</html>");
			} catch (Exception ex) {
				ex.printStackTrace();
				resultMessage = "There were an error: " + ex.getMessage();
				PrintWriter pw = response.getWriter();
				pw.println("<!DOCTYPE html>");
				pw.println("<html>");
				pw.println("<body>");
				pw.println("<p>You were reset password fail!</p>");
				pw.println("Go to <a href='goLogin'>Login</a>");
				pw.println("</body>");
				pw.println("</html>");
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
