package automation.apidefinition;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;

import static io.restassured.config.DecoderConfig.decoderConfig;

public class DailyForecast {

    public Response getDailyForecast(String key, int days, String postalCode) {
        SerenityRest.useRelaxedHTTPSValidation();
        SerenityRest.config().decoderConfig(decoderConfig().useNoWrapForInflateDecoding(true));

        return SerenityRest.given()
                .baseUri("https://api.weatherbit.io")
                .queryParam("key", "0ef6b51b93e04d5a8ba06ffa258ff118")
                .queryParam("days", days)
                .queryParam("country", "AU")
                .queryParam("postal_code", postalCode)
                .get("/v2.0/forecast/daily");
    }
}
