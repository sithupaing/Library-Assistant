/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.model;

import java.sql.Date;

/**
 *
 * @author Sithu
 */
public class IssueInfo {
    
    private int bookId;
    private int memberId;
    private Date issueDate;
    private int renewCount;

    public IssueInfo(int bookId, int memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }

    public IssueInfo(int bookId, int memberId, Date issueDate, int renewCount) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.renewCount = renewCount;
    }
    
    

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getRenewCount() {
        return renewCount;
    }

    public void setRenewCount(int renewCount) {
        this.renewCount = renewCount;
    }

    
}
