package com.grass.demo.retrofit;

import java.util.ArrayList;

import android.support.annotation.IntDef;

/**
 * Created by mingjun on 16/7/20.
 */
public interface TrendingApi {

    int LANG_JAVA = 1;
    int LANG_OC = 2;
    int LANG_SWIFT = 3;
    int LANG_HTML = 4;
    int LANG_PYTHON = 5;
    int LANG_BASH = 6;

    @IntDef({
            LANG_JAVA,
            LANG_OC,
            LANG_SWIFT,
            LANG_HTML,
            LANG_PYTHON,
            LANG_BASH
    })
    @interface LanguageType{

    }

    /**
     * Get trending repo base on type.
     * @param language
     * @return
     */
    retrofit2.Call<ArrayList<TrendingRepo>> getTrendingRepo(@LanguageType int language);
}
