import java.util.ArrayList;

public class Channel {

    String name;
    String bio;
    String channelId; // Must be unique!!!!!!!!!!!

    ArrayList<User> users = new ArrayList<User>();
    ArrayList<User> admins = new ArrayList<User>();

    ArrayList<Message> messages = new ArrayList<Message>();

    // This is constructor
    public Channel(String name, String bio, String channelId) {
        this.name = name;
        this.bio = bio;
        this.channelId = channelId;
    }

}
