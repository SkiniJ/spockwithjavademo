package apps.biz.skini.entity;

/**
 * Created by skinibizapps on 5/11/16.
 */
public class Account {
    private long accountId;
    private String firstName;
    private String lastName;
    private int age;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != account.accountId) return false;
        if (age != account.age) return false;
        if (firstName != null ? !firstName.equals(account.firstName) : account.firstName != null) return false;
        return lastName != null ? lastName.equals(account.lastName) : account.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }
}
