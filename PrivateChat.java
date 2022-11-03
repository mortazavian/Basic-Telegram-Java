import java.util.ArrayList;

public class PrivateChat {
    User user1;
    User user2;
    // int id;

    ArrayList<Message> messages = new ArrayList<Message>();

    public PrivateChat(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        // this.id = id;
    }

}
