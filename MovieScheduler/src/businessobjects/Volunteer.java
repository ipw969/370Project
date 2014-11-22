package businessobjects;

/**
 * volunteer data class
 *
 * @author john mason
 */
public class Volunteer extends BaseBusinessObject {

    String firstName;
    String lastName;
    String email;
    String phone;   //since we are not actually doing anything with it having it as a string will get rid of many errors
    BusinessObjectList<TimeInterval> availability = new BusinessObjectList<>();

    //empty constructor
    public Volunteer() {
        setIsNew(true);
    }

    //general constructor
    public Volunteer(String fName, String lName, String email, String phone,
            BusinessObjectList<TimeInterval> avail) {
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phone = phone;
        this.availability = avail;
        setIsNew(true);
    }

    //general constructor
    public Volunteer(String fName, String lName, String email, String phone) {
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.phone = phone;
        setIsNew(true);
    }

    public void addAvailability(TimeInterval avail) {
        this.availability.add(avail);
        setHasChanged(true);
    }

    private void setAvailability(BusinessObjectList<TimeInterval> availabilityList) {
        this.availability = availabilityList;
    }

    public void setFirstName(String name) {
        this.firstName = name;
        setHasChanged(true);
    }

    public void setLastName(String name) {
        this.lastName = name;
        setHasChanged(true);
    }

    public void setEmail(String email) {
        this.email = email;
        setHasChanged(true);
    }

    public void setPhone(String phone) {
        this.phone = phone;
        setHasChanged(true);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public BusinessObjectList<TimeInterval> getAvailability() {
        return availability;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public String toDescriptiveString() {
        StringBuilder newString = new StringBuilder();
        newString.append("First name: " + this.firstName + "\n");
        newString.append("Last name: " + this.lastName + "\n");
        newString.append("Phone number: " + this.phone + "\n");
        newString.append("Email address: " + this.email + "\n");

        return newString.toString();
    }

    @Override
    public BaseBusinessObject clone() throws CloneNotSupportedException {
        Volunteer cloneVolunteer = (Volunteer) super.clone();
        cloneVolunteer.setFirstName(this.getFirstName());
        cloneVolunteer.setLastName(this.getLastName());
        cloneVolunteer.setEmail(this.getEmail());
        cloneVolunteer.setPhone(this.getPhone());

        cloneVolunteer.setAvailability(new BusinessObjectList<>());
        for (TimeInterval currentAvailability : getAvailability()) {
            cloneVolunteer.addAvailability(currentAvailability);
        }
        cloneVolunteer.setHasChanged(false);
        return cloneVolunteer;
    }

    @Override
    public void merge(BaseBusinessObject mergeObject) {
        if (mergeObject == null) {
            throw new RuntimeException("The given mergeObject was null for the volunteer merge.");
        } else if (!(mergeObject instanceof Scene)) {
            throw new RuntimeException("The given mergeObject is not an instance of volunteer for the volunteer merge");
        }

        Volunteer mergeVolunteer = (Volunteer) mergeObject;

        this.setFirstName(mergeVolunteer.getFirstName());
        this.setLastName(mergeVolunteer.getLastName());
        this.setEmail(mergeVolunteer.getEmail());
        this.setPhone(mergeVolunteer.getPhone());
    }
}
