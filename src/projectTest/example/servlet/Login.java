package projectTest.example.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projectTest.example.beans.PostTb;
import projectTest.example.beans.TagTb;
import projectTest.example.beans.UserTb;
import projectTest.example.conn.ConnectDB;
import projectTest.example.util.DBUtil;
import projectTest.example.util.Encode;

/**
 * Active with database
 *
 * @author ToanNV
 *
 */
public class Login extends HttpServlet {
	HttpSession httpSession;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = request.getParameter("rememberMe");
		DBUtil dbUtil = new DBUtil();
		ConnectDB connectDB = new ConnectDB();
		Connection connection = null;
		connection = connectDB.ConnectDB();
		Encode encode = new Encode();
		UserTb user = new UserTb();
		int page = 1;
		if (connection != null && email != null && password != null) {
			String password_ = encode.getMD5(password);
			try {
				user = dbUtil.findUser(connection, email, password_);
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
			if (user != null) {
				if (remember != null) {
					Cookie cookieEmail = new Cookie("email", email.trim());
					Cookie cookieUsername = new Cookie("username", user.getUsername().trim());
					Cookie cookiePassword = new Cookie("password", password);
					Cookie cookieRemember = new Cookie("remember", remember);
					cookieEmail.setMaxAge(24 * 60 * 60);
					cookieUsername.setMaxAge(24 * 60 * 60);
					cookiePassword.setMaxAge(24 * 60 * 60);
					cookieRemember.setMaxAge(24 * 60 * 60);
					response.addCookie(cookieEmail);
					response.addCookie(cookieUsername);
					response.addCookie(cookiePassword);
					response.addCookie(cookieRemember);
					httpSession = request.getSession();
					httpSession.setAttribute("sessionEmail", cookieEmail);
					httpSession.setAttribute("sessionUsername", cookieUsername);
					httpSession.setAttribute("sessionCreateat", user.getCreateAt());
				}
				httpSession = request.getSession();
				httpSession.setAttribute("sessionEmail", email.trim());
				httpSession.setAttribute("sessionUsername", user.getUsername());
				httpSession.setAttribute("sessionCreateat", user.getCreateAt());
				httpSession.setAttribute("sessionUserId", user.getId());
				httpSession.setAttribute("hash", password_);
				List<TagTb> listTag = new ArrayList<TagTb>();
				List<PostTb> listPost = new ArrayList<PostTb>();
				try {
					if (request.getParameter("page") != null) {
						page = Integer.parseInt(request.getParameter("page"));
					}
					listTag = dbUtil.showAllTag(connection);
					listPost = dbUtil.listPost(connection, 0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("listTag", listTag);
				request.setAttribute("listPost", listPost);
				request.getRequestDispatcher("/WEB-INF/view/mainContent.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		try {
			connectDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
