package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



// JDBC版
public class MemberDaoImpl_Jdbc implements MemberDao {
	
	Context ctx;
	

	public MemberDaoImpl_Jdbc() {
		
		try {
			
			ctx = new InitialContext();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	
	// 新增一筆Member紀錄(OK)
	@Override
	public Member save(Member mem) {
		
		String sql = "INSERT INTO [dbo].[memberstemp] ([Username],[Password],[CodeName],[Gender],[Birthday],[Phone],[Email],[RegisterTime])"
				+ "     VALUES (?,?,?,?,?,?,?,?)";
		
		
		try {
			
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/midrush");
			
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql,
						                 PreparedStatement.RETURN_GENERATED_KEYS);
			) {
				stmt.setString(1, mem.getUsername());
				stmt.setString(2, mem.getPassword());
				stmt.setString(3, mem.getCodename());
				stmt.setString(4, mem.getGender());
				stmt.setDate(5, mem.getBirthday());
				stmt.setString(6, mem.getPhone());
				stmt.setString(7, mem.getEmail());
				stmt.setTimestamp(8, mem.getRegisterTime());
				int affectedRows = stmt.executeUpdate();

		        if (affectedRows == 0) {
		            throw new RuntimeException("新增失敗.");
		        }
		        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		            	mem.setPlayerId(generatedKeys.getInt(1));
		            } else {
		                throw new RuntimeException("新增失敗.無法得到主鍵值");
		            }
		        }
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return mem;
	}

	// 查詢一筆Member紀錄(OK)
	@Override
	public Member findById(Integer id) {
		Member member = null;
//		String sql = "SELECT * FROM ch04_MemberExample WHERE id = ?";
		String sql = "SELECT [PlayerId],[Username],[Password],[CodeName],[Gender],[Birthday],[Phone],[Email],[RegisterTime]"
				+ "  FROM [dbo].[memberstemp]"
				+ "  WHERE PlayerId = ?";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/midrush");
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {
				stmt.setInt(1, id);
				try (ResultSet rset = stmt.executeQuery();) {
					if (rset.next()) {
						member = new Member(rset.getInt("playerId"), 
											rset.getString("username"), 
											rset.getString("password"),
											rset.getString("codename"), 
											rset.getString("gender"), 
											rset.getDate("birthday"),
											rset.getString("Phone"), 
											rset.getString("email"),
											rset.getTimestamp("registerTime")
											);
					}
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return member;
	}
	
	
	// 查詢一筆Member紀錄 by Username(OK)
		@Override
		public Member findByUsername(String mem) {
			Member member = null;
//			String sql = "SELECT * FROM ch04_MemberExample WHERE id = ?";
			String sql = "SELECT [PlayerId],[Username],[Password],[CodeName],[Gender],[Birthday],[Phone],[Email],[RegisterTime]"
					+ "  FROM [dbo].[memberstemp]"
					+ "  WHERE Username = ?";
			try {
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/midrush");
				try (
					Connection conn = ds.getConnection(); 
					PreparedStatement stmt = conn.prepareStatement(sql);
				) {
					stmt.setString(1, mem);
					try (ResultSet rset = stmt.executeQuery();) {
						if (rset.next()) {
							member = new Member(rset.getInt("playerId"), 
												rset.getString("username"), 
												rset.getString("password"),
												rset.getString("codename"), 
												rset.getString("gender"), 
												rset.getDate("birthday"),
												rset.getString("Phone"), 
												rset.getString("email"),
												rset.getTimestamp("registerTime")
												);
						}
					}
				}
			} catch (Exception e) {
				throw new RecordNotFoundException(e);
			}
			return member;
		}
	
	

	// 刪除一筆Member紀錄
	@Override
	public void delete(Integer id) {
//		String sql = "DELETE FROM ch04_MemberExample WHERE id = ?";
		String sql = "DELETE FROM [dbo].[memberstemp]"
				+ "      WHERE PlayerId = ?";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/midrush");
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {
				stmt.setInt(1, id);
				int count = stmt.executeUpdate();

				if (count == 0) {
					throw new RecordNotFoundException("無法刪除紀錄或該筆紀錄不存在");
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return;
	}

	
	// 更新一筆Member紀錄
	@Override
	public void update(Member mem) {

//		String sql = "UPDATE ch04_MemberExample SET account=?, password=?, name=?, phoneNo=?, experience=?, birthday=? where id = ?";
		String sql = "UPDATE [dbo].[memberstemp]"
				+ "   SET [Username] = ?"
				+ "      ,[Password] = ?"
				+ "      ,[CodeName] = ?"
				+ "      ,[Gender] = ?"
				+ "      ,[Birthday] = ?"
				+ "      ,[Phone] = ?"
				+ "      ,[Email] = ?"
				+ "      ,[RegisterTime] = ?"
				+ " WHERE PlayerId = ?";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/midrush");
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {
				stmt.setString(1, mem.getUsername());
				stmt.setString(2, mem.getPassword());
				stmt.setString(3, mem.getCodename());
				stmt.setString(4, mem.getGender());
				stmt.setDate(5, mem.getBirthday());
				stmt.setString(6, mem.getPhone());
				stmt.setString(7, mem.getEmail());
				stmt.setTimestamp(8, mem.getRegisterTime());
				stmt.setInt(9, mem.getPlayerId());
				int count = stmt.executeUpdate();
				if (count == 0) {
					throw new RecordNotFoundException("無法更新紀錄或該筆紀錄不存在");
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return;
	}
	
	
	// 查詢多筆Member紀錄by PlayerId or Username (OK)
		public List<Member> findAllByIdOrUsername(String id, String mem) {
			List<Member> allMembers = new ArrayList<Member>();
//			String sql = "SELECT * FROM  ch04_MemberExample";
			String sql = "SELECT [PlayerId],[Username],[Password],[CodeName],[Gender],[Birthday],[Phone],[Email],[RegisterTime]"
					+ "  FROM [dbo].[memberstemp]"
					+ "  where PlayerId like ? and Username like ?"
					+ "  order by 1";
			
			String sql2 = "%" + id + "%";
			
			String sql3 = "%" + mem + "%";
			
			try {
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/midrush");
				try (
					Connection conn = ds.getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql);
//					ResultSet rs = stmt.executeQuery();
				) {
					
//					stmt.setString(1, id);
					stmt.setString(1, sql2);
					stmt.setString(2, sql3);
					Member member = null;
					
					try (ResultSet rset = stmt.executeQuery();) {
						while (rset.next()) {
							member = new Member(rset.getInt("playerId"), 
												rset.getString("username"), 
												rset.getString("password"),
												rset.getString("codename"), 
												rset.getString("gender"), 
												rset.getDate("birthday"),
												rset.getString("Phone"), 
												rset.getString("email"),
												rset.getTimestamp("registerTime")
												);
							allMembers.add(member);
						}
					}
					
					
					
				}
			} catch (Exception e) {
				throw new RecordNotFoundException(e);
			}
			return allMembers;
		}
	

	// 查詢多筆Member紀錄(OK)
	public List<Member> findAll() {
		
		List<Member> allMembers = new ArrayList<Member>();
//		String sql = "SELECT * FROM  ch04_MemberExample";
		
		String sql = "SELECT [PlayerId],[Username],[Password],[CodeName],[Gender],[Birthday],[Phone],[Email],[RegisterTime]"
				+ "  FROM [dbo].[memberstemp]";
		
		try {
			
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/midrush");
			
			try (
					
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
					
			) {
				
				Member mem = null;
				
				while (rs.next()) {
					
					mem = new Member(
									rs.getInt("PlayerId"), 
									rs.getString("Username"), 
									rs.getString("Password"),
									rs.getString("CodeName"), 
									rs.getString("Gender"), 
									rs.getDate("Birthday"), 
									rs.getString("Phone"),
									rs.getString("Email"),
									rs.getTimestamp("RegisterTime")
									);
					
					allMembers.add(mem);
					
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return allMembers;
	}
	// (OK)
	public boolean existsByUserId(String userId) {
		boolean exist = false;
//		String sql = "SELECT * FROM ch04_MemberExample WHERE account = ?";
		String sql = "SELECT [PlayerId],[Username],[Password],[CodeName],[Gender],[Birthday],[Phone],[Email],[RegisterTime]"
				+ "  FROM [dbo].[memberstemp]"
				+ "  WHERE username = ?";
		try {
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/midrush");
			
			try (
				Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql);
			) {
				
				stmt.setString(1, userId);
				try (ResultSet rs = stmt.executeQuery();) {
					if (rs.next()) {
						exist = true;
						
					}
				}
			}
		} catch (Exception e) {
			throw new RecordNotFoundException(e);
		}
		return exist;
	}
	
	
	
	public void close() {
		
		try {
			
			ctx.close();
			
		} catch (NamingException e) {
			
			throw new RuntimeException(e);
			
		}
		
	}
	
}
