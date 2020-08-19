/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.repositories;

import com.exerciseSpringBoot.crudBootstrap.entities.EmpCondition;
import com.exerciseSpringBoot.crudBootstrap.entities.HealtyStatus;
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
public interface EmpconditionRepository extends JpaRepository<EmpCondition, Integer>{
    @Modifying
    @Query(value = "SELECT*FROM emp_condition e INNER JOIN healty_status h ON e.idhealty=h.id WHERE e.idemployee =:username", nativeQuery = true)
    List<EmpCondition> findbyUSERNAME(@Param("username") String username);
    
    @Modifying
    @Query(value = "SELECT*FROM emp_condition ORDER BY emp_condition.idemployee ASC, emp_condition.date ASC", nativeQuery = true)
    List<EmpCondition> findAllConditions();
     
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO emp_condition(idemployee,date,temperature,idhealty) VALUES (?1,?2,?3,?4);",nativeQuery = true)
    void saveinput(String idemployee, LocalDate date, float temperature, String idhealty);
    
    @Query(value="SELECT COUNT(*) FROM emp_condition where emp_condition.date =?1 AND emp_condition.idemployee=?2", nativeQuery= true)
    int hitung(LocalDate tanggal, String username);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM emp_condition WHERE idempcondition=?1",nativeQuery = true)
    void deletedata(@Param("idempcondition") int idempcondition);
    
    @Transactional
    @Modifying
    @Query(value="UPDATE emp_condition u SET u.idemployee=?1, u.date=?2, u.temperature=?3, u.idhealty=?4 WHERE u.idempcondition=?5",nativeQuery = true)
    void update(String idemployee, Date date, float temperature, String idhealty, int idempcondition);
    
    @Modifying
    @Query(value = "SELECT*FROM emp_condition WHERE emp_condition.temperature > 37 ORDER BY emp_condition.idemployee ASC, emp_condition.date ASC", nativeQuery = true)
    List<EmpCondition> findByIlness();
    
    @Modifying
    @Query(value = "SELECT emp_condition.idempcondition, emp_condition.idemployee, emp_condition.date, emp_condition.temperature, emp_condition.idhealty FROM emp_condition  INNER JOIN employees ON emp_condition.idemployee=employees.id WHERE employees.name=?1 AND MONTH(emp_condition.date)=?2 AND (emp_condition.temperature BETWEEN ?3 AND ?4) ORDER BY emp_condition.date ASC;" , nativeQuery = true)
    List<EmpCondition> findBySorting(String name, int month, float temp1, float temp2);
   

    @Modifying
    @Query(value = "SELECT emp_condition.idempcondition, emp_condition.idemployee, emp_condition.date, emp_condition.temperature, emp_condition.idhealty FROM emp_condition  INNER JOIN employees ON emp_condition.idemployee=employees.id WHERE (emp_condition.temperature BETWEEN ?1 AND ?2) ORDER BY emp_condition.date ASC;" , nativeQuery = true)
    List<EmpCondition> findBySorting1(float temp1, float temp2);
    

    @Modifying
    @Query(value = "SELECT emp_condition.idempcondition, emp_condition.idemployee, emp_condition.date, emp_condition.temperature, emp_condition.idhealty FROM emp_condition  INNER JOIN employees ON emp_condition.idemployee=employees.id WHERE employees.name=?1 AND (emp_condition.temperature BETWEEN ?2 AND ?3) ORDER BY emp_condition.date ASC;" , nativeQuery = true)
    List<EmpCondition> findBySorting2(String name, float temp1, float temp2);
    

    @Modifying
    @Query(value = "SELECT emp_condition.idempcondition, emp_condition.idemployee, emp_condition.date, emp_condition.temperature, emp_condition.idhealty FROM emp_condition  INNER JOIN employees ON emp_condition.idemployee=employees.id WHERE MONTH(emp_condition.date)=?1 AND (emp_condition.temperature BETWEEN ?2 AND ?3) ORDER BY emp_condition.date ASC;" , nativeQuery = true)
    List<EmpCondition> findBySorting3(int month, float temp1, float temp2);
    
    @Modifying
    @Query(value = "SELECT emp_condition.idempcondition, emp_condition.idemployee, emp_condition.date, emp_condition.temperature, emp_condition.idhealty FROM emp_condition  INNER JOIN employees ON emp_condition.idemployee=employees.id WHERE employees.name=?1 ORDER BY emp_condition.date ASC;" , nativeQuery = true)
    List<EmpCondition> findByGrafik(String name);
}
