package com.gallery.websoket.exception;

import com.gallery.websoket.enums.ResultCodeEnum;
import com.gallery.websoket.result.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/*
* 异常信息自定义返回数据
*
* @date 2021/6/8 16:30
*/
@Configuration
public class BizExceptionResolver implements HandlerExceptionResolver {

	private Logger logger = LogManager.getLogger(BizExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
										 Exception ex) {


		// 是否包含ss框架
		boolean hasSpringSecurity = false;
		try {
			hasSpringSecurity = Class.forName("org.springframework.security.access.AccessDeniedException") != null;
		} catch (Exception e) {
		}

		String outPutJson;

		//业务异常
        if(ex instanceof BizException) {
        	logger.error("公共捕捉[Biz]异常：{}",ex.getMessage());
			outPutJson = ((BizException) ex).getApiRes().toJSONString();
        }else if(ex instanceof DataAccessException){
			logger.error("公共捕捉[DataAccessException]异常：",ex);
			outPutJson = R.fail(ResultCodeEnum.DB_ERROR).toJSONString();
		}else if(hasSpringSecurity && ex instanceof AccessDeniedException) {
			logger.error("公共捕捉[AccessDeniedException]异常：", ex);
			outPutJson = R.fail(ResultCodeEnum.SYS_PERMISSION_ERROR, ex.getMessage()).toJSONString();
		}else{
			logger.error("公共捕捉[Exception]异常：",ex);
			outPutJson = R.fail(ResultCodeEnum.SYSTEM_ERROR, ex.getMessage()).toJSONString();
		}
        try {
	   			this.outPutJson(response, outPutJson);
   		} catch (IOException e) {
   			logger.error("输出错误信息异常:", e);
   		}

   		return new ModelAndView();
	}


	public void outPutJson(HttpServletResponse res, String jsonStr) throws IOException {
		res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		res.getWriter().write(jsonStr);
		res.getWriter().flush();
		res.getWriter().close();
	}

}
