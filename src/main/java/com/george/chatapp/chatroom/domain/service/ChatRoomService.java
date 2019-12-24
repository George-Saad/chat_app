package com.george.chatapp.chatroom.domain.service;

import java.util.List;

import com.george.chatapp.chatroom.domain.model.ChatRoom;
import com.george.chatapp.chatroom.domain.model.ChatRoomUser;
import com.george.chatapp.chatroom.domain.model.InstantMessage;
import org.springframework.stereotype.Service;


public interface ChatRoomService {
	
	ChatRoom save(ChatRoom chatRoom);
	ChatRoom findById(String chatRoomId);
	ChatRoom join(ChatRoomUser joiningUser, ChatRoom chatRoom);
	ChatRoom leave(ChatRoomUser leavingUser, ChatRoom chatRoom);
	void sendPublicMessage(InstantMessage instantMessage);
	void sendPrivateMessage(InstantMessage instantMessage);
	List<ChatRoom> findAll();
}
