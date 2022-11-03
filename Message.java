import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Message {

    static int id = 0;
    String caption;
    User sender;
    GroupChat groupChat;
    PrivateChat privateChat;
    Channel channel;

    // Set<User> likedBy = new Set<user>();

    Set<User> likedBy = new HashSet<User>();

    public Message(User sender, String caption, PrivateChat privateChat, GroupChat groupChat, Channel channel) {
        this.sender = sender;
        this.caption = caption;
        this.privateChat = privateChat;
        this.caption = caption;
        this.groupChat = groupChat;
        this.channel = channel;
    }

}
