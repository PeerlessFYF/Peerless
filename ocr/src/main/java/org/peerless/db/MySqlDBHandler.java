package org.peerless.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

/**
 * MySQL DB Handler
 * 
 * @author yinfeng.fyf
 */
public class MySqlDBHandler {
	
    /**
     * 插入数据
     * 
     * @param sql
     */
    public boolean insert(String tableName, Map<String, Object> map) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
        	int count = 0;
        	StringBuilder colKey = new StringBuilder("");
        	StringBuilder colValue = new StringBuilder("");
        	for (String col : map.keySet()) {
        		if (count > 0) {
        			colKey.append(",");
        			colValue.append(",");
        		}
        		colKey.append(col);
        		Object val = map.get(col);
        		if (val instanceof String) {
        			colValue.append("'" + val + "'");
        		} else {
        			colValue.append(val);
        		}
        		count++;
        	}
        	
        	StringBuilder sql = new StringBuilder("");
        	sql.append("insert into " + tableName + " (" + colKey.toString() + ") values (" + colValue.toString() + ")");
        	
            conn = MySqlDBFactory.getConnection();
            
            pstm = conn.prepareStatement(sql.toString());
            flag = pstm.execute();
            
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
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
