package com.ylw;

import com.ylw.util.HiveConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * hive ddl测试
 *
 * @author : YangLinWei
 * @createTime: 2022/2/23 9:30 上午
 */
public class HiveTest {


    public static boolean createEmployeeTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " +
                "employee(name string," +
                "work_place array<string>," +
                "sex_age struct<sex:string, age:int>," +
                "score map<string, int>," +
                "depart_title map<string, string>) " +
                "row format delimited " +
                "fields terminated by '|' " +
                "collection items terminated by ',' " +
                "map keys terminated by ':'" +
                "lines terminated by '\n' " +
                "stored as textfile";
        Statement statement = HiveConnect.getConnection().createStatement();
        return statement.execute(sql);
    }

    public static boolean loadData() throws SQLException {
        String loadSql = "load data local inpath '本地路径/employ.txt' into table employee";
        Statement statement = HiveConnect.getConnection().createStatement();
        return statement.execute(loadSql);
    }

    public static void query() throws SQLException {
        String sql = "select name ,work_place[0] from employee";
        PreparedStatement pstm = HiveConnect.getConnection().prepareStatement(sql);
        ResultSet rs = pstm.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getString(1) + "	" + rs.getString(2));
        }
        pstm.close();
        rs.close();
    }


    public static void main(String[] args) throws SQLException {

        //createEmployeeTable();
        //loadData();
        query();
    }

}
