package com.thymeleafready.servlets;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.messageresolver.IMessageResolver;

/**
 * MyMessageResolver uses ResourceBundle for searching messages.
 */
public class MyMessageResolver implements IMessageResolver {

	private ResourceBundle bundle;

	public MyMessageResolver() {
		// properties file is places in /src/main/resources
		bundle = ResourceBundle.getBundle("messages");
	}

	@Override
	public String getName() {
		return "MyMessageResolver";
	}

	@Override
	public Integer getOrder() {
		// setting priority for message resolver - lower order is higher priority
		return 0;
	}

	@Override
	public String resolveMessage(ITemplateContext context, Class<?> origin, String key, Object[] messageParameters) {
		try {
			String msg = bundle.getString(key);
			return MessageFormat.format(msg, messageParameters);
		} catch (MissingResourceException | NullPointerException e) {
			// If there is no message for given key resolver returns null.
			// Chain of resolvers then continue with next lower-priority resolvers.
			return null;
		}
	}

	@Override
	public String createAbsentMessageRepresentation(ITemplateContext context, Class<?> origin, String key,
			Object[] messageParameters) {
		return "no message found for key '" + key + "'";
	}

}
