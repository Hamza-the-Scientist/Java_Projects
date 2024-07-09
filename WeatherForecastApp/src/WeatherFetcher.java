import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFetcher {

    private static final String API_KEY = "63bf3b96dcd1b2595955afe71083a070";

    public static String getWeatherData(String city, String country) throws Exception {
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=%s&units=metric", city, country, API_KEY);
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        return content.toString();
    }
}
