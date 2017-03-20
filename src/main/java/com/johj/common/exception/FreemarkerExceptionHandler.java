package com.johj.common.exception;

import java.io.Writer;

import org.apache.log4j.Logger;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(FreemarkerExceptionHandler.class);

	@Override
	public void handleTemplateException(TemplateException arg0,
			Environment arg1, Writer arg2) throws TemplateException {
		logger.info("Freemaker模板引擎统一异常处理。");
		logger.info("异常信息：" + arg0.getLocalizedMessage());
		arg0.printStackTrace();
	}

}
