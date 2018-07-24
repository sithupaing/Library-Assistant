/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import library.system.database.Database;
import library.system.model.IssueInfo;

/**
 *
 * @author Sithu
 */
public class IssueDAO {

    public void saveIssueInfo(IssueInfo issueInfo) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();

        String sql = "insert into lbdb.issue (book_id,member_id,issue_date,renew_count) values (?,?,now(),0)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, issueInfo.getBookId());
        stmt.setInt(2, issueInfo.getMemberId());
        stmt.execute();

    }

    public IssueInfo getIssueInfo(int bookId) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();

        String sql = "select * from lbdb.issue where book_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, bookId);
        ResultSet result = stmt.executeQuery();
        
        IssueInfo issueInfo = null;
        if(result.next()){
            int memberId = result.getInt("member_id");
            Date issuedDate = result.getDate(("issue_date"));
            int renewCount = result.getInt("renew_count");
            issueInfo = new IssueInfo(bookId, memberId, issuedDate, renewCount);
        }
        
        return issueInfo;
    }

    public void deleteIssueInfo(int bookId) throws SQLException {
        
        Connection conn = Database.getInstance().getConnection();

        String sql = "delete from lbdb.issue where book_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, bookId);
        stmt.execute();
        
        
    }

    public void updateRenewCount(int bookId) throws SQLException {
       
        Connection conn = Database.getInstance().getConnection();

        String sql = "update lbdb.issue set renew_count=renew_count+1 where book_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, bookId);
        stmt.execute();
    }

}
