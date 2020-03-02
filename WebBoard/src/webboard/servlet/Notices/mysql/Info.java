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
        String sql = "Select noti�es.NOTI�ES_ID, user_account.USER_NAME, noti�es.DATE, noti�es.CONTENT " + // 
                      "from webboard.noti�es, webboard.user_account " + //
                      "where noti�es.USER_ID=user_account.USER_ID";
        System.out.println("Search info notices in DB");
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Notices> list = new ArrayList<Notices>();
        while (rs.next()) {
        	
        	Integer notices_id = rs.getInt("NOTI�ES_ID");
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
			Integer NOTI�ES_ID) throws SQLException {
 
        String sql = "Select USER_ID from webboard.noti�es where NOTI�ES_ID = ?";
        System.out.println("Find (" + NOTI�ES_ID + ") in DB");
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, NOTI�ES_ID);
        ResultSet rs = pstm.executeQuery();
        Integer Finduser_id = null;
        while (rs.next()) {
        Finduser_id = rs.getInt("USER_ID");
        }
        System.out.println("Finduser_id: " + Finduser_id);
        return Finduser_id; 
	}
}
	
