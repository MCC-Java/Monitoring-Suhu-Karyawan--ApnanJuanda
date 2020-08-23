/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.repositories;

import com.exerciseSpringBoot.crudBootstrap.entities.Ccondition;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ASUS
 */
@Repository
public interface CconditionRepository extends JpaRepository<Ccondition, String>{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ccondition(idemployee,date,temperature,idhealty) VALUES (?1,?2,?3,?4);",nativeQuery = true)
    void saveinput(String idemployee, LocalDate date, float temperature, String idhealty);
    
    @Transactional
    @Modifying
    @Query(value="UPDATE ccondition u SET u.idemployee=?1, u.date=?2, u.temperature=?3, u.idhealty=?4 WHERE u.idempcondition=?5",nativeQuery = true)
    void update(String idemployee, Date tanggal, float temperature, String idhealty, int idempcondition);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ccondition WHERE idempcondition=?1",nativeQuery = true)
    void deletedata(int idempcondition);
    
    @Modifying
    @Query(value = "SELECT employees.name, emp_condition.date, emp_condition.temperature, healty_status.information, emp_condition.idempcondition, emp_condition.idemployee, emp_condition.idhealty FROM employees INNER JOIN emp_condition ON employees.id=emp_condition.idemployee INNER JOIN healty_status ON emp_condition.idhealty=healty_status.id ORDER BY employees.name ASC, emp_condition.date ASC;" , nativeQuery = true)
    List<Ccondition> findAllEmp();
    
    @Modifying
    @Query(value = "SELECT employees.name, emp_condition.date, emp_condition.temperature, healty_status.information, emp_condition.idempcondition, emp_condition.idemployee, emp_condition.idhealty FROM employees INNER JOIN emp_condition ON employees.id=emp_condition.idemployee INNER JOIN healty_status ON emp_condition.idhealty=healty_status.id WHERE emp_condition.temperature > 37 ORDER BY emp_condition.idemployee ASC, emp_condition.date ASC" , nativeQuery = true)
    List<Ccondition> findIlness();
    
    @Modifying
    @Query(value = "SELECT emp_condition.idempcondition, employees.name, emp_condition.idemployee, emp_condition.date, emp_condition.temperature, emp_condition.idhealty, healty_status.information FROM emp_condition  INNER JOIN employees ON emp_condition.idemployee=employees.id INNER JOIN healty_status ON emp_condition.idhealty=healty_status.id WHERE employees.name=?1 AND MONTH(emp_condition.date)=?2 AND (emp_condition.temperature BETWEEN ?3 AND ?4) ORDER BY emp_condition.date ASC;" , nativeQuery = true)
    List<Ccondition> findBySorting(String name, int month, float temp1, float temp2);
    
    @Modifying
    @Query(value = "SELECT emp_condition.idempcondition, employees.name, emp_condition.idemployee, emp_condition.date, emp_condition.temperature, emp_condition.idhealty, healty_status.information FROM emp_condition  INNER JOIN employees ON emp_condition.idemployee=employees.id INNER JOIN healty_status ON emp_condition.idhealty=healty_status.id WHERE (emp_condition.temperature BETWEEN ?1 AND ?2) ORDER BY emp_condition.date ASC;" , nativeQuery = true)
    List<Ccondition> findBySorting1(float temp1, float temp2);
    
    @Modifying
    @Query(value = "SELECT emp_condition.idempcondition, employees.name, emp_condition.idemployee, emp_condition.date, emp_condition.temperature, emp_condition.idhealty, healty_status.information FROM emp_condition  INNER JOIN employees ON emp_condition.idemployee=employees.id INNER JOIN healty_status ON emp_condition.idhealty=healty_status.id WHERE employees.name=?1 AND (emp_condition.temperature BETWEEN ?2 AND ?3) ORDER BY emp_condition.date ASC;" , nativeQuery = true)
    List<Ccondition> findBySorting2(String name, float temp1, float temp2);
    
    @Modifying
    @Query(value = "SELECT emp_condition.idempcondition, employees.name, emp_condition.idemployee, emp_condition.date, emp_condition.temperature, emp_condition.idhealty, healty_status.information FROM emp_condition  INNER JOIN employees ON emp_condition.idemployee=employees.id INNER JOIN healty_status ON emp_condition.idhealty=healty_status.id WHERE MONTH(emp_condition.date)=?1 AND (emp_condition.temperature BETWEEN ?2 AND ?3) ORDER BY emp_condition.date ASC;" , nativeQuery = true)
    List<Ccondition> findBySorting3(int month, float temp1, float temp2);
    
    //page karyawan
    @Modifying
    @Query(value = "SELECT employees.name, emp_condition.date, emp_condition.temperature, healty_status.information, emp_condition.idempcondition, emp_condition.idemployee, emp_condition.idhealty FROM employees INNER JOIN emp_condition ON employees.id=emp_condition.idemployee INNER JOIN healty_status ON emp_condition.idhealty=healty_status.id WHERE emp_condition.idemployee =:username", nativeQuery = true)
    List<Ccondition> findbyUSERNAME(@Param("username") String username);
}
