package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import domain.Admin;

@Service
@Transactional
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository ;
	
	public AdminService(){
		super();
	}
	
	// CRUD methods --------------------------------------------------------------------------------
	public Admin create() {
		Admin res = new Admin();
		
		//TODO
		
		return res;
	}

	public Admin findOne(int adminId) {
		return adminRepository.findOne(adminId);
	}

	public Collection<Admin> findAll(){
		return adminRepository.findAll();
	}
		
	public Admin save(Admin admin) {
		Assert.notNull(admin);
		return adminRepository.save(admin);
	}

	public void delete(Admin admin) {
		Assert.notNull(admin);
		Assert.isTrue(adminRepository.exists(admin.getId()));
		
		adminRepository.delete(admin);
		
		Assert.isTrue(!adminRepository.exists(admin.getId()));
	}

}