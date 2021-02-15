package automation.api.stepdefs;

import automation.apidefinition.DailyForecast;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class SurferStepDefs {
    @Steps
    DailyForecast dailyForecast;

    static final Logger LOGGER = LoggerFactory.getLogger(SurferStepDefs.class);

    HashMap<String, ArrayList<String>> surfingPrefs = new HashMap<>();
    List<String> datesForSurfing = new ArrayList<>();

    @Given("I like to surf in any of {int} beaches of Sydney, out of the following")
    public void setBeaches(int count, List<Map<String, String>> beaches) {
        Serenity.setSessionVariable("count").to(count);
        Serenity.setSessionVariable("beaches").to(beaches);
    }

    @And("I only like to surf on following days of the week in next {int} days")
    public void setSurfDays(int noOfDays, @Transpose List<String> days) {
        Serenity.setSessionVariable("noOfDays").to(noOfDays);
        Serenity.setSessionVariable("days").to(days);
        getDateListForSurfing();
    }

    @When("I look up the weather forecast with POSTAL CODES")
    public void getWeatherForecast() {
        List<Map<String, String>> beaches = Serenity.sessionVariableCalled("beaches");
        HashMap<String, ResponseBody> weatherForecasts = new HashMap<>();
        for (int i = 0; i < beaches.size(); i++) {
            Response response = dailyForecast.getDailyForecast(
                    Serenity.sessionVariableCalled("key"),
                    Serenity.sessionVariableCalled("noOfDays"),
                    beaches.get(i).get("PostCode")
            );
            weatherForecasts.put(beaches.get(i).get("Beach"), response.getBody());
        }
        Serenity.setSessionVariable("weatherForecasts").to(weatherForecasts);
    }

    private void getDateListForSurfing() {
        List<String> days = Serenity.sessionVariableCalled("days");
        int noOfDays = Serenity.sessionVariableCalled("noOfDays");


        LocalDate today = LocalDate.now();

        for (int i = 0; i < noOfDays; i++) {
            LocalDate curDate = today.plusDays(i);
            if (days.contains(curDate.getDayOfWeek().name())) {
                datesForSurfing.add(curDate.toString());
            }
        }
    }

    @And("I pick spots where temperature is between {float}°C and {float}°C")
    public void checkTemperatureAndPickSpots(float minTemp, float maxTemp) {
        Serenity.setSessionVariable("minTemp").to(minTemp);
        Serenity.setSessionVariable("maxTemp").to(maxTemp);
        HashMap<String, ResponseBody> weatherForecasts = Serenity.sessionVariableCalled("weatherForecasts");

        for (String date : datesForSurfing) {
            ArrayList<String> eligibleBeaches = new ArrayList<>();
            for (String beach : weatherForecasts.keySet()) {
                float temp = Float.parseFloat(weatherForecasts.get(beach).jsonPath()
                        .get("data.find{it.datetime == '" + date + "'}.temp").toString());
                if (temp > minTemp && temp < maxTemp) {
                    eligibleBeaches.add(beach);
                }
            }
            surfingPrefs.put(date, eligibleBeaches);
        }
    }

    @And("I pick spots where wind speed is between {float} and {float}")
    public void checkWindSpeedAndPickSpots(float minSpeed, float maxSpeed) {
        Serenity.setSessionVariable("minSpeed").to(minSpeed);
        Serenity.setSessionVariable("maxSpeed").to(maxSpeed);
        HashMap<String, ResponseBody> weatherForecasts = Serenity.sessionVariableCalled("weatherForecasts");

        for (String date : datesForSurfing) {
            ArrayList<String> eligibleBeaches = surfingPrefs.get(date);
            for (Iterator<String> it = eligibleBeaches.iterator(); it.hasNext(); ) {
                float windSpeed = Float.parseFloat(weatherForecasts.get(it.next()).jsonPath()
                        .get("data.find{it.datetime == '" + date + "'}.wind_spd").toString());
                if (!(windSpeed > minSpeed && windSpeed < maxSpeed)) {
                    it.remove();
                }
            }
            surfingPrefs.replace(date, eligibleBeaches);
        }
    }

    @And("I pick spots where UV index is less than or equal to {float}")
    public void checkUvIndexAndPickSpots(float maxUvIndex) {
        Serenity.setSessionVariable("maxUvIndex").to(maxUvIndex);
        HashMap<String, ResponseBody> weatherForecasts = Serenity.sessionVariableCalled("weatherForecasts");

        for (String date : datesForSurfing) {
            ArrayList<String> eligibleBeaches = surfingPrefs.get(date);
            for (Iterator<String> it = eligibleBeaches.iterator(); it.hasNext(); ) {
                float uvIndex = Float.parseFloat(weatherForecasts.get(it.next()).jsonPath()
                        .get("data.find{it.datetime == '" + date + "'}.uv").toString());
                if (uvIndex > maxUvIndex) {
                    it.remove();
                }
            }
            surfingPrefs.replace(date, eligibleBeaches);
        }
    }

    @Then("I pick best suitable spot out of top two spots, based upon suitable weather " +
            "forecast for the day and display it co-ordinates")
    public void pickSuitableSpot() {
        int count = Serenity.sessionVariableCalled("count");
        HashMap<String, ResponseBody> weatherForecasts = Serenity.sessionVariableCalled("weatherForecasts");
        List<Map<String, String>> beaches = Serenity.sessionVariableCalled("beaches");
        for (String date : datesForSurfing) {
            LOGGER.info("Top " + count + " Beaches for Date = " + date);
            ArrayList<String> eligibleBeaches = surfingPrefs.get(date);
            int top = 0;
            for (int i = 0; i < beaches.size(); i++) {
                if (eligibleBeaches.contains(beaches.get(i).get("Beach")) && top < count) {
                    top = top + 1;
                    if (top == 1) {
                        LOGGER.info(String.format("Date = %s; Top Beach %d is %s", date, top, beaches.get(i)));
                        LOGGER.info(String.format("Lat = %s, Lon = %s",
                                weatherForecasts.get(beaches.get(i).get("Beach")).jsonPath().get("lat"),
                                weatherForecasts.get(beaches.get(i).get("Beach")).jsonPath().get("lon")));

                        float temp = Float.parseFloat(weatherForecasts.get(beaches.get(i).get("Beach")).jsonPath()
                                .get("data.find{it.datetime == '" + date + "'}.temp").toString());
                        float minTemp = Float.parseFloat(Serenity.sessionVariableCalled("minTemp").toString());
                        float maxTemp = Float.parseFloat(Serenity.sessionVariableCalled("maxTemp").toString());

                        assertThat("Temperature is between set value",
                                temp, is(both(greaterThan(minTemp)).and(lessThan(maxTemp))));

                        float windSpeed = Float.parseFloat(weatherForecasts.get(beaches.get(i).get("Beach")).jsonPath()
                                .get("data.find{it.datetime == '" + date + "'}.wind_spd").toString());
                        float minSpeed = Float.parseFloat(Serenity.sessionVariableCalled("minSpeed").toString());
                        float maxSpeed = Float.parseFloat(Serenity.sessionVariableCalled("maxSpeed").toString());

                        assertThat("Wind Speed is between set value", windSpeed,
                                is(both(greaterThan(minSpeed)).and(lessThan(maxSpeed))));

                        assertThat("UV is less than set value",
                                Float.parseFloat(weatherForecasts.get(beaches.get(i).get("Beach")).jsonPath()
                                        .get("data.find{it.datetime == '" + date + "'}.uv").toString()),
                                is(lessThan(Float.parseFloat(Serenity.sessionVariableCalled("maxUvIndex").toString()))));
                    } else {
                        LOGGER.info(String.format("Date = %s; Beach %d is %s", date, top, beaches.get(i)));
                    }
                }
            }
        }
    }

}
