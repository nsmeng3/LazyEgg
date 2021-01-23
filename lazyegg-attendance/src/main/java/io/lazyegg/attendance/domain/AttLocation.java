package io.lazyegg.attendance.domain;

/**
 * @author DifferentW  nsmeng3@163.com 2021/1/18 11:30 下午
 */


public class AttLocation {

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 打卡地址
     */
    private String presentAddress;

    public Double getLatitude() {
        return latitude;
    }

    public AttLocation setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public AttLocation setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public AttLocation setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
        return this;
    }
}
