package com.coe.moi.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coe.moi.core.entity.Action;
import com.coe.moi.core.entity.Board;
import com.coe.moi.core.entity.IotAction;
import com.coe.moi.core.entity.UserProfile;
import com.coe.moi.core.service.IotService;

@RestController
public class IotServiceController {
	@Autowired
	IotService service;
	
	@GetMapping("/iot/users/id/{id}")
	public ResponseEntity<?>  findUserById(@PathVariable("id") Long id){
		Optional<UserProfile> user=service.findUserById(id);
		return new ResponseEntity<>(user,(user.isPresent())?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/iot/boards/id/{id}")
	public ResponseEntity<?>  findBoardById(@PathVariable("id") Long id){
		Optional<Board> board=service.findBoardById(id);
		return new ResponseEntity<>(board,(board.isPresent())?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/iot/actions/full/id/{id}")
	public ResponseEntity<?>  findActionById(@PathVariable("id") Long id){
		Optional<IotAction> action=service.findActionById(id);
		System.out.println("FindAcctionByID-> "+action);
		return new ResponseEntity<>(action,(action.isPresent())?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/iot/actions/id/{id}")
	public ResponseEntity<?>  findFullActionById(@PathVariable("id") Long id){
		Optional<IotAction> action=service.findActionById(id);
		Optional<Action> actionResponse =iotToAction(action);
		System.out.println("FindAcctionByID-> "+action);
		return new ResponseEntity<>(actionResponse,(actionResponse.isPresent())?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/iot/actions/full/uid/{id}")
	public ResponseEntity<?>  findFullActionByUid(@PathVariable("id") Long id){
		
		List<IotAction> actions=service.findActionByUid(id);
		System.out.println("FindAcctionByUID-> "+actions);
		return new ResponseEntity<>(actions,((actions!=null) && (actions.size()>0))?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/iot/actions/uid/{id}")
	public ResponseEntity<?>  findActionByUid(@PathVariable("id") Long id){
		
		List<IotAction> actions=service.findActionByUid(id);
		List<Action> actionResponses = new ArrayList<Action>();
		actions.stream().forEach(action->actionResponses.add(iotToAction(Optional.of(action)).get()));
		System.out.println("FindAcctionByUID-> "+actionResponses);
		return new ResponseEntity<>(actionResponses,((actionResponses!=null) && (actionResponses.size()>0))?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/iot/users")
	public ResponseEntity<?>  findUsers(){
		List<UserProfile> users=service.findUsers();
		return new ResponseEntity<>(users,((users!=null) && (users.size()>0))?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
		
	}
	@GetMapping("/iot/boards")
	public ResponseEntity<?>  findBoards(){
		List<Board> boards=service.findBoards();
		return new ResponseEntity<>(boards,((boards!=null) && (boards.size()>0))?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
		
	}
	@GetMapping("/iot/actions")
	public ResponseEntity<?>  findActions(){
		List<IotAction> actions=service.findActions();
		return new ResponseEntity<>(actions,((actions!=null) && (actions.size()>0))?HttpStatus.FOUND:HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/iot/users")
	public ResponseEntity<?>  createUser(@RequestBody UserProfile user){
		Optional<UserProfile> newUser=service.createUser(user);
		return new ResponseEntity<>(newUser,(newUser.isPresent())?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@PostMapping("/iot/boards")
	public ResponseEntity<?>  createBoard(@RequestBody Board board){
		Optional<Board> newBoard=service.createBoard(board);
		return new ResponseEntity<>(newBoard,(newBoard.isPresent())?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
		
	}
	@PostMapping("/iot/actions")
	public ResponseEntity<?>  createActions(@RequestBody Action action){
		IotAction iotAction= new IotAction();
		iotAction.setIoAction(action.getIoAction());
		iotAction.setIoId(action.getIoId());
		iotAction.setIoMode(action.getIoMode());
		
		Optional<UserProfile> user = service.findUserById(action.getUserId());
		if(user.isPresent())
			iotAction.setUser(user.get());
		else
			return new ResponseEntity<>(iotAction,HttpStatus.NOT_ACCEPTABLE);
		
		Optional<Board> board = service.findBoardById(action.getBoardId());
		if(board.isPresent())
			iotAction.setBoard(board.get());
		else
			return new ResponseEntity<>(iotAction,HttpStatus.NOT_ACCEPTABLE);
		
		Optional<IotAction> newAction=service.createAction(iotAction);
		return new ResponseEntity<>(newAction,(newAction.isPresent())?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@PutMapping("/iot/users/remove/id/{id}")
	public ResponseEntity<?>  deleteUsertById(@PathVariable("id") Long id){
		service.findActionByUid(id).stream().forEach(action->service.deleteAction(action.getId()));
		Boolean status = service.deleteUser(id);
		return new ResponseEntity<>(status,(status)?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("/iot/boards/remove/id/{id}")
	public ResponseEntity<?>  deleteBoardById(@PathVariable("id") Long id){
		service.findActionByBid(id).stream().forEach(action->service.deleteAction(action.getId()));
		Boolean status = service.deleteBoard(id);
		return new ResponseEntity<>(status,(status)?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("/iot/Actions/remove/id/{id}")
	public ResponseEntity<?>  deleteActionById(@PathVariable("id") Long id){
		Boolean status = service.deleteAction(id);
		return new ResponseEntity<>(status,(status)?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("/iot/actions")
	public ResponseEntity<?>  updateActions(@RequestBody Action action){
		IotAction iotAction= new IotAction();
		
		iotAction.setId(action.getId());
		
		if(action.getIoAction()!=null)
			iotAction.setIoAction(action.getIoAction());
		if(action.getIoId()!=null)
			iotAction.setIoId(action.getIoId());
		if(action.getIoMode()!=null)
			iotAction.setIoMode(action.getIoMode());
		
		Optional<UserProfile> user = service.findUserById(action.getUserId());
		if(user.isPresent())
			iotAction.setUser(user.get());
		else
			return new ResponseEntity<>(iotAction,HttpStatus.NOT_ACCEPTABLE);
		
		Optional<Board> board = service.findBoardById(action.getBoardId());
		if(board.isPresent())
			iotAction.setBoard(board.get());
		else
			return new ResponseEntity<>(iotAction,HttpStatus.NOT_ACCEPTABLE);
		
		IotAction newAction=service.updateAction(iotAction);
		return new ResponseEntity<>(newAction,(newAction!=null)?HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	private Optional<Action> iotToAction(Optional<IotAction> iot)
	{
		Action action = null;
		if(iot.isPresent())
			action= new Action(	iot.get().getId(), 
				iot.get().getIoId(), 
				iot.get().getIoMode(), 
				iot.get().getIoAction(), 
				iot.get().getBoard().getId(), 
				iot.get().getUser().getId());
		
		return Optional.ofNullable(action);
		
	}
	
	private Optional<IotAction> actionToIot(Optional<Action> action)
	{
		IotAction iot = null;
		if(action.isPresent())
			iot=  new IotAction(
				action.get().getId(), 
				action.get().getIoId(), 
				action.get().getIoMode(), 
				action.get().getIoAction(), 
				service.findBoardById(action.get().getBoardId()).get(), 
				service.findUserById(action.get().getUserId()).get()
				) ;
		return Optional.ofNullable(iot);
	}
	
	
}
