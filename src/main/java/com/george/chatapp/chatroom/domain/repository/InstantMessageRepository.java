package com.george.chatapp.chatroom.domain.repository;


import com.george.chatapp.chatroom.domain.model.InstantMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface InstantMessageRepository extends MongoRepository<InstantMessage, String> {

    List<InstantMessage> findInstantMessagesByUsernameAndChatRoomId(String username, String chatRoomId);
}
