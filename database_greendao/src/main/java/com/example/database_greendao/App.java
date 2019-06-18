package com.example.database_greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class App extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 生成了Session
         */
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "note-db");
        SQLiteDatabase db = helper.getWritableDatabase();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
