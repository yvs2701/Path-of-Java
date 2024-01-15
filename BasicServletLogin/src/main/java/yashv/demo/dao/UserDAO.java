package yashv.demo.dao;

public class UserDAO {
    private final String adminUname;
    private final String adminPsswd;

    public UserDAO() {
        this.adminUname = "admin";
        this.adminPsswd = "admin";
    }

    public boolean check(String uname, String psswd) {
        return uname.equals(this.adminUname) && psswd.equals(this.adminPsswd);
    }
}
