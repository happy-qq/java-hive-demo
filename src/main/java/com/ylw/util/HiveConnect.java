package com.ylw.util;

import com.ylw.constant.Const;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hive连接工具类
 *
 * @author : YangLinWei
 * @createTime: 2022/2/23 9:22 上午
 */
public class HiveConnect {

    private static Connection connection = null;

    /**
     * 获取hive连接单例
     *
     * @return hive连接单例
     */
    public static Connection getConnection() {
        if (null == connection) {
            synchronized (HiveConnect.class) {
                if (null == connection) {
                    try {
                        Class.forName("org.apache.hive.jdbc.HiveDriver");
                        HiveConnect.connection = DriverManager.getConnection(Const.HIVE_DB_URL, Const.USER_NAME, Const.PASSWORD);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }



}
