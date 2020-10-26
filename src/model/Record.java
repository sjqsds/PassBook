package model;

public class Record {
    private String name;
    private String account;
    private String passwd;

    @Override
    public String toString() {
        return "**" + name +
               "**" + account +
                "**" + passwd +
                "**";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getPasswd() {
        return passwd;
    }
}
