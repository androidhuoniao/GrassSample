package com.grass.db.ormlite;

import java.sql.SQLException;

import com.grass.db.GrassAccount;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.orhanobut.logger.Logger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by grass on 16/8/18.
 */

public class GrassDataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "grass.db";
    private static final int DATABASE_VERSION = 1;

    private static volatile GrassDataBaseHelper mInstance;
    private Dao<GrassAccount, String> mAccountDao;

    public GrassDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Dao<GrassAccount, String> getAccountDao() {
        if (mAccountDao == null) {
            try {
                mAccountDao = getDao(GrassAccount.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mAccountDao;
    }

    public static GrassDataBaseHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (GrassDataBaseHelper.class) {
                if (mInstance == null) {
                    mInstance = new GrassDataBaseHelper(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, GrassAccount.class);
        } catch (SQLException e) {
            Logger.i("can not create database ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }
}
