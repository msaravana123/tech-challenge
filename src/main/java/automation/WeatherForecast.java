package automation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class WeatherForecast {
    float lat;
    float lon;
    Weather[] data;

    public Weather[] getData() {
        return data;
    }

    public void setData(Weather[] data) {
        this.data = data;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public class Weather {
        Date datetime;
        float temp;
        float wind_gust_spd;
        float uv;
        float min_temp;
        float max_temp;

        public Date getDatetime() {
            return datetime;
        }

        public void setDatetime(Date datetime) {
            this.datetime = datetime;
        }

        public float getTemp() {
            return temp;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getWind_gust_spd() {
            return wind_gust_spd;
        }

        public void setWind_gust_spd(float wind_gust_spd) {
            this.wind_gust_spd = wind_gust_spd;
        }

        public float getUv() {
            return uv;
        }

        public void setUv(float uv) {
            this.uv = uv;
        }


        public float getMin_temp() {
            return min_temp;
        }

        public void setMin_temp(float min_temp) {
            this.min_temp = min_temp;
        }

        public float getMax_temp() {
            return max_temp;
        }

        public void setMax_temp(float max_temp) {
            this.max_temp = max_temp;
        }
    }

}
