package com.george.chatapp.chatroom.domain.service;

import com.george.chatapp.chatroom.domain.model.InstantMessage;

import java.util.List;

public interface InstantMessageService {

	void appendInstantMessageToConversations(InstantMessage instantMessage);
	List<InstantMessage> findAllInstantMessagesFor(String username, String chatRoomId);
}
