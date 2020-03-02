package webboard.servlet.mysql;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
public class deluser {
 
	public static void userDEL(Connection conn, Integer user_id, String user_name) throws SQLException {
        String sql = "Delete From webboard.user_account where USER_ID= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, user_id);
 
        pstm.executeUpdate();
        System.out.println("(" + user_name + ") removed from the database ");
    }
}