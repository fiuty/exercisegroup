package com.dayue.security.repository;


import com.dayue.security.configure.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhengdayue
 */
public interface UserDetailsRepository extends JpaRepository<UserDetailsImpl, Long>, JpaSpecificationExecutor<UserDetailsImpl> {

    UserDetailsImpl findByUsername(String username);
}
