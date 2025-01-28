package com.rezende.learn.repositories;

import com.rezende.learn.entities.User;
import com.rezende.learn.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(nativeQuery = true, value =
            "SELECT " +
            "tb_users.email AS username, " +
            "tb_users.password, " +
            "tb_role.id AS roleId, " +
            "tb_role.authority " +
            "FROM tb_users " +
            "INNER JOIN tb_user_role ON tb_users.id = tb_user_role.user_id  " +
            "INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id " +
            "WHERE tb_users.email = :username")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String username);

    @Query(nativeQuery = true, value =
            "SELECT " +
                    "tb_users.email AS username, " +
                    "tb_users.password, " +
                    "tb_role.id AS roleId, " +
                    "tb_role.authority " +
                    "FROM tb_users " +
                    "INNER JOIN tb_user_role ON tb_users.id = tb_user_role.user_id  " +
                    "INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id " +
                    "WHERE tb_users.email = :username")
    List<UserDetailsProjection> findByUsername(String username);

}
