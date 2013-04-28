package com.alonso.performance.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Servlet implementation class DbcpServlet
 */
@WebServlet("/dbcpServlet")
public class DbcpServlet extends HttpServlet {
	private static final long serialVersionUID = 6632177218196304178L;
	private JdbcTemplate jdbcTemplate;
	@Override
	public void init() throws ServletException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sql = "select count(*) from price where id=" + System.currentTimeMillis();
		int count = jdbcTemplate.queryForInt(sql);
		request.setAttribute("count", count);
		request.setAttribute("sql", sql);
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
