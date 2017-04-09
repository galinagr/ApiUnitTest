package domParser;

public class Dom {

    private String lastName;
    private String firstName;
    private String street;
    private String city;
    private String state;
    private String zip;

    @Override
    public String toString() {
        return "Dom{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dom dom = (Dom) o;

        if (!lastName.equals(dom.lastName)) return false;
        if (!firstName.equals(dom.firstName)) return false;
        if (!street.equals(dom.street)) return false;
        if (!city.equals(dom.city)) return false;
        if (!state.equals(dom.state)) return false;
        return zip.equals(dom.zip);

    }

    @Override
    public int hashCode() {
        int result = lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zip.hashCode();
        return result;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Dom() {
        this.lastName = lastName;
        this.firstName = firstName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

}
