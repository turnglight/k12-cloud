package k12.base.web.util;

import k12.base.web.exception.BusinessException;
import org.springframework.validation.Errors;

import java.util.function.Predicate;

/**
 * 业务工具类
 */
public class BSUtil {

    public static final String ILLEGAL_OPERATION = "illegal operation!";

    public static final String FAILED_OPERATION = "操作失败!";

    public static void error(String error) {
        throw new BusinessException(error);
    }

    public static <T> void isTrue(T arg, Predicate<T> predicate, String error) {
        if (!predicate.test(arg)) {
            error(error);
        }
    }

    public static void isTrue(boolean expression, String error) {
        if (!expression) {
            error(error);
        }
    }

    public static void isNull(Object object, String error) {
        if (object != null) {
            error(error);
        }
    }

    public static void notNull(Object object, String error) {
        if (object == null) {
            error(error);
        }
    }

    /**
     * 校验 Controller 层数据 @Validated 的结果
     */
    public static void controllerValidate(Errors... errorsList) {
        for (Errors errors : errorsList) {
            isTrue(!errors.hasErrors(), errors.hasErrors() ? errors.getFieldError().getDefaultMessage() : "");
        }
    }

    /**
     * 校验 数据库增删改的操作结果影响行数大于0
     */
    public static void checkDbCrdGtZero(long result, String error) {
        isTrue(result, (i) -> i > 0, error + " !> 0");
    }

    /**
     * 正则校验
     *
     * @param input
     * @param regexp
     * @param error
     */
    public static void validateRegexp(String input, String regexp, String error) {
        isTrue(input.matches(regexp), error);
    }
}
