package com.thymeleafready.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.thymeleafready.messageresolvers.MyMessageResolver;

/**
 * Base servlet with template resolver and template engine instantiation. Extends this servlet to have template processing available.
 */
public class ThymeServlet extends HttpServlet {
	private static final long serialVersionUID = 4285574099016766167L;

	private TemplateEngine templateEngine;

	@Override
	public void init() {
		// init of template resolver with project specific settings
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false); // cacheble=false during development

		Set<IMessageResolver> messageResolvers = new HashSet<>();
		// StandardMessageResolver searches in same directory like template with same name
		messageResolvers.add(new StandardMessageResolver());
		// custom message resolver searches in messages.properties
		messageResolvers.add(new MyMessageResolver());

		// init of template engine
		templateEngine = new TemplateEngine();
		templateEngine.setMessageResolvers(messageResolvers);
		templateEngine.setTemplateResolver(templateResolver);
	}

	/**
	 * Method calls {@link org.thymeleaf.ITemplateEngine#process()}
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
