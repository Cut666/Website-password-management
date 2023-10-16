package model;

public class AccountWebsite {
    private String url;
    private String userName;
    private String passWord;

    public AccountWebsite(String url, String userName, String passWord) {
        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "AccountWebsite{" +
                "url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
