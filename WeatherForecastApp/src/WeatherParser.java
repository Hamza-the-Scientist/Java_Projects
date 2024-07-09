import org.json.JSONObject;

public class WeatherParser {

    public static WeatherInfo parse(String jsonData) {
        JSONObject jsonObject = new JSONObject(jsonData);

        JSONObject main = jsonObject.getJSONObject("main");
        double temperature = main.getDouble("temp");
        int humidity = main.getInt("humidity");

        JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);
        String description = weather.getString("description");

        return new WeatherInfo(temperature, humidity, description);
    }
}
