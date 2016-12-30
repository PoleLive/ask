package cn.lawliex.ask.data;

/**
 * Created by Terence on 2016/12/30.
 */

public class UserInfo {
    private int id;
    private String name;
    private String email;
    private String motto;
    private int followerCount;
    private int followeeCount;
    private int answerCount;
    private int questionCount;
    private int followQuestionCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getFolloweeCount() {
        return followeeCount;
    }

    public void setFolloweeCount(int followeeCount) {
        this.followeeCount = followeeCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getFollowQuestionCount() {
        return followQuestionCount;
    }

    public void setFollowQuestionCount(int followQuestionCount) {
        this.followQuestionCount = followQuestionCount;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
