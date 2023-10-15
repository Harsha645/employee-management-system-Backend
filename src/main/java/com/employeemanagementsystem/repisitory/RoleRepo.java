package com.employeemanagementsystem.repisitory;

import com.employeemanagementsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
}
