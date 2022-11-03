import java.util.ArrayList;

import javax.print.DocFlavor.STRING;

public class DataBase {

    // All the users are stored here
    static ArrayList<User> users = new ArrayList<User>();

    // All the group chats are stored here
    static ArrayList<GroupChat> groupChats = new ArrayList<GroupChat>();

    // All the private chats are stored here
    static ArrayList<PrivateChat> privateChats = new ArrayList<PrivateChat>();

    // All the channels are stored here
    static ArrayList<Channel> channels = new ArrayList<Channel>();

    public static boolean isUniqueUserName(String userName) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userName))
                return false;
        }

        return true;

    }

    // Change the type to int
    public static int isValidToSignIn(String userId, String password) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId) && users.get(i).getPassword().equals(password))
                return i;
        }

        return -1;
    }

    public static boolean isFoundUserId(String userId) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId))
                return true;
        }

        return false;
    }

    public static boolean isMachPassword(String userId, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId) && users.get(i).getPassword().equals(password))
                return true;
        }

        return false;

    }

    public static void userIdChanger(String oldUserId, String newUserId) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(oldUserId)) {
                users.get(i).setUserId(newUserId);
            }
        }

    }

    public static void phoneNUmberChanger(String oldPhoneNumber, String newPhoneNumber) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPhoneNumber().equals(oldPhoneNumber)) {
                users.get(i).setPhoneNumber(newPhoneNumber);
            }
        }

    }

    public static void passwordChanger(String oldPassword, String newPassword) {

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getPassword().equals(oldPassword)) {
                users.get(i).setPassword(newPassword);
            }
        }

    }

    public static void userDelete(String userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId))
                users.remove(i);
        }
    }

    public static User findUserByUserId(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId))
                return user;
        }
        // this part is just for handling the HomeWork and we assume that the user is
        // son of human
        return users.get(0);
    }

    public static boolean isFoundGroup(String groupId) {
        for (GroupChat groupChat : groupChats) {
            if (groupChat.groupId.equals(groupId)) {
                return true;
            }
        }
        return false;
    }

    public static User returnUserByUserId(String userId) {

        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }

        return users.get(0);
    }

    // Check if the user is admin in the group chat!
    public static boolean isAdmin(User currentUser, String groupToAdd) {

        for (GroupChat groupChat : groupChats) {
            if (groupChat.admins.contains(currentUser) && groupChat.groupId.equals(groupToAdd)) {
                return true;
            }
        }

        return false;

    }

    public static int returnIndexOfGroup(String groupName) {
        for (int i = 0; i < groupChats.size(); i++) {
            if (groupChats.get(i).groupId.equals(groupName)) {
                return i;
            }
        }

        return 0;
    }

    public static boolean isInGroup(User user, String userId) {
        for (GroupChat groupChat : groupChats) {
            if (groupChat.users.contains(user)) {
                return true;
            }
        }

        return false;

    }

    public static boolean isUniqueChannelId(String channelId) {

        for (Channel channel : channels) {
            if (channel.channelId.equals(channelId)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isFoundChannel(String channelId) {

        for (Channel channel : channels) {
            if (channel.channelId.equals(channelId)) {
                return true;
            }
        }

        return false;
    }

    public static int returnIndexOfChannel(String channelId) {

        for (int i = 0; i < channels.size(); i++) {
            if (channels.get(i).channelId.equals(channelId)) {
                return i;
            }
        }

        // just added to handle the code
        return 0;
    }

    public static boolean isAdminInChannel(User currentUser, String channelId) {

        for (Channel channel : channels) {
            if (channel.admins.contains(currentUser) && channel.channelId.equals(channelId)) {
                return true;
            }
        }

        return false;

    }

    public static void showAdminsOfGroup(String groupId) {

        for (GroupChat groupChat : groupChats) {
            if (groupChat.groupId.equals(groupId)) {
                System.out.println("The admins of the group are:");
                for (User user : groupChat.admins) {
                    System.out.println(user.getUserId());
                }
            }
        }

    }

    public static void showMemberOfGroup(String groupId) {

        for (GroupChat groupChat : groupChats) {
            if (groupChat.groupId.equals(groupId)) {
                System.out.println("The users of the group are:");
                for (User user : groupChat.users) {
                    System.out.println(user.getUserId());
                }
            }
        }

    }

    public static void showAdminsOfChannel(String channelId) {

        for (Channel channel : channels) {
            if (channel.channelId.equals(channelId)) {
                System.out.println("The admins of the channel are:");
                for (User user : channel.admins) {
                    System.out.println(user.getUserId());
                }
            }
        }

    }

    public static void showMemberOfChannel(String channelId) {

        for (Channel channel : channels) {
            if (channel.channelId.equals(channelId)) {
                System.out.println("The viewers of the channel are:");
                for (User user : channel.users) {
                    System.out.println(user.getUserId());
                }
            }
        }

    }

    public static void showProfile(User loggedInUser) {

        for (User user : users) {
            if (user.equals(loggedInUser)) {
                System.out.println("The information of the user is:");
                System.out.println("The name is: " + user.getName());
                System.out.println("The gender is: " + user.getGender());
                System.out.println("The phone number is: " + user.getPhoneNumber());
                System.out.println("The user id is: " + user.getUserId());
                System.out.println("The password is: " + user.getPassword());

            }
        }

    }

    public static void showPeopleInPrivateChat(PrivateChat privateChatToCheck) {

        for (PrivateChat privateChat : privateChats) {
            if (privateChat.user1.equals(privateChatToCheck.user1)
                    && privateChat.user2.equals(privateChatToCheck.user2)) {
                System.out.println("The first person in the private chat is: " + privateChat.user1.getUserId());
                System.out.println("The second person in the private chat is: " + privateChat.user2.getUserId());
            }
        }

    }

    public static void showMessagesInPrivateChat(User user1, User user2) {

        System.out.println("===============================================");

        for (PrivateChat privateChat : privateChats) {
            if (privateChat.user1.equals(user1)
                    && privateChat.user2.equals(user2)) {
                for (Message message : privateChat.messages) {
                    System.out.println(message.sender.getName() + " : " + message.caption);
                }
            }
        }

    }

    public static int returnIndexOfPrivateChat(User user1, User user2) {

        for (int i = 0; i < privateChats.size(); i++) {
            if (privateChats.get(i).user1.equals(user1) && privateChats.get(i).user2.equals(user2)) {
                return i;
            }
        }
        return 0;
    }

    public static void showMessagesInChannel(Channel channelToCheck) {

        for (Channel channel : channels) {
            if (channel.equals(channelToCheck)) {
                for (Message message : channel.messages) {
                    System.out.println(message.sender.getName() + " : " + message.caption);
                }
            }
        }

    }

    public static void showAllTheGroups() {

        System.out.println("All the group chats are: ");

        for (GroupChat groupChat : groupChats) {
            System.out.println(groupChat.groupId);
        }
    }

    public static void showAllChannels() {

        System.out.println("All the channels are: ");

        for (Channel channel : channels) {
            System.out.println(channel.channelId);
        }

    }

    public static void showAllUsers() {

        System.out.println("All the users are:");

        for (User user : users) {

            System.out.println(user.getUserId());
        }
    }

    public static boolean isFoundByPhoneNumber(String phoneNumber) {

        for (User user : users) {

            if (user.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }

        return false;
    }

    public static User returnUserByPhoneNumber(String phoneNumber) {

        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return user;
            }
        }

        return null;

    }

    public static void showMessagesInChannel(String channelId) {
        for (Channel channel : channels) {
            if (channel.channelId.equals(channelId)) {
                for (Message message : channel.messages) {
                    System.out.println(message.sender.getUserId() + " : " + message.caption);
                }
            }
        }
    }

    public static void showMessagesInGroup(String groupId) {
        for (GroupChat groupChat : groupChats) {
            if (groupChat.groupId.equals(groupId)) {
                for (Message message : groupChat.messages) {
                    System.out.println(message.sender.getUserId() + " : " + message.caption);
                }
            }
        }
    }

    public static void deleteMessageChannel(String channelId, String caption, String senderId) {

        for (Channel channel : channels) {
            if (channel.channelId.equals(channelId)) {

                for (Message message : channel.messages) {
                    if (message.caption.equals(caption)) {
                        if (message.sender.getUserId().equals(senderId)) {
                            channel.messages.remove(message);
                        }
                    }
                }

            }
        }

    }

    public static void deleteMessageGroup(String groupId, String caption, String senderId) {

        for (GroupChat groupChat : groupChats) {
            if (groupChat.groupId.equals(groupId)) {

                for (Message message : groupChat.messages) {
                    if (message.caption.equals(caption)) {
                        if (message.sender.getUserId().equals(senderId)) {
                            groupChat.messages.remove(message);
                        }
                    }
                }

            }
        }

    }

    public static void likeMessage(User currentUser, String channelId, String userId, String captionOfMessage) {

        for (Channel channel : channels) {
            if (channel.channelId.equals(channel)) {
                for (Message message : channel.messages) {
                    if (message.caption.equals(captionOfMessage) && message.sender.getUserId().equals(userId)) {
                        channel.messages.get(channel.messages.indexOf(message)).likedBy.add(currentUser);
                    }
                }
            }
        }

    }

}
