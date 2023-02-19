package JerryServlet;

import java.util.List;

public interface AdvertisementService {
	
	
	public Advertisement save(Advertisement ad);

	public Advertisement findById(Integer id);
	
	public Advertisement findByUsername(String ad);


	public void update(Advertisement ad);

	public void delete(Integer id);

	public List<Advertisement> findAll();
	

}
