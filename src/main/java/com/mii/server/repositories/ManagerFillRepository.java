/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.repositories;

import com.mii.server.entities.ManagerFill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jakab
 */
@Repository
public interface ManagerFillRepository extends JpaRepository<ManagerFill, Integer>{
    
    public List<ManagerFill> findByManagerId(Integer managerId);
    
}
