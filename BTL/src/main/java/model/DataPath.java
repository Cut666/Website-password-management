package model;

public enum DataPath {
    ACCOUNT("src//main//java//data//Account.txt"),
    ACCOUNTWEBSITE("src//main//java//data//AccountWebsite.txt"),
    PRIVATEKEY("src//main//java//data//privateKey.rsa"),
    PUBLICKEY("src//main//java//data//publicKey.rsa");
    private String value;

    DataPath(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

//    public void setValue(String value) {
//        this.value = value;
//    }
}
