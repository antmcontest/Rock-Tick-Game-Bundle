/** Method was created to
 *  more easily access
 *  and manipulate user data
 *
 *  Anthony Contestabile
 */

public class Users {
    private String username;
    private String password;
    private int numCoins;
    private rewardStatus darkTheme;
    private rewardStatus lightTheme;
    private rewardStatus USATheme;

    Users(String username, String password, int numCoins, rewardStatus darkTheme, rewardStatus lightTheme, rewardStatus USATheme){
        this.username = username;
        this.password = password;
        this.numCoins = numCoins;
        this.darkTheme = darkTheme;
        this.lightTheme = lightTheme;
        this.USATheme = USATheme;
    }


    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    int getNumCoins() {
        return numCoins;
    }

    void setNumCoins(int numCoins) {
        this.numCoins = numCoins;
    }

    rewardStatus isDarkThemeUnlocked() {
        return darkTheme;
    }

    void setDarkThemeUnlocked(rewardStatus darkTheme) {
        this.darkTheme = darkTheme;
    }

    rewardStatus isLightThemeUnlocked() {
        return lightTheme;
    }

    void setLightThemeUnlocked(rewardStatus lightTheme) {
        this.lightTheme = lightTheme;
    }

    rewardStatus isUSAThemeUnlocked() {
        return USATheme;
    }

    void setUSAThemeUnlocked(rewardStatus USATheme) {
        this.USATheme = USATheme;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return username + "," + password + "," + numCoins + "," + darkTheme
                + "," + lightTheme + "," + USATheme;
    }

    public void printUser(){
        System.out.println("Username: " + username + "\nPassword: " + password
                + "\nNumber of coins: " + numCoins);
        System.out.println();
    }
}
