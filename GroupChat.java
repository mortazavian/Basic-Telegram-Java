import java.util.ArrayList;

public class GroupChat {

    String name;
    String bio;
    int ageRestriction;
    String groupId; // Must be unique!!!!!!!!!
    int id;

    ArrayList<User> admins = new ArrayList<User>();
    ArrayList<User> users = new ArrayList<User>();

    // The messages in the group chat!
    ArrayList<Message> messages = new ArrayList<Message>();

    public GroupChat(String name, String bio, int ageRestriction, String groupId, int id) {
        this.name = name;
        this.bio = bio;
        this.ageRestriction = ageRestriction;
        this.groupId = groupId;
        this.id = id;
    }

}

// Admins:
// ▪ First User That Create the Group is Consider its Admin
// ▪ Admins Can Add New Members Using Their User-ID or Phone-Number or Kick One
// from the Group.
// ▪ Admins Can Promote a Member to Admin
// ▪ Admins Can Delete Any Message they wish
// ▪ Admins Can Delete the Entire Group

// Members:
// ▪ Members Can Also Join a Group by Searching Its Group-ID
// ▪ Members Whose Age is Under Group’s Age -Restriction Shouldn’t Be Able to
// Join.