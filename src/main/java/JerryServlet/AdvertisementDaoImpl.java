package JerryServlet;

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

import test.RecordNotFoundException;

public class AdvertisementDaoImpl implements AdvertisementDao {
	
	
	
	Context context;
	
	

	public AdvertisementDaoImpl() {
		
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	@Override
	public Advertisement save(Advertisement ad) {
		
		String sql = "INSERT INTO [dbo].[AdAcc] ([Username],[Password])"
				+ "     VALUES (?,?)";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			try(
					Connection conn = ds.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					){
				pstmt.setString(1, ad.getUsername());
				pstmt.setString(2, ad.getPassword());
				int count = pstmt.executeUpdate();
				
				if(count ==0) {
					throw new RuntimeException("新增失敗...");
				}
			}
			
			
		} catch (Exception e) {
			
			throw new RecordNotFoundException(e);
		}
		
		
		return ad;
	}

	
	
	
	@Override
	public Advertisement findById(Integer id) {
		
		Advertisement adac = null;
		
		String sql = "SELECT [AdId],[Username],[Password]"
				+ "  FROM [dbo].[AdAcc]"
				+ "  WHERE AdId = ?";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			try(
					Connection conn = ds.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					){
				pstmt.setInt(1, id);
				try(ResultSet rs = pstmt.executeQuery();){
					if(rs.next()) {
						
						adac = new Advertisement(rs.getInt("adId"),
												 rs.getString("username"),
												 rs.getString("password")
												 );
					}
				}
			}
		} catch (Exception e) {
		throw new RecordNotFoundException(e);
		}
		return adac;
	}

	@Override
	public Advertisement findByUsername(String ad) {
		
		Advertisement adac = new Advertisement();
		
		String sql = "SELECT [AdId],[Username],[Password]"
				+ "  FROM [dbo].[AdAcc]"
				+ "  WHERE Username = ?";
		
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ad);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				
				adac.setAdId(rs.getInt(1));
				adac.setUsername(rs.getString(2));
				adac.setPassword(rs.getString(3));
				
			}
			conn.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adac;
	}


	@Override
	public void update(Advertisement ad) {
		
		String sql = "UPDATE [dbo].[AdAcc]"
				+ "   SET [Username] = ?"
				+ "      ,[Password] = ?"
				+ " WHERE AdId = ?";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");

			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(3, ad.getAdId());
			pstmt.setString(1, ad.getUsername());
			pstmt.setString(2, ad.getPassword());
			
			int count = pstmt.executeUpdate();
			System.out.println(count);
			if(count == 0) {
				throw new RecordNotFoundException("無法更新紀錄或該筆紀錄不存在");
			}
			
			conn.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	@Override
	public void delete(Integer id) {
		
		String sql = "DELETE FROM [dbo].[AdAcc]"
				+ "      WHERE AdId = ?";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			int count = pstmt.executeUpdate();
			
			if(count == 0) {
				throw new RecordNotFoundException();
			}
			
			conn.close();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
		
	}

	@Override
	public List<Advertisement> findAll() {
		
		List<Advertisement> allAds = new ArrayList<Advertisement>();
		
		String sql = "SELECT [AdId],[Username],[Password]"
				+ "  FROM [dbo].[AdAcc]";
		
		try {
			
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/midrush");
			
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Advertisement adas = new Advertisement();
				
				adas.setAdId(rs.getInt(1));
				adas.setUsername(rs.getString(2));
				adas.setPassword(rs.getString(3));
				
				allAds.add(adas);
				
			}
			
			
			conn.close();
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return allAds;
	}


	public void close() {
		
		try {
			context.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




}
