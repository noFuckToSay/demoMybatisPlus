package com.example.demoMybatisPlus.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.Serializable;

/**
 * <h2>web api通用封装类</h2>
 * <p>
 * 在后端返回json相关数据的时候，会通过该类，封装原始结果集。<br/>
 * 习惯成自然，这样的设计是为了兼容之前已经做的内容。<br/>
 * 实际上，标准的RESTful请求，不会通过这种封装类的方式封装返回参数。具体可以参照
 * </p>
 *
 * @author： liuzhilong
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AjaxResult<T> implements Serializable {
    // 自定义返回码。通常参照http的code标准进行封装一遍；实际使用中，可以自定义业务编码
    protected int code = 200;
    // 标志本次请求是否成功，即逻辑通顺，没有异常
    protected boolean success = true;
    // 承接原始参数
    protected T data = null;
    // 提示消息
    protected String msg = null;

    // 错误堆栈，一般在新功能上线的时候，启用该字段，保存业务流程中出现的异常信息
    private String[] errorStackTrace;


    public AjaxResult<T> errorStack(Throwable throwable) {
        try {
            setErrorStackTrace(ExceptionUtils.getRootCauseStackTrace(throwable));
        } catch (Exception e) {
            // ignore
        }
        return this;
    }


    public AjaxResult() {

    }

    protected AjaxResult(boolean success, T data) {
        this.code = success ? 200 : 500;
        this.data = data;
        this.success = success;
    }


    public AjaxResult(String msg) {
        this.success = false;
        this.code = 500;
        this.msg = msg;
    }

    public AjaxResult(int code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(T obj, String msg) {
        this.success = false;
        this.data = obj;
        this.msg = msg;
    }

    public static <T> AjaxResult<T> error(String msg) {
        return new AjaxResult<>(msg);
    }

    public static <T> AjaxResult<T> error(String msg, Throwable throwable) {
        AjaxResult<T> result = error(msg);
        result.errorStack(throwable);
        return result;
    }

    public static <T> AjaxResult<T> error(int code, String msg, Throwable throwable) {
        AjaxResult<T> result = error(code, msg);
        result.errorStack(throwable);
        return result;
    }

    public static <T> AjaxResult<T> error(int code, T data, String msg, Throwable throwable) {
        AjaxResult<T> result = error(code, data, msg);
        result.errorStack(throwable);
        return result;
    }

    public static <T> AjaxResult<T> error(int code, String msg) {
        return new AjaxResult<>(code, msg);
    }

    public static <T> AjaxResult<T> error(int code, T data, String msg) {
        return new AjaxResult<T>(code, msg).setData(data);
    }

    public static <T> AjaxResult<T> ok(T data) {
        return new AjaxResult<>(true, data);
    }

    public static <T> AjaxResult<T> ok(int code, T data) {
        return new AjaxResult<>(true, data).setCode(code);
    }

    public static <T> AjaxResult<T> ok(String message, T data) {
        return new AjaxResult<>(true, data).setMsg(message);
    }

    public static <T> AjaxResult<T> ok() {
        return ok(null);
    }

    // 通用的获取内容
    public static final <T> T trimResultWithThrow(AjaxResult<T> ajaxResult) {
        if (!ajaxResult.isSuccess()) {
            throw new ServiceException(ajaxResult.getMsg());
        }
        return ajaxResult.getData();
    }


}
