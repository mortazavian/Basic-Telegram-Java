public class ChatRoomId {
    static int id = 0;

    public static int assignId() {
        id += 1;
        return id;
    }
}
