package test;

import java.util.List;

public class GamingRecordServiceImpl_Jdbc implements GamingRecordService {
	
	
	GamingRecordDao gamingRecordDao;
	
	
	

	public GamingRecordServiceImpl_Jdbc() {
		
		gamingRecordDao = new GamingRecordDaoImpl_Jdbc();
		
	}

	
	
	@Override
	public GamingRecord save(GamingRecord gr) {
		
		return gamingRecordDao.save(gr);
		
	}

	@Override
	public GamingRecord findById(Integer id) {
		
		return gamingRecordDao.findById(id);
		
	}

	@Override
	public List<GamingRecord> findByUsername(String gr) {
		
		return gamingRecordDao.findByUsername(gr);
		
	}

	@Override
	public boolean existsByUserId(String id) {
		
		return gamingRecordDao.existsByUserId(id);
		
	}

	@Override
	public void update(GamingRecord gr) {
		
		gamingRecordDao.update(gr);

	}

	@Override
	public void delete(Integer id) {
		
		gamingRecordDao.delete(id);

	}

	@Override
	public List<GamingRecord> findAll() {
		
		return gamingRecordDao.findAll();
		
	}

	@Override
	public List<GamingRecord> findAllByIdOrUsername(String id, String gr) {
		
		return gamingRecordDao.findAllByIdOrUsername(id, gr);
		
	}
	
	
	
	@Override
	public List<GamingRecord> findAllByIdOrSize(String id, String gr, String un) {
		
		return gamingRecordDao.findAllByIdOrSize(id, gr, un);
		
		
	}
	
	
	
	
	
	
	

}









