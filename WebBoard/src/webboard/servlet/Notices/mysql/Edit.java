package webboard.servlet.Notices.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import webboard.tabl.Notices;


public class Edit {
 
	public static void noticesEDIT(Connection conn, Notices edit_noti�es) throws SQLException {
        String sql = "Update notices set user_id=(SELECT user_id From user_account WHERE user_name=?), date=?, content =? where notices_id=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        System.out.println("pstm - " + pstm);
        pstm.setString(1, edit_noti�es.getuser_name());
        pstm.setString(2, edit_noti�es.getnotices_date());
        pstm.setString(3, edit_noti�es.getcontent());
        pstm.setInt(4, edit_noti�es.getnotices_id());
        
        pstm.executeUpdate();
    }
}