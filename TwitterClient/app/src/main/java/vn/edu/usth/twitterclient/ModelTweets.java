package vn.edu.usth.twitterclient;

public class ModelTweets {
    String uName, userName, pDescription, pTime, like_status, uid;

    public ModelTweets(){
    }

    public ModelTweets(String uName, String userName, String pDescription, String pTime, String like_status, String uid) {
        this.uName = uName;
        this.userName = userName;
        this.pDescription = pDescription;
        this.pTime = pTime;
        this.like_status = like_status;
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getpDescription() {
        return pDescription;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }

    public String getLike_status() {
        return like_status;
    }

    public void setLike_status(String like_status) {
        this.like_status = like_status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
