package yashv.demo.dao;

public class UserDAOFactory {
    private static UserDAO userdao = null;

    public static UserDAO getInstance()
    {
        if (UserDAOFactory.userdao == null) {
            UserDAOFactory.userdao = new UserDAO();
        }

        return UserDAOFactory.userdao;
    }
}
