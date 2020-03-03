package webboard.servlet.Notices.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import webboard.tabl.Notices;


public class Edit {
 
	public static void noticesEDIT(Connection conn, Notices edit_notiñes) throws SQLException {
        String sql = "Update webboard.notiñes set (SELECT user_id From user_account WHERE USER_NAME=?), DATE=?, CONTENT =? where NOTIÑES_ID=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        System.out.println("pstm - " + pstm);
        pstm.setString(1, edit_notiñes.getuser_name());
        pstm.setString(2, edit_notiñes.getnotices_date());
        pstm.setString(3, edit_notiñes.getcontent());
        pstm.setInt(4, edit_notiñes.getnotices_id());
        
        pstm.executeUpdate();
    }
}