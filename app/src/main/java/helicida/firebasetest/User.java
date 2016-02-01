package helicida.firebasetest;

import com.firebase.client.Firebase;

/**
 * Created by 46465442z on 27/01/16.
 */
public class User {

    private int birthYear;
    private String fullName;

    public User() {

    }

    public User(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getFullName() {
        return fullName;
    }
}
