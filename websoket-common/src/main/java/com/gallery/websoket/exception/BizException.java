package com.gallery.websoket.exception;

import com.gallery.websoket.enums.ResultCodeEnum;
import com.gallery.websoket.result.R;
import lombok.Getter;
/*
* 自定义业务异常
*
* @date 2021/6/8 16:33
*/
@Getter
public class BizException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private R apiRes;

	/** 业务自定义异常 **/
	public BizException(String msg) {
		super(msg);
		this.apiRes = R.customFail(msg);
	}

	public BizException(ResultCodeEnum apiCodeEnum, String customMsg) {
		super();
		apiRes = R.fail(apiCodeEnum, customMsg);
	}

	public BizException(R apiRes) {
		super(apiRes.getMsg());
		this.apiRes = apiRes;
	}
}
