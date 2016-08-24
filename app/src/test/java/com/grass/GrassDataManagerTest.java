package com.grass;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import com.grass.db.GrassAccount;
import com.grass.db.GrassAccountManager;

import android.app.Application;
import android.os.Build;

/**
 * Created by grass on 16/8/18.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.JELLY_BEAN)
public class GrassDataManagerTest {

    private GrassAccountManager mDataAccountManager;

    @Before
    public void setup() {
        Application application = RuntimeEnvironment.application;
        mDataAccountManager = new GrassAccountManager(application);
    }

    @Test
    public void testAdd() {
        for (int i = 0; i < 10; i++) {
            GrassAccount first = new GrassAccount("grass--" + i, "123456");
            mDataAccountManager.createOrUpdate(first);
        }
        int count = mDataAccountManager.getCount();
        Assert.assertEquals(count, 1);
    }
}
