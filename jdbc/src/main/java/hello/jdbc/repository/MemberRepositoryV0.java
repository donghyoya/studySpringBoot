package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC - DriveManager 사용
 */

@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException{
        String sql = "insert into member(member_id, money) valuse (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            //바인딩 작업
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2,member.getMoney());

            return member;
        }catch(SQLException e){
            log.error("db error",e);
            throw e;
        }finally{
            // 반드시 닫아야 메모리에 남지 않는다
            closs(con,null, pstmt);
        }
    }

    private void closs(Connection con, Statement stmt, PreparedStatement pstmt){
        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    private Connection getConnection(){
        return DBConnectionUtil.getConnection();
    }
}
