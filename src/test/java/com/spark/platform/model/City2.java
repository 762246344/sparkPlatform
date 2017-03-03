

package com.spark.platform.model;

/**
 * mybatis 3.3.1新功能测试使用
 *
 * @author zhaoguoxian
 * @since 2016-01-22 22:16
 */
public class City2 {
    private Integer id;

    private String cityName;

    private String cityState;

    public City2() {
    }

    public City2(String cityName, String cityState) {
        this.cityName = cityName;
        this.cityState = cityState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityState() {
        return cityState;
    }

    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    @Override
    public String toString() {
        return "City2{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", cityState='" + cityState + '\'' +
                '}';
    }
}
