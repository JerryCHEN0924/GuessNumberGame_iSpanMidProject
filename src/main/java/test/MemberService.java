package test;

import java.util.List;

public interface MemberService {
	
	public Member save(Member mem);

	public Member findById(Integer id);
	
	public Member findByUsername(String mem);

	public boolean existsByUserId(String id);

	public void update(Member mem);

	public void delete(Integer id);

	public List<Member> findAll();
	
	public List<Member> findAllByIdOrUsername(String id, String mem);

}
