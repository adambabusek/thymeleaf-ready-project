package com.thymeleafready.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class ThymeleafFilter implements Filter {

	private TemplateEngine templateEngine;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		IContext ctx = new WebContext((HttpServletRequest) request,	(HttpServletResponse) response, request.getServletContext());
		templateEngine.process("example", ctx, response.getWriter());
	}

	@Override
	public void destroy() {	}

	/**
	 * Initialization of ThymeleafFilter consists of template resolver and template engine creation.
	 */
	@Override
	public void init(FilterConfig cfg) throws ServletException {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(cfg.getServletContext());
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false); // cacheble=false during development

		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
	}

}
