package com.coe.moi.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coe.moi.core.dao.BoardRepository;
import com.coe.moi.core.dao.IotActionRepository;
import com.coe.moi.core.dao.UserProfileRepository;
import com.coe.moi.core.entity.Board;
import com.coe.moi.core.entity.IotAction;
import com.coe.moi.core.entity.UserProfile;
@Service
public class IotServiceImpl implements IotService {

	@Autowired
	BoardRepository boardRepo;
	@Autowired
	UserProfileRepository userRepo;
	@Autowired
	IotActionRepository actionRepo;
	
	@Override
	public Optional<UserProfile> findUserById(Long uid) {
		return userRepo.findById(uid);
	}

	@Override
	public List<UserProfile> findUsers() {
		return userRepo.findAll();
	}

	@Override
	public Optional<UserProfile> createUser(UserProfile user) {
		return Optional.of(userRepo.save(user));
	}

	@Override
	public Boolean deleteUser(Long uid) {
		userRepo.deleteById(uid);
		return userRepo.existsById(uid);
	}

	@Override
	public UserProfile updateUser(UserProfile user) {
		return userRepo.save(user);
	}

	@Override
	public Optional<Board> findBoardById(Long bid) {
		return boardRepo.findById(bid);
	}

	@Override
	public List<Board> findBoards() {
		return boardRepo.findAll();
	}

	@Override
	public Optional<Board> createBoard(Board board) {
		return Optional.of(boardRepo.save(board));
	}

	@Override
	public Boolean deleteBoard(Long bid) {
		boardRepo.deleteById(bid);
		return boardRepo.existsById(bid);
	}

	@Override
	public Board updateBoard(Board board) {
		return boardRepo.save(board);
	}

	@Override
	public Optional<IotAction> findActionById(Long aid) {
		return actionRepo.findById(aid);
	}

	@Override
	public List<IotAction> findActions() {
		return actionRepo.findAll();
	}

	@Override
	public Optional<IotAction> createAction(IotAction action) {
		return Optional.of(actionRepo.save(action));
	}

	@Override
	public Boolean deleteAction(Long aid) {
		actionRepo.deleteById(aid);
		return actionRepo.existsById(aid);
	}

	@Override
	public List<IotAction> findActionByUid(Long uid) {
		return actionRepo.findIotActionByUid(uid);
	}
	
	

	@Override
	public List<IotAction> findActionByBid(Long bid) {
		return actionRepo.findIotActionByBid(bid);
	}

	@Override
	public IotAction updateAction(IotAction action) {
		return actionRepo.save(action);
	}

}
