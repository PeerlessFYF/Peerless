package org.peerless.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * MySQL DB Handler
 * 
 * @author yinfeng.fyf
 */
public class MySqlDBHandler {

    /**
     * 查询数据
     * 
     * @param sql
     */
    public boolean queryData(String sql) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = MySqlDBFactory.getConnection();
            
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String agentId = rs.getString("AgentId");
                
            }
            
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

}
