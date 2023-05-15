package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

/**
 * JDBC - DriveManager 사용
 */

@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException{
        String sql = "insert into member(member_id, money) values (?, ?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            //바인딩 작업
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2,member.getMoney());

            pstmt.executeUpdate();
            return member;
        }catch(SQLException e){
            log.error("db error",e);
            throw e;
        }finally{
            // 호출이 보장되도록 하는 장치[finally](29번 줄위에서 예외처리 뜨면 아래코드가 실행이 안됀
            // 반드시 닫아야 메모리에 남지 않는다
            closs(con, pstmt, null);
        }
    }

    public Member findById(String memberId)throws SQLException{
        String sql = "select * from member where member_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();// select 할때 한다

            if(rs.next()){//한번 호출난뒤에 실제 데이터가 나온다
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));

                return member;
            }else{
                throw new NoSuchElementException("member not found memberId = "+memberId);
            }

        }catch(SQLException e){
            log.info("error",e);
            throw e;
        }finally{
            closs(con, pstmt, rs);
        }
    }

    private void closs(Connection con, Statement stmt, ResultSet rs){
        //리소스 정리할때 역순으로
        //con을 통해 stmt 얻고 stmt로 rs 얻었으니 rs -> stmt -> con 순서로 한다
        if(rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                log.info("error ",e);
            }
        }
        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException e){
                log.info("error ",e);
            }
        }
        if(con != null){
            try{
                con.close();
            }catch(SQLException e){
                log.info("error ",e);
            }
        }
    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money=? where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1,money);
            pstmt.setString(2, memberId);


            int resultSize = pstmt.executeUpdate();

            log.info("resultSize = {}",resultSize);


        }catch(SQLException e){
            log.info("error",e);
            throw e;
        }finally{
            closs(con, pstmt, null);
        }
    }

    public void delete(String memberId)throws SQLException{
        String sql = "delete from member where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, memberId);
            int resultSize = pstmt.executeUpdate();

            log.info("resultSize = {}",resultSize);

        }catch(SQLException e){
            log.info("error",e);
            throw e;
        }finally{
            closs(con, pstmt, null);
        }
    }

    private Connection getConnection(){
        return DBConnectionUtil.getConnection();
    }
}
