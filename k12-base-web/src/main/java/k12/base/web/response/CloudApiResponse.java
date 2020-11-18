package k12.base.web.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.function.Function;

/**
 * Created by kinginblue on 2017/7/17.
 */
@Data
public class CloudApiResponse<T> {

    // 自定义状态码
    private int code = StatusCode.SUCCESS;

    // 提示信息
    private String msg = "";

    // 返回数据域
    private T data;

    // data setter overload: apply Converter to data

    /**
     * 设置数据域：converter 转换后的数据
     * @param data 原始数据
     * @param converter 转换器
     * @param <D> 原始数据类型
     */
    public <D> void setData(D data, Converter<D, T> converter) {
        this.setData(converter.convert(data));
    }

    /**
     * 设置数据域：converter 转换后的 page 数据
     * @param page 原始数据 page
     * @param function page 内元素转换器
     * @param <D> 原始数据类型
     * @param <TO> page 内元素转换后的类型
     */
    @SuppressWarnings("unchecked")
    public <D, TO> void setData(Page<D> page, Function<D, TO> function) {
        Page<TO> convertedPage = page.map(function);
        this.setData((T) convertedPage);
    }

    // data setter overload: apply Projection to data

    // using SpEL expression Projection
    @JsonIgnore
    private final ProjectionFactory projectionFactory = new SpelAwareProxyProjectionFactory();

    /**
     * 设置数据域：应用 projection 投影后的数据
     * @param data 原始数据
     * @param projection 投影
     * @param <D> 原始数据类型
     */
    public <D> void setData(D data, Class<? extends T> projection) {
        this.setData(projectionFactory.createProjection(projection, data));
    }

    /**
     * 设置数据域：应用 projection 投影后的数据
     * @param page 原始数据 page
     * @param projection page 内元素投影
     * @param <D> 始数据类型
     * @param <TO> page 内元素投影后的类型
     */
    @SuppressWarnings("unchecked")
    public <D, TO> void setData(Page<D> page, Class<? extends TO> projection) {
        Page<TO> convertedPage = page.map(data -> projectionFactory.createProjection(projection, data));
        this.setData((T) convertedPage);
    }

    // set bundle

    /**
     * 设置失败数据
     * @param msg 提示信息
     * @param data 数据
     */
    public void setSuccess(String msg, T data) {
        this.setCode(StatusCode.SUCCESS);
        this.setMsg(msg);
        this.setData(data);
    }

    public void setTokenValid(String msg, T data) {
        this.setCode(StatusCode.TOKEN_EXPIRE);
        this.setMsg(msg);
        this.setData(data);
    }

    /**
     * 设置成功数据
     * @param msg 提示信息
     * @param data 数据
     */
    public void setFailure(String msg, T data) {
        this.setCode(StatusCode.FAILURE);
        this.setMsg(msg);
        this.setData(data);
    }

    /**
     * 强制客户端重新登录
     * @param msg 提示信息
     */
    public void setForceReLogin(String msg) {
        this.setCode(StatusCode.FORCE_RE_LOGIN);
        this.setMsg(msg);
    }

    /**
     * 响应状态码
     */
    private static class StatusCode {
        // 成功
        static final int SUCCESS = 0;
        // 失败
        static final int FAILURE = -1;
        // 信息过期，强制重新登录
        static final int FORCE_RE_LOGIN = -2;

        static final int TOKEN_EXPIRE = 401;
    }

}
