import java.util.ArrayList;
import javax.swing.JOptionPane;

public class User {

    private String name;
    // Date birthDate;
    private Gender gender;
    private String phoneNumber;
    private String userId; // Must be unique
    private String password;
    private int age;

    private ArrayList<Channel> channels = new ArrayList<Channel>();

    private ArrayList<GroupChat> groupChats = new ArrayList<GroupChat>();

    private ArrayList<PrivateChat> privateChats = new ArrayList<PrivateChat>();

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    public ArrayList<GroupChat> getGroupChats() {
        return groupChats;
    }

    public void setGroupChats(ArrayList<GroupChat> groupChats) {
        this.groupChats = groupChats;
    }

    public ArrayList<PrivateChat> getPrivateChats() {
        return privateChats;
    }

    public void setPrivateChats(ArrayList<PrivateChat> privateChats) {
        this.privateChats = privateChats;
    }

    public User(String name, Gender gender, int age, String phoneNumber, String userId, String password) {
        this.name = name;
        // this.birthDate = birthDate;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ------------------------Just to Create Instance-----------------------
    public static void instanceCreator() {

        // Creating 4 instance of User class
        User user4 = new User("Reza Dolati", Gender.MALE, 18,
                "+0171451234", "1",
                "1");

        User user1 = new User("Mehdi Mortazavian", Gender.MALE, 22,
                "+98903820773", "mortazavian_mehdi",
                "Mehdi123");

        User user2 = new User("Mohsen Zahamtkesh", Gender.MALE, 28,
                "+9126051223", "mohamadzahmatkesh",
                "Mohammadz1234");

        User user3 = new User("Sara Ahmadi", Gender.FEMALE, 45,
                "+9354120099", "ahmadis",
                "SaRa1380");

        DataBase.users.add(user4);
        DataBase.users.add(user1);
        DataBase.users.add(user2);
        DataBase.users.add(user3);

        // Create 1 instance of GroupChat class
        GroupChat groupChat1 = new GroupChat("gorooh1", "SIILAAM!", 23,
                "emam", ChatRoomId.assignId());

        groupChat1.admins.add(user4);
        groupChat1.admins.add(user1);

        groupChat1.users.add(user1);
        groupChat1.users.add(user2);

        DataBase.groupChats.add(groupChat1);

        Message message = new Message(user1, "dorood bar to", null, groupChat1, null);
        groupChat1.messages.add(message);

        Channel channel = new Channel("mychannel", "the best channel in the world", "channel1");

        channel.admins.add(user1);
        channel.admins.add(user2);

        channel.users.add(user4);
        channel.users.add(user3);

        DataBase.channels.add(channel);

    }

    // --------------------------Sign Up--------------------------------
    public static void signUp() {
        String name;
        Gender gender;
        String phoneNumber;
        String userId;
        String password;
        int age;

        DataBase.showAllUsers();

        System.out.println("--------------------------------------");

        name = JOptionPane.showInputDialog(null, "Please enter your name: ");

        int genderGetter = Integer
                .parseInt(JOptionPane.showInputDialog(null, "Please enter your gender\n1.man\n2.woman: "));
        switch (genderGetter) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            default:
                gender = Gender.NOT_DEFINED;
                break;
        }

        age = Integer.parseInt(
                JOptionPane.showInputDialog(null, "Please enter your age", "Sign In", JOptionPane.DEFAULT_OPTION));

        phoneNumber = JOptionPane.showInputDialog(null, "Please enter your phone number in +98xxxxxxxxxx format: ");

        userId = JOptionPane.showInputDialog(null,
                "Here is the tricky part!\nyour user id must be unique\nplease enter your user id until we can accept it as a unique one :)");

        while (DataBase.isUniqueUserName(userId) == false) {
            JOptionPane.showMessageDialog(null, "your user id is not unique!", "user id", JOptionPane.ERROR_MESSAGE);

            userId = JOptionPane.showInputDialog(null,
                    "Please enter your user id again :)");
        }

        password = JOptionPane.showInputDialog(null, "Please enter your password: ");

        User user = new User(name, gender, age, phoneNumber, userId.toLowerCase(), password);

        DataBase.users.add(user);

        DataBase.showAllUsers();

        System.out.println("--------------------------------------");
    }

    // ------------------------------Log In--------------------------
    public static int logIn() {

        String inputUserId;
        String inputPassword;

        JOptionPane.showMessageDialog(null, "Welcome again my friend :)");
        inputUserId = JOptionPane.showInputDialog(null, "please enter your user id: ");
        inputPassword = JOptionPane.showInputDialog(null, "please enter your password: ");

        while (DataBase.isValidToSignIn(inputUserId, inputPassword) == -1) {

            JOptionPane.showMessageDialog(null, "Either the user id or the password is incorrect!");
            inputUserId = JOptionPane.showInputDialog(null, "please enter your user id again: ");
            inputPassword = JOptionPane.showInputDialog(null, "please enter your password again: ");
        }

        return DataBase.isValidToSignIn(inputUserId, inputPassword);

    }

    // -----------------------------Chat Page---------------------------
    public static void chatPage(User currentUser) {

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Which kind of chats you want to enter?\n1.Private Chats\n2.Group Chats\n3.Channels", "Chat Page",
                JOptionPane.DEFAULT_OPTION));

        switch (userInput) {
            case 1:
                privateChat(currentUser);
                break;

            case 2:
                groupChat(currentUser);
                break;

            case 3:
                channel(currentUser);
                break;

            default:
                break;
        }

    }

    // ===============================PRIVATE CHAT==============================

    public static void privateChat(User currentUser) {
        JOptionPane.showMessageDialog(null, "This is Private Chat page\nClick OK to continue", "Private Chat",
                JOptionPane.DEFAULT_OPTION);

        // 1.Go to an existing chat!
        // 2.Start a new chat with another user!

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null, "1.Go to private chats\n2.Start new chat",
                "Private Chat", JOptionPane.DEFAULT_OPTION));

        switch (userInput) {
            case 1:
                continueAnExistingChat(currentUser);
                break;

            case 2:
                startNewPrivateChat(currentUser);
                break;

            default:
                break;
        }

    }

    public static void startNewPrivateChat(User currentUser) {

        String userToChatWith = JOptionPane.showInputDialog(null, "Please enter the user id of your friend",
                "Private Chat", JOptionPane.DEFAULT_OPTION);

        if (DataBase.isFoundUserId(userToChatWith)) {
            // PrivateChat privateChatInstance = new PrivateChat(currentUser,
            // DataBase.findUserByUserId(userToChatWith),
            // ChatRoomId.assignId());
        } else {
            while (DataBase.isFoundUserId(userToChatWith) == false) {
                JOptionPane.showMessageDialog(null, "The user id you entered is not in the system!", "Private Chat",
                        JOptionPane.ERROR_MESSAGE);

                userToChatWith = JOptionPane.showInputDialog(null, "Please enter the user id of your friend again!",
                        "Private Chat", JOptionPane.DEFAULT_OPTION);
            }

        }

        PrivateChat privateChatInstance1 = new PrivateChat(currentUser,
                DataBase.findUserByUserId(userToChatWith));

        PrivateChat privateChatInstance2 = new PrivateChat(DataBase.findUserByUserId(userToChatWith), currentUser);

        DataBase.privateChats.add(privateChatInstance1);

        currentUser.getPrivateChats().add(privateChatInstance1);

        // Add the chat to the receiver Private Chats
        DataBase.findUserByUserId(userToChatWith).getPrivateChats().add(privateChatInstance2);

        DataBase.showPeopleInPrivateChat(privateChatInstance1);

    }

    public static void continueAnExistingChat(User currentUser) {
        // Just for the mother fucking test in the middle of nowhere!

        JOptionPane.showMessageDialog(null, "You are going to continue an existing private chat!\nClick Ok to continue",
                "Private Chat", JOptionPane.DEFAULT_OPTION);

        String userToChatWith = JOptionPane.showInputDialog(null,
                "please enter the user id of the person you want to continue chat with", "Private Chat",
                JOptionPane.DEFAULT_OPTION);

        if (DataBase.isFoundUserId(userToChatWith)) {

        } else {
            while (DataBase.isFoundUserId(userToChatWith) == false) {

                JOptionPane.showMessageDialog(null, "The user id is not in the system!", "Private Chat",
                        JOptionPane.ERROR_MESSAGE);
                userToChatWith = JOptionPane.showInputDialog(null,
                        "please enter the user id of the person you want to continue chat with again", "Private Chat",
                        JOptionPane.DEFAULT_OPTION);
            }

        }

        String text = JOptionPane.showInputDialog(null, "Please enter the text you want to send and then click OK",
                "Private Chat", JOptionPane.DEFAULT_OPTION);

        Message message = new Message(currentUser, text,
                DataBase.privateChats.get(
                        DataBase.returnIndexOfPrivateChat(currentUser, DataBase.returnUserByUserId(userToChatWith))),
                null, null);

        DataBase.privateChats
                .get(DataBase.returnIndexOfPrivateChat(currentUser, DataBase.findUserByUserId(userToChatWith))).messages
                .add(message);

        DataBase.showMessagesInPrivateChat(currentUser, DataBase.findUserByUserId(userToChatWith));

    }

    // ===============================GROUP CHAT===========================

    public static void groupChat(User currentUser) {

        JOptionPane.showMessageDialog(null, "This is Group Chat page\nClick OK to continue", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "1.Go to group chats\n2.Start new group chat", "Group Chat", JOptionPane.DEFAULT_OPTION));

        // 1 -> Go to group chats
        // 2-> Start new group chat
        switch (userInput) {
            case 1:
                continueAnExistingGroupChat(currentUser);
                break;

            case 2:
                startNewGroupChat(currentUser);
                break;

            default:
                break;
        }

    }

    public static void startNewGroupChat(User currentUser) {

        String name;
        String bio;
        int ageRestriction;
        String groupId; // Must be unique!!!!

        JOptionPane.showMessageDialog(null, "You are going to create a new group chat\nPlease click OK to continue",
                "Group Chat", JOptionPane.DEFAULT_OPTION);

        name = JOptionPane.showInputDialog(null, "Please enter the name of the group and then click OK", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        bio = JOptionPane.showInputDialog(null, "Please enter the bio of the group and then click OK", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        ageRestriction = Integer.parseInt(
                JOptionPane.showInputDialog(null, "Please enter the age restriction of the group and then click OK",
                        "Group Chat",
                        JOptionPane.DEFAULT_OPTION));

        groupId = JOptionPane.showInputDialog(null, "Please enter the group id of the group and then click OK",
                "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        while (DataBase.isFoundGroup(groupId) == true) {
            JOptionPane.showMessageDialog(null,
                    "The group id is used by another group\nPlease click OK and then enter another group id",
                    "Group Chat", JOptionPane.ERROR_MESSAGE);

            groupId = JOptionPane.showInputDialog(null, "Please enter the group id again and then click OK",
                    "Group Chat",
                    JOptionPane.DEFAULT_OPTION);
        }

        GroupChat groupChat = new GroupChat(name, bio, ageRestriction, groupId, ChatRoomId.assignId());

        groupChat.admins.add(currentUser);

        DataBase.groupChats.add(groupChat);

        DataBase.showAdminsOfGroup(groupId);

    }

    public static void continueAnExistingGroupChat(User currentUser) {

        String groupId;

        JOptionPane.showMessageDialog(null, "You are going to continue group chat\nPlease click OK to continue",
                "Group Chat", JOptionPane.DEFAULT_OPTION);

        groupId = JOptionPane.showInputDialog(null, "Please enter the group id of the group and then click OK",
                "Group Chat", JOptionPane.DEFAULT_OPTION);

        while (DataBase.isFoundGroup(groupId) == false) {

            JOptionPane.showMessageDialog(null,
                    "The group id is not found!\nPlease click OK and then enter another group id",
                    "Group Chat", JOptionPane.ERROR_MESSAGE);

            groupId = JOptionPane.showInputDialog(null, "Please enter the group id of the group and then click OK",
                    "Group Chat", JOptionPane.DEFAULT_OPTION);
        }

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Choose one:\n1.Add Member\n2.Kick Member\n3.Promote to Admin\n4.Delete Message\n5.Delete Group\n6.Leave Group",
                "Group Chat", JOptionPane.DEFAULT_OPTION));

        // 1 -> Add member
        // 2 -> Kick member
        // 3 -> Promote to admin
        // 4 -> Delete message
        switch (userInput) {

            case 1:
                addMemberGroup(currentUser, groupId);
                break;

            case 2:
                kickMemberGroup(currentUser, groupId);
                break;

            case 3:
                addAdminGroup(currentUser, groupId);
                break;

            case 4:
                deleteMessageGroup(currentUser, groupId);
                break;

            case 5:
                deleteEntireGroup(currentUser, groupId);
                break;

            case 6:
                leaveGroup(currentUser, groupId);

            case 7:
                sendMessageGroup(currentUser, groupId);

            default:
                break;
        }

    }

    public static void addMemberGroup(User currentUser, String groupToAdd) {

        String userToAdd;

        DataBase.showMemberOfGroup(groupToAdd);

        // String groupToAdd;

        JOptionPane.showMessageDialog(null, "You are going to add a member to the group\nPlease click OK to continue",
                "Group Chat", JOptionPane.DEFAULT_OPTION);

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Choose one:\n1.Add by userId\n2.Add by phone number", "Group Chat", JOptionPane.DEFAULT_OPTION));

        switch (userInput) {
            case 1:
                userToAdd = JOptionPane.showInputDialog(null,
                        "Please enter the user id of the user you want to add.\nThen click OK", "Group Chat",
                        JOptionPane.DEFAULT_OPTION);

                while (DataBase.isFoundUserId(userToAdd) == false) {
                    JOptionPane.showMessageDialog(null,
                            "The user id you just entered is not in the system\nclick OK and the enter another one",
                            "Group Chat", JOptionPane.ERROR_MESSAGE);

                    userToAdd = JOptionPane.showInputDialog(null,
                            "Please enter the user id of the user you want to add again.\nThen click OK", "Group Chat",
                            JOptionPane.DEFAULT_OPTION);
                }

                User tempUser = DataBase.returnUserByUserId(userToAdd);

                if (DataBase.isAdmin(currentUser, groupToAdd) == true) {
                    // Create a function -----> find the group!
                    DataBase.groupChats.get(DataBase.returnIndexOfGroup(groupToAdd)).users.add(tempUser);
                }

                else {
                    JOptionPane.showMessageDialog(null,
                            "You are not the admin of the group!\nAsk admin(s) to promote you to admin and then add the user you want!",
                            "Group Chat", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 2:

                String phoneNumberOfUserToAdd = JOptionPane.showInputDialog(null,
                        "Please enter the phone number of the user you want to add.\nThen click OK", "Group Chat",
                        JOptionPane.DEFAULT_OPTION);

                while (DataBase.isFoundByPhoneNumber(phoneNumberOfUserToAdd) == false) {
                    JOptionPane.showMessageDialog(null,
                            "The phone number you just entered is not in the system\nclick OK and the enter another one",
                            "Group Chat", JOptionPane.ERROR_MESSAGE);

                    phoneNumberOfUserToAdd = JOptionPane.showInputDialog(null,
                            "Please enter the phone number of the user you want to add again.\nThen click OK",
                            "Group Chat",
                            JOptionPane.DEFAULT_OPTION);
                }

                tempUser = DataBase.returnUserByPhoneNumber(phoneNumberOfUserToAdd);

                if (DataBase.isAdmin(currentUser, groupToAdd) == true) {
                    // Create a function -----> find the group!
                    DataBase.groupChats.get(DataBase.returnIndexOfGroup(groupToAdd)).users.add(tempUser);
                }

                else {
                    JOptionPane.showMessageDialog(null,
                            "You are not the admin of the group!\nAsk admin(s) to promote you to admin and then add the user you want!",
                            "Group Chat", JOptionPane.ERROR_MESSAGE);
                }

                break;

            default:
                break;
        }

        System.out.println("-------------------------------------------");

        DataBase.showMemberOfGroup(groupToAdd);

        System.out.println("-------------------------------------------");

    }

    public static void kickMemberGroup(User currentUser, String groupId) {

        String userToKick;
        JOptionPane.showMessageDialog(null,
                "You are going to kick a member from the group\nPlease click OK to continue",
                "Group Chat", JOptionPane.DEFAULT_OPTION);

        userToKick = JOptionPane.showInputDialog(null,
                "Please enter the user id of the user you want to kick.\nThen click OK", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        while (DataBase.isFoundUserId(userToKick) == false) {
            JOptionPane.showMessageDialog(null,
                    "The user id you just entered is not in the system\nclick OK and the enter another one",
                    "Group Chat", JOptionPane.ERROR_MESSAGE);

            userToKick = JOptionPane.showInputDialog(null,
                    "Please enter the user id of the user you want to kick again.\nThen click OK", "Group Chat",
                    JOptionPane.DEFAULT_OPTION);
        }

        User tempUser = DataBase.returnUserByUserId(userToKick);

        // ------------------------------------------------------------------------------------

        if (DataBase.isAdmin(currentUser, groupId) == true) {
            // Create a function -----> find the group!
            DataBase.groupChats.get(DataBase.returnIndexOfGroup(groupId)).users.remove(tempUser);

        }

        else {
            JOptionPane.showMessageDialog(null,
                    "You are not the admin of the group!\nAsk admin(s) to promote you to admin and then add the user you want!",
                    "Group Chat", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void addAdminGroup(User currentUser, String groupId) {

        String userToPromote;

        DataBase.showAdminsOfGroup(groupId);

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Choose one:\n1.Add by userId\n2.Add by phone number", "Group Chat", JOptionPane.DEFAULT_OPTION));

        switch (userInput) {
            case 1:
                userToPromote = JOptionPane.showInputDialog(null,
                        "Please enter the user id of the user you want to promote.\nThen click OK", "Group Chat",
                        JOptionPane.DEFAULT_OPTION);

                while (DataBase.isFoundUserId(userToPromote) == false) {
                    JOptionPane.showMessageDialog(null,
                            "The user id you just entered is not in the system\nclick OK and the enter another one",
                            "Group Chat", JOptionPane.ERROR_MESSAGE);

                    userToPromote = JOptionPane.showInputDialog(null,
                            "Please enter the user id of the user you want to promote again.\nThen click OK",
                            "Group Chat",
                            JOptionPane.DEFAULT_OPTION);
                }

                User tempUser = DataBase.returnUserByUserId(userToPromote);

                if (DataBase.isAdmin(currentUser, groupId) == true) {
                    // Create a function -----> find the group!
                    DataBase.groupChats.get(DataBase.returnIndexOfGroup(groupId)).admins.add(tempUser);
                }

                else {
                    JOptionPane.showMessageDialog(null,
                            "You are not the admin of the group!\nAsk admin(s) to promote you to admin and then add the user you want!",
                            "Group Chat", JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 2:

                String phoneNumberOfUserToAdd = JOptionPane.showInputDialog(null,
                        "Please enter the phone number of the user you want to promote.\nThen click OK", "Group Chat",
                        JOptionPane.DEFAULT_OPTION);

                while (DataBase.isFoundByPhoneNumber(phoneNumberOfUserToAdd) == false) {
                    JOptionPane.showMessageDialog(null,
                            "The phone number you just entered is not in the system\nclick OK and the enter another one",
                            "Group Chat", JOptionPane.ERROR_MESSAGE);

                    phoneNumberOfUserToAdd = JOptionPane.showInputDialog(null,
                            "Please enter the phone number of the user you want to add again.\nThen click OK",
                            "Group Chat",
                            JOptionPane.DEFAULT_OPTION);
                }

                tempUser = DataBase.returnUserByPhoneNumber(phoneNumberOfUserToAdd);

                if (DataBase.isAdmin(currentUser, groupId) == true) {
                    // Create a function -----> find the group!
                    DataBase.groupChats.get(DataBase.returnIndexOfGroup(groupId)).admins.add(tempUser);
                }

                else {
                    JOptionPane.showMessageDialog(null,
                            "You are not the admin of the group!\nAsk admin(s) to promote you to admin and then add the user you want!",
                            "Group Chat", JOptionPane.ERROR_MESSAGE);
                }

                break;

            default:
                break;
        }

        System.out.println("-------------------------------------------");

        DataBase.showAdminsOfGroup(groupId);

        System.out.println("-------------------------------------------");

    }

    public static void deleteMessageGroup(User currentUser, String groupId) {

        String userId;
        String caption;
        // String channelId;

        JOptionPane.showMessageDialog(null,
                "You are going to delete a message in the group.\nPlease click OK to continue", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        userId = JOptionPane.showInputDialog(null, "Please enter the user id of the sender of the message",
                "Group Chat", JOptionPane.DEFAULT_OPTION);

        while (DataBase.isFoundUserId(userId) == false) {
            JOptionPane.showMessageDialog(null,
                    "The user id you just entered is not in the system\nclick OK and the enter another one",
                    "Group Chat", JOptionPane.ERROR_MESSAGE);

            userId = JOptionPane.showInputDialog(null,
                    "Please enter the user id of the user again.\nThen click OK", "Group Chat",
                    JOptionPane.DEFAULT_OPTION);
        }

        caption = JOptionPane.showInputDialog(null, "Please enter the caption of the message", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        if (DataBase.groupChats.get(DataBase.returnIndexOfGroup(groupId)).admins.contains(currentUser)) {

            DataBase.deleteMessageGroup(groupId, caption, userId);

        }

        else {
            JOptionPane.showMessageDialog(null,
                    "You are not an admin please ask admin(s) to promote you to admin\nOr\nthe message is not found",
                    "Channel", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("===========");

        DataBase.showMessagesInGroup(groupId);

        // DataBase.showMessagesInGroup.(DataBase.returnIndexOfGroup(groupId));
        System.out.println("===========");

    }

    public static void deleteEntireGroup(User currentUser, String groupId) {

        String groupChatName;

        JOptionPane.showMessageDialog(null,
                "You are going to delete the entire group including the messages.\nPlease click OK to continue",
                "Group Chat", JOptionPane.DEFAULT_OPTION);

        groupChatName = JOptionPane.showInputDialog(null, "Please enter the name of the group", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        DataBase.showAllTheGroups();
        System.out.println("----------------------------------------------");

        if (DataBase.isAdmin(currentUser, groupId)) {
            DataBase.groupChats.remove(DataBase.returnIndexOfGroup(groupChatName));
        }

        else {
            JOptionPane.showMessageDialog(null,
                    "You are not an admin in the group please ask admin(s) to promote you to admin", "Group Chat",
                    JOptionPane.ERROR_MESSAGE);
        }

        DataBase.showAllTheGroups();
        System.out.println("----------------------------------------------");

    }

    public static void leaveGroup(User currentUser, String groupId) {

        DataBase.showAdminsOfGroup(groupId);

        DataBase.showMemberOfGroup(groupId);

        JOptionPane.showMessageDialog(null, "You are going to leave the group\nClick OK to leave", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        if (DataBase.channels.get(DataBase.returnIndexOfGroup(groupId)).admins.contains(currentUser) == true) {

            DataBase.channels.get(DataBase.returnIndexOfGroup(groupId)).admins.remove(currentUser);
        }

        if (DataBase.channels.get(DataBase.returnIndexOfGroup(groupId)).users.contains(currentUser) == true) {

            DataBase.channels.get(DataBase.returnIndexOfGroup(groupId)).users.remove(currentUser);
        }

        System.out.println("----------------------------------------------------------");

        DataBase.showAdminsOfGroup(groupId);

        DataBase.showMemberOfGroup(groupId);

    }

    public static void sendMessageGroup(User currentUser, String groupId) {

        String caption;

        JOptionPane.showMessageDialog(null,
                "You are going to send message in this group.\nPlease click Okay to continue", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        caption = JOptionPane.showInputDialog(null, "Please enter the text of your message", "Group Chat",
                JOptionPane.DEFAULT_OPTION);

        Message message = new Message(currentUser, caption, null, null,
                DataBase.channels.get(DataBase.returnIndexOfGroup(groupId)));

        if (DataBase.isAdmin(currentUser, groupId)) {

            DataBase.groupChats.get(DataBase.returnIndexOfGroup(groupId)).messages.add(message);

        } else {

            JOptionPane.showMessageDialog(null,
                    "You are not an admin in this group!\nPlease ask admin(s) to promote you to admin", "Group Chat",
                    JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("===========");
        DataBase.showMessagesInGroup(groupId);
        System.out.println("===========");

    }

    // ================================CHANEL=====================================

    public static void channel(User currentUser) {

        JOptionPane.showMessageDialog(null, "This is Channel Chat page\nClick OK to continue", "Channel",
                JOptionPane.DEFAULT_OPTION);

        int userInput = Integer
                .parseInt(JOptionPane.showInputDialog(null, "1.Go to channels\n2.Start new channel",
                        "Channel", JOptionPane.DEFAULT_OPTION));

        switch (userInput) {

            case 1:
                continueAnExistingChannel(currentUser);
                break;

            case 2:
                startNewChannelChat(currentUser);
                break;

            default:
                break;
        }

    }

    public static void startNewChannelChat(User currentUser) {

        String name;
        String bio;
        String channelId;

        JOptionPane.showMessageDialog(null, "You are going to create new channel.\nPlease click OK to continue",
                "Channel", JOptionPane.DEFAULT_OPTION);

        name = JOptionPane.showInputDialog(null, "Please enter the name of the channel you want to create", "Channel",
                JOptionPane.DEFAULT_OPTION);

        bio = JOptionPane.showInputDialog(null, "Please enter the bio of the channel you want to create", "Channel",
                JOptionPane.DEFAULT_OPTION);

        JOptionPane.showMessageDialog(null,
                "This is the tricky part!\nThe channel id must be unique.\nPlease click OK to continue", "Channel",
                JOptionPane.DEFAULT_OPTION);

        channelId = JOptionPane.showInputDialog(null, "Please enter the channel id of the channel you want to create",
                "Channel", JOptionPane.DEFAULT_OPTION);

        while (DataBase.isUniqueChannelId(channelId) == false) {

            JOptionPane.showMessageDialog(null,
                    "The channel id you just entered is used by another channel!\nPlease click OK and then enter another channel id",
                    "Channel", JOptionPane.ERROR_MESSAGE);

            channelId = JOptionPane.showInputDialog(null,
                    "Please enter the channel id of the channel you want to create again", "Channel",
                    JOptionPane.DEFAULT_OPTION);

        }

        Channel channel = new Channel(name, bio, channelId);

        channel.admins.add(currentUser); // Add the user we are logged in with to the Admins

        DataBase.channels.add(channel);

        DataBase.returnUserByUserId(currentUser.userId).channels.add(channel);

        DataBase.showAdminsOfChannel(channelId);

    }

    public static void continueAnExistingChannel(User currentUser) {

        String channelId;

        JOptionPane.showMessageDialog(null,
                "You are going to continue an existing channel\nPlease click OK to continue",
                "Channel", JOptionPane.DEFAULT_OPTION);

        channelId = JOptionPane.showInputDialog(null, "Please enter the channel id of the channel and then click OK",
                "Channel", JOptionPane.DEFAULT_OPTION);

        while (DataBase.isFoundChannel(channelId) == false) {

            JOptionPane.showMessageDialog(null,
                    "The channel id is not found!\nPlease click OK and then enter another channel id",
                    "Channel", JOptionPane.ERROR_MESSAGE);

            channelId = JOptionPane.showInputDialog(null, "Please enter the channel id of the group and then click OK",
                    "Channel", JOptionPane.DEFAULT_OPTION);
        }

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Choose one:\n1.Add new admin\n2.Add new viewer\n3.Send message\n4.Delete message\n5.Delete the entire channel\n6.Leave channel\n7.show messages\n8.like message",
                "Channel", JOptionPane.DEFAULT_OPTION));

        switch (userInput) {

            case 1:
                addAdminChannel(currentUser, channelId);
                break;

            case 2:
                addViewerChannel(currentUser, channelId);
                break;

            case 3:
                sendMessage(currentUser, channelId);
                break;

            case 4:
                deleteMessageChannel(currentUser, channelId);
                break;

            case 5:
                deleteEntireChannel(currentUser, channelId);
                break;

            case 6:
                leaveChannel(currentUser, channelId);
                break;

            case 7:
                DataBase.showMessagesInChannel(channelId);

            case 8:
                likeMessage(currentUser, channelId);

            default:
                break;
        }

    }

    public static void addAdminChannel(User currentUser, String channelId) {

        String adminIdToAdd;

        DataBase.showAdminsOfChannel(channelId);

        JOptionPane.showMessageDialog(null, "You are going to add a admin to the channel.\nPlease click OK to continue",
                "Channel", JOptionPane.DEFAULT_OPTION);

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Choose one:\n1.add by user id\n2.add by phone number", "Channel", JOptionPane.DEFAULT_OPTION));

        switch (userInput) {
            case 1:

                adminIdToAdd = JOptionPane.showInputDialog(null,
                        "Please enter the user id of the user you want to add to admins.\nThen click OK to continue",
                        "Channel",
                        JOptionPane.DEFAULT_OPTION);

                while (DataBase.isFoundUserId(adminIdToAdd) == false) {

                    JOptionPane.showMessageDialog(null,
                            "The user id is not defined in the system!\nPlease click OK and enter another user id",
                            "Channel",
                            JOptionPane.ERROR_MESSAGE);

                    adminIdToAdd = JOptionPane.showInputDialog(null,
                            "Please enter the user id of the user you want to add to admins again.\nThen click OK to continue",
                            "Channel", JOptionPane.DEFAULT_OPTION);
                }

                DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).admins
                        .add(DataBase.findUserByUserId(adminIdToAdd));

                break;

            case 2:

                String phoneNumberOfUserToPromote = JOptionPane.showInputDialog(null,
                        "Please enter the phone number of the user you want to add to admins.\nThen click OK to continue",
                        "Channel",
                        JOptionPane.DEFAULT_OPTION);

                while (DataBase.isFoundByPhoneNumber(phoneNumberOfUserToPromote) == false) {

                    JOptionPane.showMessageDialog(null,
                            "The phone number is not defined in the system!\nPlease click OK and enter another user id",
                            "Channel",
                            JOptionPane.ERROR_MESSAGE);

                    phoneNumberOfUserToPromote = JOptionPane.showInputDialog(null,
                            "Please enter the phone number of the user you want to add to admins again.\nThen click OK to continue",
                            "Channel", JOptionPane.DEFAULT_OPTION);
                }

                DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).admins
                        .add(DataBase.returnUserByPhoneNumber(phoneNumberOfUserToPromote));

                break;

            default:
                break;
        }

        System.out.println("--------------------------------------------");

        DataBase.showAdminsOfChannel(channelId);

    }

    public static void addViewerChannel(User currentUser, String channelId) {

        String viewerIdToAdd;

        DataBase.showMemberOfChannel(channelId);

        JOptionPane.showMessageDialog(null, "You are going to add a viewer to the channel", "Channel",
                JOptionPane.DEFAULT_OPTION);

        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Choose one:\n1.add by user id\n2.add by phone number", "Channel", JOptionPane.DEFAULT_OPTION));

        switch (userInput) {
            case 1:

                viewerIdToAdd = JOptionPane.showInputDialog(null,
                        "Please enter the user id of the user you want to add to channel.\nThen click OK to continue",
                        "Channel",
                        JOptionPane.DEFAULT_OPTION);

                while (DataBase.isFoundUserId(viewerIdToAdd) == false) {

                    JOptionPane.showMessageDialog(null,
                            "The user id is not defined in the system!\nPlease click OK and enter another user id",
                            "Channel",
                            JOptionPane.ERROR_MESSAGE);

                    viewerIdToAdd = JOptionPane.showInputDialog(null,
                            "Please enter the user id of the user you want to add to channel again.\nThen click OK to continue",
                            "Channel", JOptionPane.DEFAULT_OPTION);
                }

                if (DataBase.isAdminInChannel(currentUser, channelId)) {
                    DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).users
                            .add(DataBase.findUserByUserId(viewerIdToAdd));
                } else {
                    JOptionPane.showMessageDialog(null,
                            "You are not an admin in this channel!\nPlease ask admin(s) to promote you to admin",
                            "Channel",
                            JOptionPane.ERROR_MESSAGE);
                }

                break;

            case 2:

                String phoneNumberOfUserToPromote = JOptionPane.showInputDialog(null,
                        "Please enter the phone number of the user you want to add to admins.\nThen click OK to continue",
                        "Channel",
                        JOptionPane.DEFAULT_OPTION);

                while (DataBase.isFoundByPhoneNumber(phoneNumberOfUserToPromote) == false) {

                    JOptionPane.showMessageDialog(null,
                            "The phone number is not defined in the system!\nPlease click OK and enter another user id",
                            "Channel",
                            JOptionPane.ERROR_MESSAGE);

                    phoneNumberOfUserToPromote = JOptionPane.showInputDialog(null,
                            "Please enter the phone number of the user you want to add to admins again.\nThen click OK to continue",
                            "Channel", JOptionPane.DEFAULT_OPTION);
                }

                if (DataBase.isAdminInChannel(currentUser, channelId)) {
                    DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).users
                            .add(DataBase.returnUserByPhoneNumber(phoneNumberOfUserToPromote));
                } else {
                    JOptionPane.showMessageDialog(null,
                            "You are not an admin in this channel!\nPlease ask admin(s) to promote you to admin",
                            "Channel",
                            JOptionPane.ERROR_MESSAGE);
                }

                System.out.println("-------------------------------------------------");

                DataBase.showMemberOfChannel(channelId);
        }
    }

    public static void sendMessage(User currentUser, String channelId) {

        String caption;

        JOptionPane.showMessageDialog(null,
                "You are going to send message in this channel.\nPlease click Okay to continue", "Channel",
                JOptionPane.DEFAULT_OPTION);

        caption = JOptionPane.showInputDialog(null, "Please enter the text of your message", "Channel",
                JOptionPane.DEFAULT_OPTION);

        Message message = new Message(currentUser, caption, null, null,
                DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)));

        if (DataBase.isAdminInChannel(currentUser, channelId)) {

            DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).messages.add(message);

        } else {

            JOptionPane.showMessageDialog(null,
                    "You are not an admin in this channel!\nPlease ask admin(s) to promote you to admin", "Channel",
                    JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("===========");
        DataBase.showMessagesInChannel(DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)));
        System.out.println("===========");
    }

    public static void deleteMessageChannel(User currentUser, String channelId) {

        String userId;
        String caption;
        // String channelId;

        JOptionPane.showMessageDialog(null,
                "You are going to delete a message in the channel.\nPlease click OK to continue", "Channel",
                JOptionPane.DEFAULT_OPTION);

        userId = JOptionPane.showInputDialog(null, "Please enter the user id of the sender of the message",
                "Channel", JOptionPane.DEFAULT_OPTION);

        while (DataBase.isFoundUserId(userId) == false) {
            JOptionPane.showMessageDialog(null,
                    "The user id you just entered is not in the system\nclick OK and the enter another one",
                    "Channel", JOptionPane.ERROR_MESSAGE);

            userId = JOptionPane.showInputDialog(null,
                    "Please enter the user id of the user again.\nThen click OK", "Channel",
                    JOptionPane.DEFAULT_OPTION);
        }

        caption = JOptionPane.showInputDialog(null, "Please enter the caption of the message", "Channel",
                JOptionPane.DEFAULT_OPTION);

        if (DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).admins.contains(currentUser)) {

            DataBase.deleteMessageChannel(channelId, caption, userId);

        }

        else {
            JOptionPane.showMessageDialog(null,
                    "You are not an admin please ask admin(s) to promote you to admin\nOr\nthe message is not found",
                    "Channel", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("===========");
        DataBase.showMessagesInChannel(DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)));
        System.out.println("===========");

    }

    public static void deleteEntireChannel(User currentUser, String channelId) {

        JOptionPane.showMessageDialog(null,
                "You are going to delete the entire channel including messages!\nclick OK to continue", "Channel",
                JOptionPane.DEFAULT_OPTION);

        DataBase.showAllChannels();

        System.out.println("----------------------------------");

        if (DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).admins.contains(currentUser)) {

            DataBase.channels.remove(DataBase.returnIndexOfChannel(channelId));

        }

        else {
            JOptionPane.showMessageDialog(null, "You are not an admin please ask admin(s) to promote you to admin",
                    "Channel", JOptionPane.ERROR_MESSAGE);
        }

        DataBase.showAllChannels();

        System.out.println("----------------------------------");

    }

    public static void leaveChannel(User currentUser, String channelId) {

        DataBase.showAdminsOfChannel(channelId);

        DataBase.showMemberOfChannel(channelId);

        JOptionPane.showMessageDialog(null, "You are going to leave the channel\nClick OK to leave", "Channel",
                JOptionPane.DEFAULT_OPTION);

        if (DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).admins.contains(currentUser) == true) {

            System.out.println("salam");

            DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).admins.remove(currentUser);
        }

        if (DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).users.contains(currentUser) == true) {

            System.out.println("khobie");

            DataBase.channels.get(DataBase.returnIndexOfChannel(channelId)).users.remove(currentUser);
        }

        System.out.println("----------------------------------");

        DataBase.showAdminsOfChannel(channelId);

        DataBase.showMemberOfChannel(channelId);

        // currentUser.channels.remove(DataBase.returnIndexOfChannel(channelId));
    }

    public static void likeMessage(User currentUser, String channelId) {

        JOptionPane.showMessageDialog(null, "You are going to like a message\nclick OK to continue", "Channel",
                JOptionPane.DEFAULT_OPTION);

        String captionOfMessage = JOptionPane.showInputDialog(null,
                "Please enter the caption of message you are going to like", "Channel", JOptionPane.DEFAULT_OPTION);

        String userId = JOptionPane.showInputDialog(null, "Please enter the user id of the sender of the message",
                "Channel", JOptionPane.DEFAULT_OPTION);

        DataBase.likeMessage(currentUser, channelId, userId, captionOfMessage);

    }

    // =============================User Page=============================
    public static void userPage(User currentUser) {
        int userInput = Integer.parseInt(JOptionPane.showInputDialog(null,
                "chose from the menu below:\n1.change user id\n2.change phone number\n3.change password\n4.DELETE account ",
                "User Page",
                JOptionPane.DEFAULT_OPTION));

        switch (userInput) {
            case 1:
                changeUserId(currentUser);
                break;

            case 2:
                changePhoneNumber(currentUser);
                break;

            case 3:
                changePassword(currentUser);
                break;

            case 4:
                deleteAccount(currentUser);

            default:
                break;
        }

    }

    public static void changeUserId(User currentUser) {

        DataBase.showProfile(currentUser);

        String inputUserId;
        // String inputPassword;

        JOptionPane.showMessageDialog(null, "You are going to change your User Id \nPlease touch OK to continue",
                "Change User Id", JOptionPane.DEFAULT_OPTION);

        inputUserId = JOptionPane.showInputDialog(null, "Please enter you new user id", "Change User Id",
                JOptionPane.DEFAULT_OPTION);

        if (DataBase.isUniqueUserName(inputUserId) == false) {
            while (DataBase.isUniqueUserName(inputUserId) == false) {
                JOptionPane.showMessageDialog(null, "The user id is taken by another user!",
                        "Change User Id", JOptionPane.ERROR_MESSAGE);
                inputUserId = JOptionPane.showInputDialog(null, "Please enter your new user id", "Change User Id",
                        JOptionPane.DEFAULT_OPTION);
            }
        }

        DataBase.userIdChanger(currentUser.userId, inputUserId);

        System.out.println("----------------------------------");

        DataBase.showProfile(currentUser);

        System.out.println("----------------------------------");

    }

    public static void changePhoneNumber(User currentUser) {

        String inputPhoneNumber;

        DataBase.showProfile(currentUser);

        JOptionPane.showMessageDialog(null, "You are going to change your phone number \nPlease touch OK to continue",
                "Change Phone Number", JOptionPane.DEFAULT_OPTION);

        inputPhoneNumber = JOptionPane.showInputDialog(null, "Please enter your new phone number",
                "Change Phone Number",
                JOptionPane.DEFAULT_OPTION);

        DataBase.phoneNUmberChanger(currentUser.phoneNumber, inputPhoneNumber);

        System.out.println("----------------------------------");

        DataBase.showProfile(currentUser);

        System.out.println("----------------------------------");

    }

    public static void changePassword(User currentUser) {

        String inputPassword;

        DataBase.showProfile(currentUser);

        JOptionPane.showMessageDialog(null, "You are going to change your password\nPlease touch OK to continue",
                "Change Password", JOptionPane.DEFAULT_OPTION);

        inputPassword = JOptionPane.showInputDialog(null, "Please enter your new password", "Change Password",
                JOptionPane.DEFAULT_OPTION);

        DataBase.passwordChanger(currentUser.password, inputPassword);

        System.out.println(currentUser.getPassword());
        System.out.println(DataBase.users.get(0).getPassword());

        System.out.println("----------------------------------");

        DataBase.showProfile(currentUser);

        System.out.println("----------------------------------");

    }

    public static void deleteAccount(User currentUser) {

        JOptionPane.showMessageDialog(null, "You are going to DELETE your account\nPlease touch OK to continue",
                "Change Password", JOptionPane.DEFAULT_OPTION);

        JOptionPane.showMessageDialog(null, "Are you sure? \nclick OK to Delete your account forever!",
                "Change Password", JOptionPane.DEFAULT_OPTION);

        DataBase.showAllUsers();

        System.out.println("-----------------------------------");

        DataBase.userDelete(currentUser.userId);

        DataBase.showAllUsers();

        System.out.println("-----------------------------------");
    }

    // ----------------------------find the chat of those 2
    // people------------------------------------------

    public static void putTextIntoPrivateChat(User currentUser, User user2, Message message) {

        for (PrivateChat privateChat : currentUser.getPrivateChats()) {
            if (user2.equals(privateChat.user2)) {
                privateChat.messages.add(message);
            }
        }

    }

    // ======================================Search Page==========================

    public static void searchPage(User currentUser) {
        // The search page is handled in all 3 part of the Program
        // 1.in Private chat if you want to start private chat with another person you
        // can

        String groupId;

        JOptionPane.showMessageDialog(null, "This is the search page.\nClick OK to continue", "Search Page",
                JOptionPane.DEFAULT_OPTION);

        groupId = JOptionPane.showInputDialog(null, "Please enter the id of the group you want to join", "Search Page",
                JOptionPane.DEFAULT_OPTION);

        while (DataBase.isFoundGroup(groupId) == false) {

            JOptionPane.showMessageDialog(null, "The group id is not defined in the system", "Search Page",
                    JOptionPane.ERROR_MESSAGE);

            groupId = JOptionPane.showInputDialog(null, "Please enter the id of the group you want to join again",
                    "Search Page", JOptionPane.DEFAULT_OPTION);
        }

        DataBase.showMemberOfGroup(groupId);

        if (currentUser.getAge() >= DataBase.groupChats.get(DataBase.returnIndexOfGroup(groupId)).ageRestriction) {

            DataBase.groupChats.get(DataBase.returnIndexOfGroup(groupId)).users.add(currentUser);

        } else {

            JOptionPane.showMessageDialog(null, "Your age is not enough to join the group", "Search Page",
                    JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("------------------------------------------------");

        DataBase.showMemberOfGroup(groupId);

    }

}