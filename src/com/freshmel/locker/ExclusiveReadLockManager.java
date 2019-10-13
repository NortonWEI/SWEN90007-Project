package com.freshmel.locker;

import com.freshmel.dbc.DataBaseConnection;
import com.freshmel.exception.ConcurrencyException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExclusiveReadLockManager {
    public Connection conn = new DataBaseConnection().getConnection();
    public PreparedStatement pstmt;
    public static ExclusiveReadLockManager exclusiveReadLockManager;

    public static ExclusiveReadLockManager getInstance(){
        if (ExclusiveReadLockManager.exclusiveReadLockManager == null){
            ExclusiveReadLockManager.exclusiveReadLockManager = new ExclusiveReadLockManager();
        }
        return ExclusiveReadLockManager.exclusiveReadLockManager;
    }

    public void acquireLock(Long lockable, String owner) throws ConcurrencyException {
        if(!hasLock(lockable, owner)) {
            try {
                String insertLockStmt = "INSERT INO lock VALUES ?, ?";
                pstmt = conn.prepareStatement(insertLockStmt);
                pstmt.setLong(1, lockable);
                pstmt.setString(2, owner);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new ConcurrencyException("Unable to lock " + lockable);
            }
        }
    }

    public void releaseLock(Long lockable, String owner) {
        String deleteLockStmt = "DELETE FROM lock WHERE lockable = ? and owner = ?";
        try {
            pstmt = conn.prepareStatement(deleteLockStmt);
            pstmt.setLong(1, lockable);
            pstmt.setString(2, owner);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean hasLock(Long lockable, String owner) {
        String sql = "SELECT * FROM lock WHERE lockable=? AND owner=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, lockable);
            pstmt.setString(2, owner);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
