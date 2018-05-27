package com.example.yealm.takego1.model.backend;

import com.example.yealm.takego1.model.datasource.ListDS;
import com.example.yealm.takego1.model.datasource.MySQL_DBmanger;

/**
 * Created by yealm on 16/11/2017.
 */

public class DBmanagerFactory
{

        static DB_manager manager = null;

        public static DB_manager getManager() {
            if (manager == null)
                manager= new MySQL_DBmanger();
                //manager = new ListDS();
            return manager;
        }

}
