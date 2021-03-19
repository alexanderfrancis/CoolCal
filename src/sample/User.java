package sample;

public class User {

    int id;
    String firstName;
    String lastName;
    String userName;
    String email;
    String passwordHash;
    String registeredAt;
    String lastLogin;

    public User (int id, String firstName, String lastName, String userName, String email,
                 String passwordHash, String registeredAt, String lastLogin) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.registeredAt = registeredAt;
        this.lastLogin = lastLogin;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void printUser(){
        System.out.println("User ID                :      " + this.getId());
        System.out.println("Name                   :      " + this.getFirstName() + " " + this.getLastName());
        System.out.println("username               :      " + this.getUserName());
        System.out.println("Email                  :      " + this.getEmail());
        System.out.println("Registration Date      :      " + this.getRegisteredAt());
    }

    public static void main(String[] args) {
        User u = new User(1584568, "Paul", "Blart", "pblart",
                "segwayspeeddemon@fake.io", "49d93xsddEddk4874EDkdk",
                "March 15, 2021", "March 18, 2021");
        u.printUser();
    }
}
