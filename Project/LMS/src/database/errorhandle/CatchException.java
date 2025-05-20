package database.errorhandle;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import com.mysql.cj.exceptions.CJException;

import database.error.*;
import local.error.*;

public class CatchException {
    public static void handle(Exception e, errorHandler eh) {
        System.err.println("An error occurred: " + e.getMessage());
        e.printStackTrace();
        eh.handleOtherError(e);
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
            
        } else if (e instanceof CJException) {
            eh.handleError((CJException) e);

        } else if (e instanceof NullPointerException) {
            eh.handleError((NullPointerException) e);

        } else {
            eh.handleOtherError(e);
        }
    }
}
