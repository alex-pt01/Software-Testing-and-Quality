package airquality.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirPollutionForecast {
    @SerializedName("pollutionType")
    @Expose
    private String pollutionType;
    @SerializedName("avg")
    @Expose
    private double avg;
    @SerializedName("min")
    @Expose
    private double min;
    @SerializedName("max")
    @Expose
    private double max;

    public String getPollutionType() {
        return pollutionType;
    }

    public AirPollutionForecast(String pollutionType, double avg, double min, double max) {
        this.pollutionType = pollutionType;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public AirPollutionForecast() {

    }

    public void setPollutionType(String pollutionType) {
        this.pollutionType = pollutionType;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }


}
