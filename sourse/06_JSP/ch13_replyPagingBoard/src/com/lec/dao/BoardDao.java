package com.lec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.dto.BoardDto;

public class BoardDao {
	public static final int SUCCESS = 1;
	public static final int FAIL = 0;
	// 싱글톤
	private static BoardDao instance = new BoardDao();
	public static BoardDao getInstance() {
		return instance;
	}
	private BoardDao() {};
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	// 1. 글개수
	public int getBoardTotalCnt() {
		int totalCnt = 0;
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		String sql = "SELECT COUNT(*)FROM BOARD";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();	// 반드시 한 줄 있으니
			totalCnt = rs.getInt(1); // 1열의 데이터를 int값으로 받기  
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totalCnt;
	}
	// 2. 글목록
	public ArrayList<BoardDto> listBoard(){
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>(); 
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		String sql = "SELECT * FROM BOARD ORDER BY REF DESC, REF_STEP ASC";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int num    	   = rs.getInt("num");
				String writer  = rs.getString("writer");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String email   = rs.getString("email");
				int readcount  = rs.getInt("readcount");
				String pw	   = rs.getString("pw");
				int ref		   = rs.getInt("ref");
				int ref_step   = rs.getInt("ref_step");
				int ref_indent = rs.getInt("ref_indent");
				String ip      = rs.getString("ip");
				Timestamp rdate = rs.getTimestamp("rdate");
				dtos.add(new BoardDto(num, writer, subject, content, email, readcount, 
										pw, ref, ref_step, ref_indent, ip, rdate));
			};
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	// 2. 글목록 (topN구문)
		public ArrayList<BoardDto> listBoard(int startRow, int endRow){
			ArrayList<BoardDto> dtos = new ArrayList<BoardDto>(); 
			Connection 		  conn  = null;
			PreparedStatement pstmt = null;
			ResultSet 		  rs    = null;
			String sql = "SELECT * " + 
					"    FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD ORDER BY REF DESC, REF_STEP ASC) A)" + 
					"    WHERE RN BETWEEN ? AND ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int num    	   = rs.getInt("num");
					String writer  = rs.getString("writer");
					String subject = rs.getString("subject");
					String content = rs.getString("content");
					String email   = rs.getString("email");
					int readcount  = rs.getInt("readcount");
					String pw	   = rs.getString("pw");
					int ref		   = rs.getInt("ref");
					int ref_step   = rs.getInt("ref_step");
					int ref_indent = rs.getInt("ref_indent");
					String ip      = rs.getString("ip");
					Timestamp rdate = rs.getTimestamp("rdate");
					dtos.add(new BoardDto(num, writer, subject, content, email, readcount, 
											pw, ref, ref_step, ref_indent, ip, rdate));
				};
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(rs   !=null) rs.close();
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return dtos;
		}
	// 3. 원글쓰기
	public int insertBoard(BoardDto dto) {
		int result = FAIL;
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD (NUM, WRITER, SUBJECT, CONTENT, EMAIL, PW," + 
				"                    REF, REF_STEP, REF_INDENT, IP)" + 
				"    VALUES ((SELECT NVL(MAX(NUM), 0)+1 FROM BOARD), ?, ?, ?, ?, ?," + 
				"            (SELECT NVL(MAX(NUM), 0)+1 FROM BOARD), 0, 0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPw());
			pstmt.setString(6, dto.getIp());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("원글쓰기 예외 발생 : " + dto);
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 4. 글번호로 글상세보기 내용
	public BoardDto getBoardOneLine(int num) {
		BoardDto dto = null;
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		String sql = "SELECT * FROM BOARD WHERE NUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String writer  = rs.getString("writer");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String email   = rs.getString("email");
				int readcount  = rs.getInt("readcount");
				String pw	   = rs.getString("pw");
				int ref		   = rs.getInt("ref");
				int ref_step   = rs.getInt("ref_step");
				int ref_indent = rs.getInt("ref_indent");
				String ip      = rs.getString("ip");
				Timestamp rdate = rs.getTimestamp("rdate");
				dto = new BoardDto(num, writer, subject, content, email, readcount, 
									pw, ref, ref_step, ref_indent, ip, rdate);
			};
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}		
		return dto;
	}
	// content.jsp에서 사용 num울 String으로
	public BoardDto getBoardOneLine(String numStr) {
		BoardDto dto = null;
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		ResultSet 		  rs    = null;
		String sql = "SELECT * FROM BOARD WHERE NUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, numStr);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int num		   = rs.getInt("num");
				String writer  = rs.getString("writer");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String email   = rs.getString("email");
				int readcount  = rs.getInt("readcount");
				String pw	   = rs.getString("pw");
				int ref		   = rs.getInt("ref");
				int ref_step   = rs.getInt("ref_step");
				int ref_indent = rs.getInt("ref_indent");
				String ip      = rs.getString("ip");
				Timestamp rdate = rs.getTimestamp("rdate");
				dto = new BoardDto(num, writer, subject, content, email, readcount, 
									pw, ref, ref_step, ref_indent, ip, rdate);
			};
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}		
		return dto;
	}
	// 5. 조회수 올리기
	public void readCountUp(int num) {
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET READCOUNT = READCOUNT + 1 WHERE NUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();	// 결과 안 받고 sql 전송
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	// content.jsp 에서 사용 num울 String으로
	public void readCountUp(String num) {
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET READCOUNT = READCOUNT + 1 WHERE NUM=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();	// 결과 안 받고 sql 전송
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	// 6. 글 수정
	public int updateBoard(BoardDto dto) {
		int result = FAIL;
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET SUBJECT = ?," + 
				"                 CONTENT = ?," + 
				"                 EMAIL   = ?," + 
				"                 PW      = ?," + 
				"                 IP      = ?" + 
				"           WHERE NUM =?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPw());
			pstmt.setString(5, dto.getIp());
			pstmt.setInt(6, dto.getNum());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("글수정 예외 발생 : " + dto);
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 7. 글삭제
	public int deleteBoard(int num, String pw) {
		int result = FAIL;
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM BOARD WHERE NUM=? AND PW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, pw);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글 삭제완료" : "글 삭제실패(비번확인)");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 8. 답변글 저장 전 단계(엑셀의 a단계) 
	private void preReplyStep(int ref, int ref_step) {
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET REF_STEP = REF_STEP + 1" + 
				"    WHERE REF = ? AND REF_STEP > ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, ref_step);
			int result = pstmt.executeUpdate();
			System.out.println("답변글" +result + "개 조정됨");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	// 9. 답변글 쓰기
	public int reply(BoardDto dto) {
		// num, writer, subject, content, email, pw, ref, ref_step, ref_indent, ip
		// 원글에 대한 정보 : num, ref, ref_step, ref_indent
		// 사용자로부터 입력받는 내용 : writer, subject, content, email, pw
		// request.getremoteAddr() 함수로 ip 가져오기
		preReplyStep(dto.getRef(), dto.getRef_step());	// 답글 쓰기 전 단계
		int result = FAIL;
		Connection 		  conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD (NUM, WRITER, SUBJECT, CONTENT, EMAIL, PW, REF, REF_STEP, REF_INDENT, IP) " + 
				"    VALUES ((SELECT NVL(MAX(NUM), 0)+1 FROM BOARD), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPw());
			pstmt.setInt(6, dto.getRef());
			pstmt.setInt(7, dto.getRef_step() + 1);
			pstmt.setInt(8, dto.getRef_indent() + 1);
			pstmt.setString(9, dto.getIp());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS? "답글 쓰기 성공" : "답글 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
