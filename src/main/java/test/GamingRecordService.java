package test;

import java.util.List;

public interface GamingRecordService {
	
	
	public GamingRecord save(GamingRecord gr);

	public GamingRecord findById(Integer id);
	
	public List<GamingRecord> findByUsername(String gr);

	public boolean existsByUserId(String id);

	public void update(GamingRecord gr);

	public void delete(Integer id);

	public List<GamingRecord> findAll();
	
	public List<GamingRecord> findAllByIdOrUsername(String id, String gr);
	
	public List<GamingRecord> findAllByIdOrSize(String id, String gr, String un);
	
	

}
