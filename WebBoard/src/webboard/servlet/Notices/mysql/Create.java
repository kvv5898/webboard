package webboard.servlet.Notices.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import webboard.tabl.Notices;


public class Create {
	
	public static void notscreate(Connection conn, Notices noticescreate) throws SQLException {
        String sql = "Insert into webboard.notiñes(user_id,  DATE, CONTENT) " + //
        		     "values ((SELECT user_id From user_account WHERE USER_NAME=?), " + //
        		    " ?, ?)";
 
        PreparedStatement add = conn.prepareStatement(sql);
 
        add.setString(1, noticescreate.getuser_name());
        add.setString(2, noticescreate.getnotices_date());
        add.setString(3, noticescreate.getcontent());

 
        add.executeUpdate();
	
	
}
}