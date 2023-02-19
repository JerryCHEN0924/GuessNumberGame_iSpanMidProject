package test;

import java.util.List;

public class MemberServiceImpl_Jdbc implements MemberService {
	
	
	
	
    MemberDao memberDao;
    
    
    
    
	public MemberServiceImpl_Jdbc() {
		memberDao = new MemberDaoImpl_Jdbc();
	}
	
	
	

	@Override
	public Member save(Member mem) {
		return memberDao.save(mem);
	}

	@Override
	public Member findById(Integer id) {
		return memberDao.findById(id);
	}
	
	@Override
	public Member findByUsername(String mem) {
		return memberDao.findByUsername(mem);
	}

	@Override
	public boolean existsByUserId(String id) {
		return memberDao.existsByUserId(id);
	}

	@Override
	public void update(Member mem) {
		 memberDao.update(mem);
	}

	@Override
	public void delete(Integer id) {
		 memberDao.delete(id);
	}

	@Override
	public List<Member> findAll() {
		return memberDao.findAll();
	}
		
	@Override
	public List<Member> findAllByIdOrUsername(String id, String mem) {
		return memberDao.findAllByIdOrUsername(id, mem);
	}

}
