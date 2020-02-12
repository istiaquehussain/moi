package com.coe.moi.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coe.moi.core.entity.Board;
import com.coe.moi.core.entity.IotAction;
import com.coe.moi.core.entity.UserProfile;

@Repository
public interface IotActionRepository extends JpaRepository<IotAction, Long> {
	 List<IotAction> findByUser(UserProfile user);
	 List<IotAction> findByBoard(Board board);

}