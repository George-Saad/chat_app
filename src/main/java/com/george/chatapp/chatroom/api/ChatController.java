package com.george.chatapp.chatroom.api;

import java.util.List;

import com.george.chatapp.chatroom.domain.model.ChatRoom;
import com.george.chatapp.chatroom.domain.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {
	
	@Autowired
	private ChatRoomService chatRoomService;
	
    @RequestMapping("/chat")
    public ModelAndView getRooms() {
    	ModelAndView modelAndView = new ModelAndView("chat");
    	List<ChatRoom> chatRooms = chatRoomService.findAll();
    	modelAndView.addObject("chatRooms", chatRooms);
    	return modelAndView;
    }
}
