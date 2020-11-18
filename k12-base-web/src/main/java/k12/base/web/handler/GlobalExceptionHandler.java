package k12.base.web.handler;

import k12.base.web.exception.BusinessException;
import k12.base.web.exception.TokenException;
import k12.base.web.response.CloudApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * {@code @ControllerAdvice} + {@code @ExceptionHandler} 实现全局的 Controller 层的异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${api.debug:false}")
    private boolean apiDebug = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(TokenException.class)
    @ResponseBody
    CloudApiResponse<?> handleTokenException(TokenException e){
        LOGGER.error(e.getMessage(), e);

        CloudApiResponse<?> apiResponse = new CloudApiResponse<>();
        apiResponse.setTokenValid(apiDebug ? e.toString() : "用户token失效或者User请求头异常！", null);
        return apiResponse;
    }


    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    CloudApiResponse<?> handleException(Exception e){
        LOGGER.error(e.getMessage(), e);

        CloudApiResponse<?> apiResponse = new CloudApiResponse<>();
        apiResponse.setFailure(apiDebug ? e.toString() : "操作失败！", null);
        return apiResponse;
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    CloudApiResponse<?> handleBusinessException(BusinessException e){
        LOGGER.error(e.getMessage(), e);

        CloudApiResponse<?> apiResponse = new CloudApiResponse<>();
        apiResponse.setFailure(e.getMessage(), null);
        return apiResponse;
    }

    /**
     * 处理所有接口验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    CloudApiResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        LOGGER.error(e.getMessage(), e);

        CloudApiResponse<?> apiResponse = new CloudApiResponse<>();
        apiResponse.setFailure(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null);
        return apiResponse;
    }

}
