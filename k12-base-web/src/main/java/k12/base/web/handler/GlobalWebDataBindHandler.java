package k12.base.web.handler;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * {@code @ControllerAdvice} + {@code @InitBinder} ，对 Http 请求中 String 参数进行 trim() 处理
 */
@ControllerAdvice
public class GlobalWebDataBindHandler {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}
