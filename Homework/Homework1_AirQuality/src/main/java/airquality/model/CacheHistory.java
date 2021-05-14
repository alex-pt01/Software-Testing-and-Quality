package airquality.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CacheHistory {
    @SerializedName("hits")
    @Expose
    private int hits;
    @SerializedName("misses")
    @Expose
    private int misses;
    @SerializedName("requests")
    @Expose
    private int requests;
    @SerializedName("time")
    @Expose
    private String time;

    public CacheHistory(int hits, int misses, int requests, String time) {
        this.hits = hits;
        this.misses = misses;
        this.requests = requests;
        this.time = time;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getRequests() {
        return requests;
    }

    public String getTime() {
        return time;
    }
}
