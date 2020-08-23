/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.repositories;

import com.exerciseSpringBoot.crudBootstrap.entities.Copyemp;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface CopyempRepository extends JpaRepository<Copyemp, String>{
    @Modifying
    @Query(value="SELECT employees.id, employees.idjob, employees.name, jobs.jobtitle, employees.group FROM employees INNER JOIN jobs ON employees.idjob=jobs.id;",nativeQuery = true)
    List<Copyemp> findAllName();
    
    @Transactional
    @Modifying
    @Query(value="UPDATE copyemp e SET e.idjob=?2, e.name=?3, e.group=?4 WHERE e.id=?1",nativeQuery = true)
    void UpdateProfil(String id, String idjobb, String name, String group);
    
}
