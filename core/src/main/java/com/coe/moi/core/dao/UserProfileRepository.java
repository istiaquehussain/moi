package com.coe.moi.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coe.moi.core.entity.UserProfile;
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}
