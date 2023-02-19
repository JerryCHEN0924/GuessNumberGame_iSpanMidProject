package JerryServlet;

import java.util.List;

public class AdvertisementServiceImpl implements AdvertisementService {
	
	AdvertisementDao advertisementDao;
	
	public AdvertisementServiceImpl() {
		advertisementDao = new AdvertisementDaoImpl();
	}

	@Override
	public Advertisement save(Advertisement ad) {
		return advertisementDao.save(ad);
	}

	@Override
	public Advertisement findById(Integer id) {
		return advertisementDao.findById(id);
	}

	@Override
	public Advertisement findByUsername(String ad) {
		return advertisementDao.findByUsername(ad);
	}

	@Override
	public void update(Advertisement ad) {
		advertisementDao.update(ad);
	}

	@Override
	public void delete(Integer id) {
		advertisementDao.delete(id);
	}

	@Override
	public List<Advertisement> findAll() {
		return advertisementDao.findAll();
	}

}
