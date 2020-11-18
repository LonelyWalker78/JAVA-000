package com.lincj.homework.lesson10.homework6.Repository;

import com.lincj.homework.lesson10.homework6.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private DataSource dataSource;

    public void insert(Student student) {
        String sql = "INSERT INTO student(id, name, number) VALUES('" + student.getId() + "', '" + student.getName() + "', " + student.getNumber() + ")";
        jdbcTemplate.execute(sql);
    }

    /*public void update(Student student) {
        String sql = "update student set number = " + student.getNumber() + " where id = '" + student.getId() + "'";
        jdbcTemplate.execute(sql);
    }*/

    public void update(Student student) throws SQLException {
        String sql = "UPDATE student SET number = ?  WHERE id = ? ";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, student.getNumber());
        preparedStatement.setString(2, student.getId());
        preparedStatement.executeUpdate();
    }
}
