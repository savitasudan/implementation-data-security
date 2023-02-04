package com.movies.finalproject;

public interface IAdminService {
    Admin getById(Integer id);
    void deleteById(Integer id);
    void addAdmin(Admin admin);   
}
