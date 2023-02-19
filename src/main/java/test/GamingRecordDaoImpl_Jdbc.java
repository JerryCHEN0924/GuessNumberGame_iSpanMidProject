package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



//JDBC版
public class GamingRecordDaoImpl_Jdbc implements GamingRecordDao {
	
	Context context;
	
	
	public GamingRecordDaoImpl_Jdbc() {
		
		try {
			
			context = new InitialContext();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	

	// 新增一筆PlayerGamingRecord紀錄
	@Override
	public GamingRecord save(GamingRecord gr) {
		
		String sql = "INSERT INTO [dbo].[GamingRecordList] ([Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DealerNumbers],[GamingTime])"
				+ "     VALUES (?,?,?,?,?,?)";
		
		
		try {
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gr.getUsername());
			pstmt.setInt(2, gr.getCorrectSize());
			pstmt.setString(3, gr.getCorrectNumbers());
			pstmt.setString(4, gr.getPlayerNumbers());
			pstmt.setString(5, gr.getDealerNumbers());
			pstmt.setTimestamp(6, gr.getGamingTime());
			
			int count = pstmt.executeUpdate();
			
			if(count == 0) {
				
				throw new RuntimeException("新增失敗.");
				
			}
			
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			
			if(generatedKeys.next()) {
				
				gr.setGamingRecordId(generatedKeys.getInt(1));
				
			}else {
				
				throw new RuntimeException("新增失敗,無法得到主鍵值");
				
			}
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return gr;
	}
	
	
	
	
	
	// 查詢一筆GamingRecord
	@Override
	public GamingRecord findById(Integer id) {
		
		GamingRecord gamingRecord = null;
		
		
		String sql = "SELECT [GamingRecordId],[Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DealerNumbers],[GamingTime]"
				+ "  FROM [dbo].[GamingRecordList]"
				+ "  WHERE GamingRecordId = ?";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				gamingRecord = new GamingRecord(rs.getInt("GamingRecordId"),
												rs.getString("Username"),
												rs.getInt("CorrectSize"),
												rs.getString("CorrectNumbers"),
												rs.getString("PlayerNumbers"),
												rs.getString("DealerNumbers"),
												rs.getTimestamp("GamingTime")
												);
				
			}
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return gamingRecord;
	}

	
	
	
	@Override
	public List<GamingRecord> findByUsername(String gr) {
		
		
		List<GamingRecord> allPersonalGamingRecords = new ArrayList<GamingRecord>();
		
		//GamingRecord gamingRecord = null;
		
		
		String sql = "SELECT [GamingRecordId],[Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DealerNumbers],[GamingTime]"
				+ "  FROM [dbo].[GamingRecordList]"
				+ "  WHERE Username = ?";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gr);
			
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				GamingRecord gamingRecord = new GamingRecord();
				
				gamingRecord.setGamingRecordId(rs.getInt(1));
				gamingRecord.setUsername(rs.getString(2));
				gamingRecord.setCorrectSize(rs.getInt(3));
				gamingRecord.setCorrectNumbers(rs.getString(4));
				gamingRecord.setPlayerNumbers(rs.getString(5));
				gamingRecord.setDealerNumbers(rs.getString(6));
				gamingRecord.setGamingTime(rs.getTimestamp(7));
				
				
				
				
				allPersonalGamingRecords.add(gamingRecord);
				
				
				
			}
			
			
			
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return allPersonalGamingRecords;
	}

	
	
	
	
	@Override
	public boolean existsByUserId(String id) {
		
		boolean exist = false;
		
		String sql = "SELECT [GamingRecordId],[Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DealerNumbers],[GamingTime]"
				+ "  FROM [dbo].[GamingRecordList]"
				+ "  WHERE Username = ?";
		
		try {
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				exist = true;
				
			}
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return exist;
		
	}

	
	
	// 更新一筆GamingRecord
	@Override
	public void update(GamingRecord gr) {
		
		
		
		String sql = "UPDATE [dbo].[GamingRecordList]"
				+ "   SET [Username] = ?"
				+ "      ,[CorrectSize] = ?"
				+ "      ,[CorrectNumbers] = ?"
				+ "      ,[PlayerNumbers] = ?"
				+ "      ,[DealerNumbers] = ?"
				+ "      ,[GamingTime] = ?"
				+ " WHERE GamingRecordId = ?";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gr.getUsername());
			pstmt.setInt(2, gr.getCorrectSize());
			pstmt.setString(3, gr.getCorrectNumbers());
			pstmt.setString(4, gr.getPlayerNumbers());
			pstmt.setString(5, gr.getDealerNumbers());
			pstmt.setTimestamp(6, gr.getGamingTime());
			pstmt.setInt(7, gr.getGamingRecordId());
			
			int count = pstmt.executeUpdate();
			
			if(count == 0) {
				
				throw new RecordNotFoundException("無法更新紀錄或該筆紀錄不存在");
				
			}
			
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		
		return;

	}
	
	
	
	
	
	
	
	
	
	// 刪除一筆GamingRecord
	@Override
	public void delete(Integer id) {
		
		
		String sql = "DELETE FROM [dbo].[GamingRecordList]"
				+ "      WHERE GamingRecordId = ?";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			int count = pstmt.executeUpdate();
			
			if(count == 0) {
				
				throw new RecordNotFoundException("無法刪除紀錄或該筆紀錄不存在");
				
			}
			
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return;
		

	}
	
	
	
	
	
	// 查詢多筆GamingRecord
	@Override
	public List<GamingRecord> findAll() {
		
		List<GamingRecord> allRecords = new ArrayList<GamingRecord>();
		
		String sql = "SELECT [GamingRecordId],[Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DealerNumbers],[GamingTime]"
				+ "  FROM [dbo].[GamingRecordList]";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			try(
					
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
					
			){
			
			GamingRecord gr = null;
			
			while(rs.next()) {
				
				gr = new GamingRecord(
										rs.getInt("GamingRecordId"),
										rs.getString("Username"),
										rs.getInt("CorrectSize"),
										rs.getString("CorrectNumbers"),
										rs.getString("PlayerNumbers"),
										rs.getString("DealerNumbers"),
										rs.getTimestamp("GamingTime")
										);
				
				allRecords.add(gr);
				
			}
			
			
			}
			
			//conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		/////////////////////////////////////////////////////////////////////
		
//		try {
//			
//			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
//			
//			Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			
//			ResultSet rs = pstmt.executeQuery();
//			
//			GamingRecord gr = null;
//			while(rs.next()) {
//				
//				gr = new GamingRecord(
//						rs.getInt("GameingRecordId"),
//						rs.getString("Username"),
//						rs.getInt("CorrectSize"),
//						rs.getString("CorrectNumbers"),
//						rs.getString("PlayerNumbers"),
//						rs.getString("DealerNumbers"),
//						rs.getTimestamp("GamingTime")
//						);
//				
//				allGamingRecords.add(gr);
//				
//			}
//			
//			
//			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
//			
//			
//		} catch (NamingException e) {
//			
//			e.printStackTrace();
//			
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			
//		}
		
		/////////////////////////////////////////////////////////////////////
		
		
		return allRecords;
	}
	
	
	
	
	

	@Override
	public List<GamingRecord> findAllByIdOrUsername(String id, String gr) {
		
		List<GamingRecord> allGamingRecords = new ArrayList<GamingRecord>();
		
		
		String sql = "SELECT [GamingRecordId],[Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DealerNumbers],[GamingTime]"
				+ "  FROM [dbo].[GamingRecordList]"
				+ "  WHERE GamingRecordId like ? and Username like ?"
				+ "  ORDER BY 1";
		
		String sql2 = "%" + id + "%";
		
		String sql3 = "%" + gr + "%";
		
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, sql2);
			pstmt.setString(2, sql3);
			
			ResultSet rs = pstmt.executeQuery();
			
			GamingRecord gamingRecord = null;
			
			while(rs.next()) {
				
				gamingRecord = new GamingRecord(
												rs.getInt("GamingRecordId"),
												rs.getString("Username"),
												rs.getInt("CorrectSize"),
												rs.getString("CorrectNumbers"),
												rs.getString("PlayerNumbers"),
												rs.getString("DealerNumbers"),
												rs.getTimestamp("GamingTime")
												);
				
				allGamingRecords.add(gamingRecord);
				
				
			}
			
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return allGamingRecords;
		
	}
	
	
	
	
	
	
	@Override
	public List<GamingRecord> findAllByIdOrSize(String id, String gr, String un) {
		
		List<GamingRecord> allGamingRecords = new ArrayList<GamingRecord>();
		
		
		String sql = "SELECT [GamingRecordId],[Username],[CorrectSize],[CorrectNumbers],[PlayerNumbers],[DealerNumbers],[GamingTime]"
				+ "  FROM [dbo].[GamingRecordList]"
				+ "  WHERE GamingRecordId like ? and CorrectSize like ? and Username = ?"
				+ "  ORDER BY 1";
		
		String sql2 = "%" + id + "%";
		
		String sql3 = "%" + gr + "%";
		
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, sql2);
			pstmt.setString(2, sql3);
			pstmt.setString(3, un);
			
			ResultSet rs = pstmt.executeQuery();
			
			GamingRecord gamingRecord = null;
			
			while(rs.next()) {
				
				gamingRecord = new GamingRecord(
						rs.getInt("GamingRecordId"),
						rs.getString("Username"),
						rs.getInt("CorrectSize"),
						rs.getString("CorrectNumbers"),
						rs.getString("PlayerNumbers"),
						rs.getString("DealerNumbers"),
						rs.getTimestamp("GamingTime")
						);
				
				allGamingRecords.add(gamingRecord);
				
				
			}
			
			
			conn.close();//將connection釋放回連線池	 即便不通過也要關閉連線
			
			
			
		} catch (NamingException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		return allGamingRecords;
		
	}
	
	
	
	public void close() {
		
		try {
			
			context.close();
			
		} catch (NamingException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
}
