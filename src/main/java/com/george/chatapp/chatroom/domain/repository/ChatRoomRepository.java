package com.george.chatapp.chatroom.domain.repository;

import com.george.chatapp.chatroom.domain.model.ChatRoom;
import org.springframework.data.repository.CrudRepository;


public interface ChatRoomRepository extends CrudRepository<ChatRoom, String> {

}
