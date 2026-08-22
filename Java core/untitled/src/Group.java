import java.util.Date;

public class Group {
    int groupId;
    String groupName;
    Date createDate;
    int creatorId;

    public Group(int groupId, String groupName, Date createDate, int creatorId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.createDate = createDate;
        this.creatorId = creatorId;
    }
}
