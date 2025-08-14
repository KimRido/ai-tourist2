package com.example.backend.domain.user.repository;

import com.example.backend.domain.user.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);
    Optional<UserEntity> findByUsernameAndIsLockAndIsSocial(String username, Boolean isLock, Boolean isSocial);
    Optional<UserEntity> findByUsernameAndIsSocial(String username, Boolean isSocial);

    @Transactional
    void deleteByUsername(String username);

    Optional<UserEntity> findByUsernameAndIsLock(String username, Boolean isLock);

}
