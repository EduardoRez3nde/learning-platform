package com.rezende.learn.repositories;

import com.rezende.learn.entities.Role;
import com.rezende.learn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
