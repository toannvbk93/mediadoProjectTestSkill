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

import projectTest.example.beans.PostTb;
import projectTest.example.beans.TagTb;
import projectTest.example.beans.UserTb;
import projectTest.example.conn.ConnectDB;
import projectTest.example.util.DBUtil;

public class MyPost extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBUtil db = new DBUtil();
		ConnectDB connection = new ConnectDB();
		Connection conn = null;
		conn = connection.ConnectDB();
		String email = request.getParameter("email");
		String password = request.getParameter("hash");

		List<PostTb> listPost = new ArrayList<PostTb>();
		UserTb user = null;
		if (conn != null)
			try {
				user = db.findUser(conn, email, password);
			} catch (SQLException | ParseException e1) {
				e1.printStackTrace();
			}
		if (user != null) {
			try {
				listPost = db.findMyPost(conn, user.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		List<TagTb> listTag = new ArrayList<TagTb>();
		try {
			listTag = db.showAllTag(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("listTag", listTag);
		request.setAttribute("listPost", listPost);
		request.getRequestDispatcher("/WEB-INF/view/mainContent.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}
}
