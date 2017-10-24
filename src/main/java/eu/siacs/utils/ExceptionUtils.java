package eu.siacs.utils;

public class ExceptionUtils {
    public static Throwable getRootCause(Throwable e) {
        Throwable cause;
        Throwable result = e;

        while(null != (cause = result.getCause())  && (result != cause) ) {
            result = cause;
        }
        return result;
    }
}
