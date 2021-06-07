package com.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author mucd
 * 2021年06月05日  上午 12:58
 */

public class Utils {
    static DataSource dataSource = new DruidDataSource();
    static Properties properties = new Properties();
    static {
        try {
            properties.load(Utils.class.getClassLoader().getResourceAsStream("druid.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDataSource(){
        try {
            return dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            System.out.println(Objects.requireNonNull(getDataSource()).getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
