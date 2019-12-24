package com.george.chatapp.chatroom.domain.service.implementation;

import com.george.chatapp.chatroom.domain.model.ChatRoom;
import com.george.chatapp.chatroom.domain.model.InstantMessage;
import com.george.chatapp.chatroom.domain.repository.InstantMessageRepository;
import com.george.chatapp.chatroom.domain.service.ChatRoomService;
import com.george.chatapp.chatroom.domain.service.InstantMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoInstantMessageService implements InstantMessageService {

    @Autowired
    private InstantMessageRepository instantMessageRepository;

    @Autowired
    private ChatRoomService chatRoomService;

    @Override
    public void appendInstantMessageToConversations(InstantMessage instantMessage) {
        if (instantMessage.isFromAdmin() || instantMessage.isPublic()) {
            ChatRoom chatRoom = chatRoomService.findById(instantMessage.getChatRoomId());

            chatRoom.getConnectedUsers().forEach(connectedUser -> {
                instantMessage.setUsername(connectedUser.getUsername());
                instantMessageRepository.save(instantMessage);
            });
        } else {
            instantMessage.setUsername(instantMessage.getFromUser());
            instantMessageRepository.save(instantMessage);

            instantMessage.setUsername(instantMessage.getToUser());
            instantMessageRepository.save(instantMessage);
        }
    }

    @Override
    public List<InstantMessage> findAllInstantMessagesFor(String username, String chatRoomId) {

        return instantMessageRepository.findInstantMessagesByUsernameAndChatRoomId(username, chatRoomId);
    }
}
