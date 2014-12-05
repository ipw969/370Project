package businessobjects;

/**
 * volunteer data class stores the name, email, phone, and availabilities of a
 * volunteer
 *
 * @author John Mason, Iain Workman
 */
public class Volunteer extends Resource {

    // Constructors
    /**
     * Creates a new Empty Volunteer class which is not known to the Database
     */
    public Volunteer() {
        super();
        boolean isEmailValid = getEmail() != null && getEmail().length() > 0;
        updateError(EMAIL_CANNOT_BE_NULL, isEmailValid);

        boolean isPhoneValid = getPhoneNumber() != null && getPhoneNumber().length() > 0;
        updateError(PHONE_NUMBER_CANNOT_BE_NULL, isPhoneValid);

        boolean isFNameValid = getFirstName() != null && getFirstName().length() > 0;
        updateError(FIRST_NAME_CANNOT_BE_NULL, isFNameValid);

        boolean isLNameValid = getLastName() != null && getLastName().length() > 0;
        updateError(LAST_NAME_CANNOT_BE_NULL, isLNameValid);
    }

    /**
     * Convenience Constructor to create a new instance of a Volunteer from data
     * loaded from the Database
     *
     * @param fName::String ~ The first name of the Volunteer
     * @param lName::String ~ The last name of the Volunteer
     * @param email::String ~ The email address of the Volunteer
     * @param phone::String ~ The phone number of the Volunteer
     */
    public Volunteer(String fName, String lName, String email, String phone) {
        super(fName, lName, email, phone);

    }

    // Public Methods
    /**
     * Sets the email of the Volunteer
     *
     * @param email::String ~ The email address of the Volunteer
     */
    @Override
    public void setEmail(String email) {
        boolean isValid = email != null && email.length() > 0;
        updateError(EMAIL_CANNOT_BE_NULL, isValid);
        super.setEmail(email);
    }

    /**
     * Sets the phone number of the Volunteer
     *
     * @param phoneNumber::String ~ The phone number of the Volunteer
     */
    @Override
    public void setPhoneNumber(String phoneNumber) {
        boolean isValid = phoneNumber != null && phoneNumber.length() > 0;
        updateError(PHONE_NUMBER_CANNOT_BE_NULL, isValid);
        super.setPhoneNumber(phoneNumber);
    }

    /**
     * Sets the first name of the Volunteer
     *
     * @param firstName::String ~ The first name of the Volunteer
     */
    @Override
    public void setFirstName(String firstName) {
        boolean isValid = firstName != null && firstName.length() > 0;
        updateError(FIRST_NAME_CANNOT_BE_NULL, isValid);
        super.setFirstName(firstName);
    }

    /**
     * Sets the last name of the Volunteer
     *
     * @param lastName::String ~ The last name of the Volunteer
     */
    @Override
    public void setLastName(String lastName) {
        boolean isValid = lastName != null && lastName.length() > 0;
        updateError(LAST_NAME_CANNOT_BE_NULL, isValid);
        super.setLastName(lastName);
    }

    /**
     * Creates a deep clone of this Volunteer
     *
     * @return A new instance of a Volunteer which shares all the same
     * attributes as this one
     */
    @Override
    public BaseBusinessObject clone() {
        try {
            return (Volunteer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Base class of Volunteer does not"
                    + " support cloning.");
        }
    }

    /**
     * Merges the provided BusinessObject's attributes into this Volunteer,
     * provided that the provided BaseBusinessObject is of Volunteer or derived
     * type and is not null
     *
     * @param mergeObject::BaseBusinessObject ~ The BusinessObject whose
     * attributes are to be merged into this.
     * @precond mergeObject instanceof Volunteer mergeObject != null
     */
    @Override
    public void merge(BaseBusinessObject mergeObject) {
        super.merge(mergeObject);
    }

    // Private Member Variables
    // Error Message Strings associated with this class
    private final String EMAIL_CANNOT_BE_NULL
            = "Email address is a required field.";
    private final String PHONE_NUMBER_CANNOT_BE_NULL
            = "Phone number is a required field.";
    private final String FIRST_NAME_CANNOT_BE_NULL
            = "First name is a required field.";
    private final String LAST_NAME_CANNOT_BE_NULL
            = "Last name is a required field.";

}
