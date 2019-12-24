package com.george.chatapp.chatroom.domain.model;

import com.george.chatapp.utils.SystemUsers;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;

@Document(collection = "messages")
@CompoundIndex(def = "{'chatRoomId':1,'username':-1}", name = "chatRoomId_1_username_-1")
public class InstantMessage {

	@Indexed
	private String username;
	private String chatRoomId;
	private Date date;
	private String fromUser;
	private String toUser;
	private String text;

	public InstantMessage() {
		this.date = new Date();
	}

	public boolean isPublic() { return this.toUser == null || this.toUser.isEmpty(); }
	public boolean isFromAdmin() {
		return this.fromUser.equals(SystemUsers.ADMIN.getUsername());
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chatRoomId == null) ? 0 : chatRoomId.hashCode());
		result = prime * result + ((fromUser == null) ? 0 : fromUser.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((toUser == null) ? 0 : toUser.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstantMessage other = (InstantMessage) obj;
		if (chatRoomId == null) {
			if (other.chatRoomId != null)
				return false;
		} else if (!chatRoomId.equals(other.chatRoomId))
			return false;
		if (fromUser == null) {
			if (other.fromUser != null)
				return false;
		} else if (!fromUser.equals(other.fromUser))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (toUser == null) {
			if (other.toUser != null)
				return false;
		} else if (!toUser.equals(other.toUser))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
