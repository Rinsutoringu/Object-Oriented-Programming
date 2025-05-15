package database.errorhandle;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import database.error.*;
import local.error.*;

/**
 * 异常捕获类
 * 主要用于捕获数据库操作中的异常
 * 超级多合一，需要根据异常类型调用相应的处理方法
 * 在DBConnectionErrorhandler接口中声明
 * 在errorHandler类中实现
 */
public class CatchException {
    public static void handle(Exception e, errorHandler eh) {
        System.err.println("An error occurred: " + e.getMessage());
        e.printStackTrace(); // 打印完整的堆栈信息
        eh.handleOtherError(e);
        // 根据异常类型调用相应的处理方法
        if (e instanceof DBConnectError) {
            eh.handleError((DBConnectError) e);

        } else if (e instanceof DBStatusError) {
            eh.handleError((DBStatusError) e);

        } else if (e instanceof SQLException) {
            eh.handleError((SQLException) e);
            
        } else if (e instanceof SQLTimeoutException) {
            eh.handleError((SQLTimeoutException) e);

        } else if (e instanceof UserInfoError) {
            eh.handleError((UserInfoError) e);

        } else if (e instanceof AuthFailed) {
            eh.handleError((AuthFailed) e);

        } else {
            eh.handleOtherError(e);
        }
    }
}
