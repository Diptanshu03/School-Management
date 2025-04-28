package com.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Entity.Student;

public class StdService {
	private static String url = "jdbc:postgresql://localhost:5432/schoolmgm?user=postgres&password=123";
	private static Connection cn;
	static {
		try {
			Class.forName("org.postgresql.Driver");
			cn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int add(int sid, String sname, int age, String phno, String gender) {
		int res = 0;
		
		String sql = "insert into student values(?,?,?,?,?)";
		try {
			PreparedStatement st = cn.prepareStatement(sql);
			st.setInt(1, sid);
			st.setString(2, sname);
			st.setInt(3, age);
			st.setString(4, phno);
			st.setString(5, gender);
			
			res = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Student> display(){
		List<Student> std = new ArrayList<>();
		
		String sql = "select * from student";
		try {
			Statement st = cn.createStatement();
			ResultSet res = st.executeQuery(sql);
			while(res.next()) {
				int sid = res.getInt(1);
				String sname = res.getString(2);
				int age = res.getInt(3);
				String phno = res.getString(4);
				String gender = res.getString(5);
				std.add(new Student(sid, sname, gender, phno, gender));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return std;
	}
	
	public int update(int sid, String sname, int age, String phno, String gender) {
		int res = 0;
		
		String sql = "update student set sname = ?, age = ?, phno = ?, gender = ? where sid = ?";
		try {
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1, sname);
			st.setInt(2, age);
			st.setString(3, phno);
			st.setString(4, gender);
			st.setInt(5, sid);
			
			res = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public int delete(int sid) {
		int res = 0;
		
		String sql = "delete from student where sid = ?";
		try {
			PreparedStatement st = cn.prepareStatement(sql);
			st.setInt(1, sid);
			res = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static void closeConnection() {
        try {
            if (cn != null && !cn.isClosed()) {
                cn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
