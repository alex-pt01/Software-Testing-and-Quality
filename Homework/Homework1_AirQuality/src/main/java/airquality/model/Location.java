package airquality.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;

public class Location {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("timezone")
    @Expose
    private String timezone;


    public Location() {
    }

    public Location(String name, double latitude, double longitude, String time, String timezone) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 && Double.compare(location.longitude, longitude) == 0 && Objects.equals(name, location.name) && Objects.equals(time, location.time) && Objects.equals(timezone, location.timezone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude, time, timezone);
    }


    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", time='" + time + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
