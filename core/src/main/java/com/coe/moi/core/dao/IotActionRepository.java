package com.coe.moi.core.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coe.moi.core.entity.IotAction;
@Repository
public interface IotActionRepository extends JpaRepository<IotAction, Long> {
	Optional<IotAction> findIotActionByUid(Long uid);

}
