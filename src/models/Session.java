package models;

public class Session {
    private static String loggedInAdmin;
    private static int loggedInAdminId;

    public static String getLoggedInAdmin() {
        return loggedInAdmin;
    }

    public static void setLoggedInAdmin(String admin) {
        loggedInAdmin = admin;
    }

    public static int getLoggedInAdminId() {
        return loggedInAdminId;
    }

    public static void setLoggedInAdminId(int adminId) {
        loggedInAdminId = adminId;
    }
}
