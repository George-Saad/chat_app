package com.george.chatapp.chatroom.domain.service.implementation;

import java.util.List;
import java.util.Optional;

import com.george.chatapp.chatroom.domain.model.ChatRoom;
import com.george.chatapp.chatroom.domain.model.ChatRoomUser;
import com.george.chatapp.chatroom.domain.model.InstantMessage;
import com.george.chatapp.chatroom.domain.repository.ChatRoomRepository;
import com.george.chatapp.chatroom.domain.service.ChatRoomService;
import com.george.chatapp.chatroom.domain.service.InstantMessageService;
import com.george.chatapp.utils.Destinations;
import com.george.chatapp.utils.SystemMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;


@Service
public class RedisChatRoomService implements ChatRoomService {

	@Autowired
	private SimpMessagingTemplate webSocketMessagingTemplate;

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	@Autowired
	private InstantMessageService instantMessageService;

	@Override
	public ChatRoom save(ChatRoom chatRoom) {
		return chatRoomRepository.save(chatRoom);
	}

	@Override
	public List<ChatRoom> findAll() {
		return (List<ChatRoom>) chatRoomRepository.findAll();
	}

	@Override
	public ChatRoom findById(String chatRoomId) {
		Optional<ChatRoom> optional = chatRoomRepository.findById(chatRoomId);
			return optional.get();
	}

	@Override
	public ChatRoom join(ChatRoomUser joiningUser, ChatRoom chatRoom) {
		chatRoom.addUser(joiningUser);
		chatRoomRepository.save(chatRoom);

		sendPublicMessage(SystemMessages.welcome(chatRoom.getId(), joiningUser.getUsername()));
		updateConnectedUsersViaWebSocket(chatRoom);
		return chatRoom;
	}

	@Override
	public ChatRoom leave(ChatRoomUser leavingUser, ChatRoom chatRoom) {
		sendPublicMessage(SystemMessages.goodbye(chatRoom.getId(), leavingUser.getUsername()));

		chatRoom.removeUser(leavingUser);
		chatRoomRepository.save(chatRoom);

		updateConnectedUsersViaWebSocket(chatRoom);
		return chatRoom;
	}

	@Override
	public void sendPublicMessage(InstantMessage instantMessage) {
		webSocketMessagingTemplate.convertAndSend(
				Destinations.ChatRoom.publicMessages(instantMessage.getChatRoomId()),
				instantMessage);

		instantMessageService.appendInstantMessageToConversations(instantMessage);
	}

	@Override
	public void sendPrivateMessage(InstantMessage instantMessage) {
		webSocketMessagingTemplate.convertAndSendToUser(
				instantMessage.getToUser(),
				Destinations.ChatRoom.privateMessages(instantMessage.getChatRoomId()),
				instantMessage);

		webSocketMessagingTemplate.convertAndSendToUser(
				instantMessage.getFromUser(),
				Destinations.ChatRoom.privateMessages(instantMessage.getChatRoomId()),
				instantMessage);

		instantMessageService.appendInstantMessageToConversations(instantMessage);
	}


	private void updateConnectedUsersViaWebSocket(ChatRoom chatRoom) {
		webSocketMessagingTemplate.convertAndSend(
				Destinations.ChatRoom.connectedUsers(chatRoom.getId()),
				chatRoom.getConnectedUsers());
	}
}
