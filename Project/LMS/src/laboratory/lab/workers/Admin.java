package laboratory.lab.workers;

import database.db.DataBase;
import standard.GlobalVariables;

public class Admin {

    DataBase dbUtils = DataBase.getInstance(); 

    public void setAdmin() {
        String username = GlobalVariables.getUserName();
        dbUtils.setUserPermission(username, 2);
    }

}
