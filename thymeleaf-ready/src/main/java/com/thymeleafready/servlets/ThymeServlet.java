package com.thymeleafready.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Base servlet with template resolver and template engine instantiation. Extends this servlet to have template processing available.
 */
public class ThymeServlet extends HttpServlet {
	private static final long serialVersionUID = 4285574099016766167L;

	private TemplateEngine templateEngine;

	@Override
	public void init() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false); // cacheble=false during development

		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
	}

	/**
	 * Method calls {@link ITemplateEngine.process(final String template, final IContext context, final Writer writer)}
	 * @param template
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void process(String template, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		IContext ctx = new WebContext(req, resp, req.getServletContext());
		templateEngine.process(template, ctx, resp.getWriter());
	}
}
