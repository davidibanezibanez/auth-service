package com.university.authservice.persistence.repository;

import com.university.authservice.persistence.entity.RoleEntity;
import com.university.authservice.persistence.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findRolesByRoleNameIn(List<RoleEnum> roleNames);
}
