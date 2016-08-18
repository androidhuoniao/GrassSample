package com.grass.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by grass on 16/8/18.
 */

@DatabaseTable(tableName = "accounts")
public class GrassAccount {

    @DatabaseField(id = true)
    private String name;
    @DatabaseField
    private String password;

    public GrassAccount() {
        // ORMLite needs a no-arg constructor
    }

    public GrassAccount(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
