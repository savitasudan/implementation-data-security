package com.movies.finalproject;

import org.springframework.beans.factory.annotation.Autowired;

public class AdminService implements IAdminService{

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public void addAdmin(Admin admin ) {
        this.adminRepository.save(admin);
        
    }

    @Override
    public Admin getById(Integer id) {
        java.util.Optional<Admin> optional = adminRepository.findById(id);
    Admin admin = null;
    if(((java.util.Optional<Admin>) optional).isPresent()){
        admin = optional.get();

    }else{
        throw new RuntimeException("data not found:"+ id);
    }
    return admin;
    }

    @Override
    public void deleteById(Integer id) {
        this.adminRepository.deleteById(id);
    }
    
}
