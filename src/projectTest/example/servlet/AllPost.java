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
import projectTest.example.beans.TagTb;
import projectTest.example.conn.ConnectDB;
import projectTest.example.util.DBUtil;

public class AllPost extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DBUtil dbUtil = new DBUtil();
		ConnectDB connectDB = new ConnectDB();
		Connection connection = null;
		connection = connectDB.ConnectDB();
		int page = 1;
		List<TagTb> listTag = new ArrayList<TagTb>();
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		List<PostTb> listPost = new ArrayList<PostTb>();
		try {
			listPost = dbUtil.listPost(connection, (page - 1) * 5);
			listTag = dbUtil.showAllTag(connection);
			int noOfRecords = dbUtil.getNoOfRecords();
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 5);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connectDB.closeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("listPost", listPost);
		request.setAttribute("listTag", listTag);
		request.getRequestDispatcher("/WEB-INF/view/mainContent.jsp").forward(request, response);
	}
}
