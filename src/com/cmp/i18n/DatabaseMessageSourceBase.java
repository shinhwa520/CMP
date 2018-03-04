package com.cmp.i18n;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;

import com.cmp.dao.I18nDAO;
import com.cmp.model.I18n;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;


@Named("databaseMessageSourceBase")
public class DatabaseMessageSourceBase extends AbstractMessageSource implements ResourceLoaderAware {
	private static Log log = LogFactory.getLog(DatabaseMessageSourceBase.class);
//    @Autowired
//	protected DatabaseMessageSourceBase messageSource;
	@Autowired
	private I18nDAO i18nDAO;
	
//	private Messages messages;

	/**
	 * Map切分字符
	 */
	protected final String MAP_SPLIT_CODE = "|";
    protected final String DB_SPLIT_CODE = "_";

	private final Map<String, String> properties = new HashMap<String, String>();

	@SuppressWarnings("unused")
	private ResourceLoader resourceLoader;

	@PostConstruct
	public void init() {
		reload();
	}

//	@PostConstruct
//	public void init() {
//		this.messages = new Messages();
//		reloadMessagesBundle();
//	}

	public void reload() {
		properties.clear();
		properties.putAll(loadTexts());
	}

	public List<I18n> getResource() {
		return i18nDAO.listI18n();
	}

	/**
	 *
	 * 描述：TODO
	 * 加载数据
	 * @return
	 */
	protected Map<String, String> loadTexts() {
		Map<String, String> mapResource = new HashMap<String, String>();
		List<I18n> resources = this.getResource();
		for (I18n item : resources) {
			String code = item.getKey() + MAP_SPLIT_CODE + item.getLocale();
			mapResource.put(code, item.getValue());
		}
		return mapResource;
	}

	/**
	 *
	 * 描述：TODO
	 * @param code
	 * @param locale 本地化语言
	 * @return
	 */
	private String getText(String code, Locale locale) {
		String localeCode = locale.getLanguage() + DB_SPLIT_CODE + locale.getCountry();
//        String localeCode = locale.toString();
		String key = code + MAP_SPLIT_CODE + localeCode;
		String localeText = properties.get(key);
		String resourceText = code;

		if(localeText != null) {
			resourceText = localeText;
		}
		else {
			localeCode = Locale.ENGLISH.getLanguage();
			key = code + MAP_SPLIT_CODE + localeCode;
			localeText = properties.get(key);
			if(localeText != null) {
				resourceText = localeText;
			}
			else {
				try {
					if(getParentMessageSource() != null) {
						resourceText = getParentMessageSource().getMessage(code, null, locale);
					}
				} catch (Exception e) {
					logger.error("Cannot find message with code: " + code);
				}
			}
		}
		return resourceText;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		String msg = getText(code, locale);
		return createMessageFormat(msg, locale);
	}

	@Override
	protected String resolveCodeWithoutArguments(String code, Locale locale) {
		String result = getText(code, locale);
		return result;
	}

//	@Override
//	protected MessageFormat resolveCode(String code, Locale locale) {
//		String msg = messages.getMessage(code, locale);
//		return createMessageFormat(msg, locale);
//	}
//
//	@Override
//	public void setResourceLoader(ResourceLoader resourceLoader) {
//		this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
//	}
//
//	@Override
//	protected String resolveCodeWithoutArguments(String code, Locale locale) {
//		String result = messages.getMessage(code, locale);
//		return result;
//	}

//	public void reloadMessagesBundle() {
//		for(I18n i : i18nDAO.listI18n()){
////			messages.addMessage(i.getKey(), LocaleUtils.toLocale(i.getLocale()), i.getValue());
//			System.out.println(i.getKey());
//		}
//	}

//	/**
//	 *
//	 * Messages bundle
//	 */
//	protected static final class Messages {
//
//		/* <code, <locale, message>> */
//		private static Map<String, Map<Locale, String>> messages;
//
//		public void addMessage(String code, Locale locale, String msg) {
//			if (messages == null)
//				messages = new HashMap<String, Map<Locale, String>>();
//
//			Map<Locale, String> data = messages.get(code);
//			if (data == null) {
//				data = new HashMap<Locale, String>();
//				messages.put(code, data);
//			}
//			data.put(locale, msg);
//		}
//
//		public String getMessage(String code, Locale locale) {
//			Map<Locale, String> data = messages.get(code);
//			return data != null ? data.get(locale) : null;
//		}
//	}
//
//	public Messages getMessages() {
//		return messages;
//	}
//
//	public void setMessages(Messages messages) {
//		this.messages = messages;
//	}

}
