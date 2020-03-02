package webboard.servlet.Notices.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webboard.tabl.Notices;


public class Info {
	
	public static List<Notices> infonotices(Connection conn) throws SQLException {
        String sql = "Select notiñes.NOTIÑES_ID, user_account.USER_NAME, notiñes.DATE, notiñes.CONTENT " + // 
                      "from webboard.notiñes, webboard.user_account " + //
                      "where notiñes.USER_ID=user_account.USER_ID";
        System.out.println("Search info notices in DB");
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Notices> list = new ArrayList<Notices>();
        while (rs.next()) {
        	
        	Integer notices_id = rs.getInt("NOTIÑES_ID");
        	String user_name = rs.getString("USER_NAME");
            String notices_date = rs.getString("DATE");
            String content = rs.getString("CONTENT");
            String roles = null;
            
            Notices notices = new Notices(notices_id, user_name, notices_date, content, roles);
            
            notices.setnotices_id(notices_id);
            notices.setuser_name(user_name);
            notices.setnotices_date(notices_date);
            notices.setcontent(content);
            notices.setroles(roles);
            list.add(notices);
        }
        return list;	
}
	
	public static Integer find2(Connection conn, //
			Integer NOTIÑES_ID) throws SQLException {
 
        String sql = "Select USER_ID from webboard.notiñes where NOTIÑES_ID = ?";
        System.out.println("Find (" + NOTIÑES_ID + ") in DB");
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, NOTIÑES_ID);
        ResultSet rs = pstm.executeQuery();
        Integer Finduser_id = null;
        while (rs.next()) {
        Finduser_id = rs.getInt("USER_ID");
        }
        System.out.println("Finduser_id: " + Finduser_id);
        return Finduser_id; 
	}
}
	
