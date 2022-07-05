package com.sibs.domain.repository;

import com.sibs.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author geraldobarrosjr
 */


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContaining(String username);

    List<User> findByEmailContaining(String email);

    User findByEmail(String email);




}

