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

import projectTest.example.beans.PostTb;
import projectTest.example.conn.ConnectDB;
import projectTest.example.util.DBUtil;

public class SearchPost extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("namepost");
		DBUtil db = new DBUtil();
		ConnectDB connection = new ConnectDB();
		Connection conn = null;
		conn = connection.ConnectDB();
		List<PostTb> listPost = new ArrayList<PostTb>();
		List<PostTb> listPostByTag = new ArrayList<PostTb>();
		List<PostTb> listPostByTitle = new ArrayList<PostTb>();
		if (conn != null && key != null) {
			try {
				listPostByTitle = db.findPostByTitle(conn, key);
				listPostByTag = db.findPostByTag(conn, key);
				if (listPostByTag != null)
					listPost.addAll(listPostByTag);
				if (listPostByTitle != null)
					listPost.addAll(listPostByTitle);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			request.getRequestDispatcher("allPost").forward(request, response);
			return;
		}
		try {
			connection.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (listPost.size() == 0) {
			response.sendRedirect("WEB-INF/view/404.jsp");
			return;
		}
		request.setAttribute("listPost", listPost);
		request.getRequestDispatcher("/WEB-INF/view/mainContent.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
