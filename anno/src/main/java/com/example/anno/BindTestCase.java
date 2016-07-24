package com.example.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by grass on 16/7/23.
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface BindTestCase {
    String name();

    /**
     * 0 fragment
     * 1 activity
     *
     * @return
     */
    int type();

    String des();
}
