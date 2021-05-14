package step3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Test {
	public static void main(String[] args) throws SQLException {
		Connection conn = Database.getConnection();
		PreparedStatement st = conn.prepareStatement("Insert into table_name(name,id,gender,year) values(?,?,?,?)");
		st.setString(1,"hey");
		st.setString(2, "hey");
		st.setString(3, "hey");
		st.setString(4, "hey");
		st.execute();
	}
}