package org.pipeline;

public class UserEventV0 {
    private String timestamp;
//    private String userAgent;
    private String colorName;
    private String userName;

    public UserEventV0(String timestamp,/*String userAgent,*/ String colorName, String userName) {
        this.timestamp = timestamp;
//        this.userAgent = userAgent;
        this.colorName = colorName;
        this.userName = userName;
    }
}
