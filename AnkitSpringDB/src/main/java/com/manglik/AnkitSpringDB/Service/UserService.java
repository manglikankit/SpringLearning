package com.manglik.AnkitSpringDB.Service;

import com.manglik.AnkitSpringDB.Models.Person;
import com.manglik.AnkitSpringDB.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean addUser(String name,String email){
        try {
            int result = userRepository.addUser(name, email);
            if(result!=0){
                return true;
            }

        }catch (Exception e){
            return false;
        }
        return false;
    }

    public boolean updateUser(String name,int id){
        try {
            int result = userRepository.updateUser(name, id);
            if(result!=0){
                return true;
            }

        }catch (Exception e){
            return false;
        }
        return false;
    }

    public boolean delete(int id){
        try {
            int result = userRepository.deleteUser(id);
            if (result != 0) {
                return true;
            }
        }
        catch(Exception e) {
            return false;
        }
        return false;
    }

}
