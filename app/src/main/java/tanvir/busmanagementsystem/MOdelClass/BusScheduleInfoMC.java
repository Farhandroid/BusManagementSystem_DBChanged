package tanvir.busmanagementsystem.MOdelClass;

/**
 * Created by USER on 01-Dec-17.
 */

public class BusScheduleInfoMC {

    private int busScheduleInfoPK;
    private String busID;
    private String busName;

    private String departureLocation;
    private String arrivalLocation;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivaleTime;

    public BusScheduleInfoMC() {

    }

    public BusScheduleInfoMC(int busScheduleInfoPK, String busID, String busName, String departureLocation, String arrivalLocation, String departureDate, String departureTime, String arrivalDate, String arrivaleTime) {
        this.busScheduleInfoPK = busScheduleInfoPK;
        this.busID = busID;
        this.busName = busName;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivaleTime = arrivaleTime;
    }

    public BusScheduleInfoMC(String busID, String busName, String departureLocation, String arrivalLocation, String departureDate, String departureTime, String arrivalDate, String arrivaleTime) {

        this.busName = busName;
        this.busID = busID;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivaleTime = arrivaleTime;
    }

    public int getBusScheduleInfoPK() {
        return busScheduleInfoPK;
    }

    public void setBusScheduleInfoPK(int busScheduleInfoPK) {
        this.busScheduleInfoPK = busScheduleInfoPK;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getArrivaleTime() {
        return arrivaleTime;
    }

    public void setArrivaleTime(String arrivaleTime) {
        this.arrivaleTime = arrivaleTime;
    }
}
