package com.example.ems.repository;

import com.example.ems.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findById(String userId);

    @Query("SELECT u from User u where (lower(u.userName) = lower(:name))")
    Optional<User> findByUserName(String name);
    @Query("SELECT u from User u where  (lower(u.userName) = lower(:name)) AND u.id !=:id")
    Optional<User> findByIdNotAndUserName(String id, String name);


    @Query(value = "SELECT u from User u" +
            " where(:userName IS NULL OR  lower(u.userName ) LIKE '%' || lower(cast(:userName as String)) || '%' )" +
            "AND (:name IS NULL OR  lower(u.name ) LIKE '%' || lower(cast(:name as String)) || '%' ) " +
            "AND (:email IS NULL OR  lower(u.email ) LIKE '%' || lower(cast(:email as String)) || '%') " +
            "AND ( u.role.id IS NULL OR u.role.id IS NOT NULL )" +
            "AND (:roleName IS NULL OR   lower(u.role.name)  LIKE  '%'  || lower(cast(:roleName as String)) || '%' ) " +
            "AND (:roleCode IS NULL OR  lower(u.role.code ) LIKE '%' || lower(cast(:roleCode as String)) || '%' ) "
    )
    Page<User> fetchUsers
            (String userName,
             String name,
             String email,
             String roleName,
             String roleCode,
             Pageable pageable);
}