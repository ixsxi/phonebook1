package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

public class PhoneDao {
	//리스트 
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";
	
	// DB연결
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	// 자원정리
		private void close() {
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	
		
		//검색기능
			public List<PersonVo> getPhoneList(String search){
			
			//리스트 생성
			List<PersonVo> personList = new ArrayList<PersonVo>();
			getConnection();
			
			try {
				//sql 문 준비
				String query = "";
				query += " select person_id, ";
				query += " name, ";
				query += " hp, ";
				query += " company ";
				query += " from phone ";
				query += " where (name || hp || company) like '%"+search+"%' ";
				
	
				
				 pstmt = conn.prepareStatement(query);
				
				rs = pstmt.executeQuery();
				
				//4.결과처리
				
				while(rs.next()) {
					int personId = rs.getInt("person_id");
					String name = rs.getString("name");
					String hp = rs.getString("hp");
					String company = rs.getString("company");
					
					PersonVo personvo = new PersonVo(personId,name,hp,company);
					personList.add(personvo);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			//자원정리
			close();
			return personList;
			
			
		}
		
		
		//삭제
		public int PersonDelete(PersonVo personVo) {
			int count = -1;

			getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " DELETE from phone ";
				query += " where person_id =  ? ";
				
				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, personVo.getPersonId());
				
				
			
				
				count = pstmt.executeUpdate();

				// 4.결과처리
				System.out.println(count + "건 등록");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			close();

			return count; // 성공갯수 리턴
			
		}
		
		
		//수정
		public int PersonUpdate(PersonVo personVo) {
			int count = -1;

			getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " update phone ";
				query += " set name = ?, ";
				query += " hp = ?, ";
				query += " company = ? ";
				query += " where person_id = ? ";
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, personVo.getName());
				pstmt.setString(2, personVo.getHp());
				pstmt.setString(3, personVo.getCompany());
				pstmt.setInt(4, personVo.getPersonId());
			
				
				count = pstmt.executeUpdate();

				// 4.결과처리
				System.out.println(count + "건 등록");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			close();

			return count; // 성공갯수 리턴
			
		}
		
		
		//등록하기 
		
		public int PersonInsert(PersonVo personVo) {
			int count = -1;

			getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " insert into phone ";
				query += " values(seq_person_id.nextval, ?, ?, ?) ";

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, personVo.getName());
				pstmt.setString(2, personVo.getHp());
				pstmt.setString(3, personVo.getCompany());

				count = pstmt.executeUpdate();

				// 4.결과처리
				System.out.println(count + "건 등록");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			close();

			return count; // 성공갯수 리턴
			
		}
		
		//전화번호 리스트 가져오기
		public List<PersonVo> getPhoneList(){
			
			//리스트 생성
			List<PersonVo> personList = new ArrayList<PersonVo>();
			getConnection();
			
			try {
				//sql 문 준비
				String query = "";
				query += " select person_id, ";
				query += " name, ";
				query += " hp, ";
				query += " company ";
				query += " from phone ";
				
				
				 pstmt = conn.prepareStatement(query);
				
				rs = pstmt.executeQuery();
				
				//4.결과처리
				
				while(rs.next()) {
					int personId = rs.getInt("person_id");
					String name = rs.getString("name");
					String hp = rs.getString("hp");
					String company = rs.getString("company");
					
					PersonVo personvo = new PersonVo(personId,name,hp,company);
					personList.add(personvo);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			//자원정리
			close();
			return personList;
			
			
		}
	
}
