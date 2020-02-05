package com.coe.moi.core.service;

import java.util.List;
import java.util.Optional;

import com.coe.moi.core.entity.Board;
import com.coe.moi.core.entity.Employee;
import com.coe.moi.core.entity.IotAction;
import com.coe.moi.core.entity.UserProfile;

public interface IotService {
	
	Optional<UserProfile> findUserById(Long uid);
	
	List<UserProfile> findUsers();
	
	Optional<UserProfile> createUser(UserProfile user);
	
	Boolean deleteUser(Long uid);
	
	UserProfile updateUser(UserProfile user);
	
	
	
	
	Optional<Board> findBoardById(Long bid);
	
	List<Board> findBoards();
	
	Optional<Board> createBoard(Board board);
	
	Boolean deleteBoard(Long uid);
	
	Board updateBoard(Board board);
	
	
	
	
	Optional<IotAction> findActionById(Long aid);
	
	List<IotAction> findActions();
	
	Optional<IotAction> createAction(IotAction action);
	
	Boolean deleteAction(Long aid);
	
	Optional<IotAction> findActionByUid(Long uid);
	
	IotAction updateAction(IotAction action);
	
	
}
