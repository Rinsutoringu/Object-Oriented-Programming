package database.errorhandle;

import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import com.mysql.cj.exceptions.CJException;

import database.error.*;
import local.error.*;

public interface DBConnectionErrorHandler {

    void handleError(DBConnectError error);

    void handleError(DBStatusError error);

    void handleError(SQLException error);

    void handleError(SQLTimeoutException error);

    void handleError(UserInfoError error);

    void handleError(AuthFailed error);

    void handleError(CJException error);

    void handleError(NullPointerException error);

    void handleOtherError(Exception error);
}