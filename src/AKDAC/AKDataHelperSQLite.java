package AKDAC;

import AKInfra.AKAppException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class AKDataHelperSQLite {

    private static String DBPathConnection = "jdbc:sqlite:AKdatabase//AKHormigueroVirtual.sqlite";

    protected AKDataHelperSQLite() {
    }

    protected synchronized Connection openConnection() throws AKAppException {
        try {
            return DriverManager.getConnection(DBPathConnection);
        } catch (Exception e) {
            throw new AKAppException(e, "SQLiteDataHelper", "SQLiteDataHelper()");
        }
    }

    protected <T> T executeReadBy(String query, Function<ResultSet, T> mapper, Object... params) throws Exception {
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? mapper.apply(rs) : null;
        } catch (SQLException e) {
            throw new AKAppException(e, getClass().getName(), "executeReadBy()");
        }
    }

    protected <T> List<T> executeReadByPadre(String query, Function<ResultSet, T> mapper, Object... params) throws Exception {
        List<T> list = new ArrayList<>();
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(mapper.apply(rs));
            }
        } catch (SQLException e) {
            //throw new AKAppException(e, getClass().getName(), "executeReadByPadre()");
            System.out.println(e);
        }
        return list;
    }

    protected <T> List<T> executeReadAll(String query, Function<ResultSet, T> mapper) throws Exception {
        List<T> list = new ArrayList<>();
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapper.apply(rs));
            }
        } catch (SQLException e) {
            throw new AKAppException(e, getClass().getName(), "executeReadAll()");
        }
        return list;
    }

    protected boolean execute(String query, Object... params) throws Exception {
        try (Connection conn = openConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new AKAppException(e, getClass().getName(), "execute()");
        }
    }

    public static String getDateTimeNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

}
