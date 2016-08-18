package com.util;

import java.sql.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 * 日期转换器
 * 
 * @author lmiky
 * @date 2014-1-26
 */
public class SqlTimestampConverter implements Converter {

	public SqlTimestampConverter() {

		this.defaultValue = null;
		this.useDefault = false;
	}

	public SqlTimestampConverter(Object defaultValue) {

		this.defaultValue = defaultValue;
		this.useDefault = true;
	}

	private Object defaultValue = null;

	private boolean useDefault = true;

	public Object convert(Class type, Object value) {

		if (value == null || "".equals(value)) {
			if (useDefault) {
				return (defaultValue);
			} else {
				throw new ConversionException("No value specified");
			}
		}

		if (value instanceof Date) {
			return (value);
		}

		try {
			return (Date.valueOf(value.toString()));
		} catch (Exception e) {
			if (useDefault) {
				return (defaultValue);
			} else {
				throw new ConversionException(e);
			}
		}
	}

}