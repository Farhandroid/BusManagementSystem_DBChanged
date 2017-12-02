package tanvir.busmanagementsystem.MOdelClass;

import java.util.ArrayList;

/**
 * Created by USER on 06-Nov-17.
 */

public class BusINfoMC {

    private String busName;
    private String busID;
    private String busType;

    public BusINfoMC() {

    }

    public BusINfoMC(String busName, String busID) {
        this.busName = busName;
        this.busID = busID;
    }

    public BusINfoMC(String busType, String totalBusSeat, String busSeatPrice) {
        this.busType = busType;
        this.totalBusSeat = totalBusSeat;
        this.busSeatPrice = busSeatPrice;
    }

    private String totalBusSeat;
    private String busSeatPrice;

    public BusINfoMC(String busName, String busID, String busType, String totalBusSeat, String busSeatPrice) {
        this.busName = busName;
        this.busID = busID;
        this.busType = busType;
        this.totalBusSeat = totalBusSeat;
        this.busSeatPrice = busSeatPrice;
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

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getTotalBusSeat() {
        return totalBusSeat;
    }

    public void setTotalBusSeat(String totalBusSeat) {
        this.totalBusSeat = totalBusSeat;
    }

    public String getBusSeatPrice() {
        return busSeatPrice;
    }

    public void setBusSeatPrice(String busSeatPrice) {
        this.busSeatPrice = busSeatPrice;
    }
}
