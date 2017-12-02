package tanvir.busmanagementsystem.MOdelClass;

/**
 * Created by USER on 06-Nov-17.
 */

public class SeatInfoMC {

    private String seatInfoPK;
    private int schedule_id;
    private String  seatNumber;
    private String  customerName;
    private String  customerMobileNumber;
    private String  customerEmail;
    private String  seatCancellableToken;

    public SeatInfoMC(String seatInfoPK, int schedule_id, String seatNumber, String customerName, String customerMobileNumber, String customerEmail, String seatCancellableToken) {
        this.seatInfoPK = seatInfoPK;
        this.schedule_id = schedule_id;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        this.customerMobileNumber = customerMobileNumber;
        this.customerEmail = customerEmail;
        this.seatCancellableToken = seatCancellableToken;
    }

    public SeatInfoMC() {

    }

    public SeatInfoMC( int schedule_id, String seatNumber, String customerName, String customerMobileNumber, String customerEmail, String seatCancellableToken) {
        this.seatInfoPK = seatInfoPK;
        this.schedule_id = schedule_id;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        this.customerMobileNumber = customerMobileNumber;
        this.customerEmail = customerEmail;
        this.seatCancellableToken = seatCancellableToken;
    }

    public String getSeatInfoPK() {
        return seatInfoPK;
    }

    public void setSeatInfoPK(String seatInfoPK) {
        this.seatInfoPK = seatInfoPK;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public void setCustomerMobileNumber(String customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSeatCancellableToken() {
        return seatCancellableToken;
    }

    public void setSeatCancellableToken(String seatCancellableToken) {
        this.seatCancellableToken = seatCancellableToken;
    }
}

