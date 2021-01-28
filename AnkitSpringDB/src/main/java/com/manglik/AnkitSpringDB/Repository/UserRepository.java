package com.manglik.AnkitSpringDB.Repository;

import com.manglik.AnkitSpringDB.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends JpaRepository<Person, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into Person (name, email) values (:name, :email)", nativeQuery = true)
    int addUser(String name,String email);

    @Transactional
    @Modifying
    @Query(value = "update Person set name=?1 where id=?2",nativeQuery = true)
    int updateUser(String name,int id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Person WHERE id=?1",nativeQuery = true)
    int deleteUser(int id);


}