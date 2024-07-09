import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ConsoleWeatherApp {

    private static String city;
    private static String country;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter city name: ");
        city = scanner.nextLine();

        System.out.print("Enter country: ");
        country = scanner.nextLine();

        updateWeatherData();
        scheduleWeatherUpdates();
    }

    private static void updateWeatherData() {
        try {
            String jsonData = WeatherFetcher.getWeatherData(city, country);
            WeatherInfo weatherInfo = WeatherParser.parse(jsonData);

            System.out.println("Temperature: " + weatherInfo.getTemperature() + "°C");
            System.out.println("Humidity: " + weatherInfo.getHumidity() + "%");
            System.out.println("Description: " + weatherInfo.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void scheduleWeatherUpdates() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateWeatherData();
            }
        }, 0, 900000); // 15 minutes in milliseconds
    }
}
