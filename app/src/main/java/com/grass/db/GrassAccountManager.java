package com.grass.db;

import java.sql.SQLException;

import com.grass.db.ormlite.GrassDataBaseHelper;
import com.j256.ormlite.dao.Dao;

import android.content.Context;

/**
 * Created by grass on 16/8/18.
 */

public class GrassAccountManager {
    private Context mContext;
    private Dao<GrassAccount, String> mAccountDao;

    public GrassAccountManager(Context context) {
        this.mContext = context;
        mAccountDao = GrassDataBaseHelper.getInstance(context).getAccountDao();
    }

    public void createOrUpdate(GrassAccount item) {
        if (mAccountDao != null) {
            try {
                mAccountDao.createOrUpdate(item);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCount() {
        if (mAccountDao != null) {
            try {
                return (int) mAccountDao.countOf();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
