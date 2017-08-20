package com.thymeleafready.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Custom servlet extending ThymeServlet. For template processing call super.process method.
 */
public class MyServlet extends ThymeServlet {
	private static final long serialVersionUID = 1631475733649468245L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// processing example template /WEB-INF/templates/example.html
		req.setAttribute("name", "World");
		process("example", req, resp);
	}
}
