package ren.vic.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherEntity {

    @SerializedName("HeWeather6")
    private List<WeatherContent> root;

    public boolean isSuccess() {
        return root.size() > 0 && "ok".equals(root.get(0).status);
    }

    public WeatherBasic getWeatherBasic() {
        return root.size() > 0 ? root.get(0).basic : null;
    }

    public WeatherNow getWeatherNow() {
        return root.size() > 0 ? root.get(0).now : null;
    }

    private static class WeatherContent {

        @SerializedName("status")
        private String status;

        @SerializedName("basic")
        private WeatherBasic basic;

        @SerializedName("now")
        private WeatherNow now;
    }

    public static class WeatherBasic {

        @SerializedName("location")
        public String location;
    }

    public static class WeatherNow {

        @SerializedName("cond_txt")
        public String condTxt;
    }
}
