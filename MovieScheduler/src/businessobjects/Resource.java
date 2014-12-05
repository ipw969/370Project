package businessobjects;

/**
 * A class representing a Resource that will be required to film a Scene; either
 * a Volunteer or a piece of Equipment
 *
 * @author Iain Workman
 */
public class Resource extends BaseBusinessObject
        implements BusinessObjectListListener {

    // Constructor
    /**
     * Default constructor for a Resource. Creates an empty resource with no
     * Availabilities
     */
    public Resource() {
        firstName = new String();
        lastName = new String();
        email = new String();
        phoneNumber = new String();
        availabilities = new BusinessObjectList<>();
    }

    /**
     * Convenience constructor for a Resource which has been loaded from the
     * database.
     *
     * @param firstName::String ~ The first name to be associated with the
     * Resource
     * @param lastName::String ~ The last name to be associated with the
     * Resource
     * @param email::String ~ The email contact to be associated with the
     * Resource
     * @param phoneNumber::String ~ The contact phone number to be associated
     * with the Resource
     */
    public Resource(
            String firstName,
            String lastName,
            String email,
            String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        availabilities = new BusinessObjectList<>();
        setIsNew(false);
    }

    // Public Methods
    /**
     * The first name associated with the Resource
     *
     * @return The first name associated with the Resource
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name associated with the Resource
     *
     * @param firstName::String ~ The new first name to associate with the
     * Resource
     */
    public void setFirstName(String firstName) {
        if(firstName.compareTo(this.firstName) == 0)
            return;
        
        boolean isValid = firstName.length() <= 60;
        updateError(FIRST_NAME_TOO_LONG, isValid);
        this.firstName = firstName;
        setHasChanged(true);
    }

    /**
     * The last name associated with the Resource
     *
     * @return The last name associated with the Resource
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name associated with the Resource
     *
     * @param lastName::String ~ The new last name to associate with the
     * Resource
     */
    public void setLastName(String lastName) {
        if(lastName.compareTo(this.lastName) == 0)
            return;
        
        boolean isValid = lastName.length() <= 60;
        updateError(LAST_NAME_TOO_LONG, isValid);
        this.lastName = lastName;
        setHasChanged(true);
    }

    /**
     * The email contact associated with the Resource
     *
     * @return The email contact associated with the Resource
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email contact associated with the Resource
     *
     * @param email::String ~ The new email contact to associate with the
     * Resource
     */
    public void setEmail(String email) {
        if(email.compareTo(this.email)==0)
            return;
        
        boolean isValid = email.length() <= 60;
        updateError(EMAIL_TOO_LONG, isValid);
        this.email = email;
        setHasChanged(true);
    }

    /**
     * The contact phone number associated with the Resource
     *
     * @return The contact phone number associated with the Resource
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the contact phone number associated with the Resource
     *
     * @param phoneNumber::String ~ The new contact phone number to associate
     * with the Resource
     */
    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.compareTo(this.phoneNumber) == 0)
            return;
            
        boolean isValid = phoneNumber.length() <= 15;
        updateError(PHONE_NUMBER_TOO_LONG, isValid);
        this.phoneNumber = phoneNumber;
        setHasChanged(true);
    }

    /**
     * The list of TimeIntervals over which the Resource is available.
     *
     * @return The list of TimeIntervals over which the Resource is available.
     */
    public BusinessObjectList<TimeInterval> getAvailabilities() {
        return availabilities;
    }

    /**
     * Sets the list of TimeIntervals over which the Resource is available.
     *
     * @param availabilities::BusinessObjectList<TimeInterval> ~ The list of
     * TimeIntervals over which the Resource is available.
     * @precond ~ availabilities != null
     */
    public void setAvailabilties(BusinessObjectList<TimeInterval> availabilities)
            throws IllegalArgumentException {
        if (availabilities == null) {
            throw new IllegalArgumentException("A resource cannot have a null"
                    + " set of availabilities provided.");
        }

        if (this.availabilities != null) {
            this.availabilities.removeListener(this);
        }

        this.availabilities = availabilities;
        this.availabilities.addListener(this);
    }

    /**
     * The firstName and lastName associated with the Resource.
     *
     * @return The firstName and lastName associated with the Resource.
     */
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * A longer descriptive String describing the Resource. Includes a
     * formatted: First name: firstName Last name: lastName Phone number:
     * phoneNumber emailAddress: emailAddress
     *
     * @return A longer descriptive String describing the Resource.
     */
    @Override
    public String toDescriptiveString() {

        StringBuilder newString = new StringBuilder();
        newString.append("First name: ").append(this.firstName).append("\n");
        newString.append("Last name: ").append(this.lastName).append("\n");
        newString.append("Phone number: ").append(this.phoneNumber).append("\n");
        newString.append("Email address: ").append(this.email).append("\n");

        return newString.toString();
    }

    /**
     * Method for merging the provided BusinessObject into this.
     *
     * @param mergeObject::BaseBusinessObject ~ The BusinessObject to merge into
     * this
     * @precond mergeObject instanceof Resource mergeObject != null
     */
    @Override
    public void merge(BaseBusinessObject mergeObject) {
        if (mergeObject == null) {
            throw new RuntimeException("Cannot merge a null object into a "
                    + "Resource");
        }

        if (!(mergeObject instanceof Resource)) {
            throw new RuntimeException("Cannot merge an object of non Resource"
                    + " type into a Resource");
        }

        Resource other = (Resource) mergeObject;

        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.email = other.email;
        this.phoneNumber = other.phoneNumber;

        this.availabilities.clear();

        for (TimeInterval currentOtherAvailability : other.availabilities) {
            this.availabilities.add(currentOtherAvailability);
        }

    }

    /**
     * Creates a deep clone of the Resource, including unique version of its
     * attributes and availabilities
     *
     * @return A deep clone of this Resource
     * @throws CloneNotSupportedException
     */
    @Override
    public BaseBusinessObject clone() throws CloneNotSupportedException {
        Resource other = (Resource) super.clone();

        other.firstName = this.firstName;
        other.lastName = this.lastName;
        other.email = this.email;
        other.phoneNumber = this.phoneNumber;
        other.availabilities = new BusinessObjectList<>();
        other.availabilities.addListener(other);
        for (TimeInterval currentAvailability : this.availabilities) {
            other.availabilities.add((TimeInterval)currentAvailability.clone());
        }
        other.setHasChanged(false);
        return other;
    }

    // Observer notifications from availabilities
    /**
     * Method to handle a notification that a TimeInterval has been added to the
     * list of Availabilities. 
     *
     * @param itemAdded
     */
    @Override
    public void businessObjectAdded(BaseBusinessObject itemAdded) {
        this.setHasChanged(true);
    }

    /**
     * Method to handle a notification that a TimeInterval has been removed from
     * the list of Availabilities.
     * @param itemRemoved
     */
    @Override
    public void businessObjectRemoved(BaseBusinessObject itemRemoved) {
        this.setHasChanged(true);
    }

    /**
     * Method to handle a notification that the list of availabilities for the
     * resource has been cleared.
     */
    @Override
    public void listCleared() {
        this.setHasChanged(true);
    }

    /**
     * Method to handle notification from the availability list that a contained
     * BusinessObject has had its valid state altered.
     * @param newState::boolean ~ The new valid state of the sender
     * @param sender::BaseBusinessObject ~ The BusinessObject sending the
     * notification.
     */
    @Override
    public void validStateAltered(boolean newState, BaseBusinessObject sender) {
        if (sender instanceof TimeInterval) {
            TimeInterval sendingTimeInterval = (TimeInterval) sender;
            updateError("Time interval " + sendingTimeInterval.toString()
                    + " is not valid", newState);
        }
    }

    /**
     * Method to handle notification from the availability list that a contained
     * BusinessObject has had its changed state altered.
     * @param newState::boolean ~ The new changed state of the sender
     * @param sender::BaseBusinessObject ~ The BusinessObject sending the
     * notification.
     */
    @Override
    public void changedStateAltered(boolean newState, BaseBusinessObject sender) {
        this.setHasChanged(true);
    }

    // Private Methods
    /// The first name associated with the Resource
    private String firstName;
    /// The last name associated with the Resource
    private String lastName;
    /// The email contact associated with the Resource
    private String email;
    /// The contact phone number associated with the Resource
    private String phoneNumber;
    /// The list of availabilities for the Resource
    private BusinessObjectList<TimeInterval> availabilities;

    // Error Message Strings associated with this class
    private final String EMAIL_TOO_LONG
            = "Email cannot be longer than 60 characters.";
    private final String FIRST_NAME_TOO_LONG
            = "First name cannot be longer than 60 characters.";
    private final String LAST_NAME_TOO_LONG
            = "Last name cannot be longer than 60 characters.";
    private final String PHONE_NUMBER_TOO_LONG
            = "Phone number cannot be longer than 15 characters.";
}
