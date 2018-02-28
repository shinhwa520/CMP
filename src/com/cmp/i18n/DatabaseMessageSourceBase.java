package com.cmp.i18n;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;

import com.cmp.dao.I18nDAO;
import com.cmp.model.I18n;


@Named("databaseMessageSourceBase")
public class DatabaseMessageSourceBase extends AbstractMessageSource {
	private static Log log = LogFactory.getLog(DatabaseMessageSourceBase.class);
    @Autowired
	protected DatabaseMessageSourceBase messageSource;
	@Autowired
	private I18nDAO i18nDAO;
	
	private Messages messages;

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		String msg = messages.getMessage(code, locale);
		return createMessageFormat(msg, locale);
	}
	
	@PostConstruct
	public void init() {
		this.messages = new Messages();
		reloadMessagesBundle();
	}
	
	public void reloadMessagesBundle() {
		for(I18n i : i18nDAO.listI18n()){
			messages.addMessage(i.getKey(), LocaleUtils.toLocale(i.getLocale()), i.getValue());
		}
	}

	
	/**
	 * 
	 * Messages bundle
	 */
	protected static final class Messages {

		/* <code, <locale, message>> */
		private static Map<String, Map<Locale, String>> messages;

		public void addMessage(String code, Locale locale, String msg) {
			if (messages == null)
				messages = new HashMap<String, Map<Locale, String>>();

			Map<Locale, String> data = messages.get(code);
			if (data == null) {
				data = new HashMap<Locale, String>();
				messages.put(code, data);
			}
			data.put(locale, msg);
		}

		public String getMessage(String code, Locale locale) {
			Map<Locale, String> data = messages.get(code);
			return data != null ? data.get(locale) : null;
		}
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}

}
