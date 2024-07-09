public class WeatherInfo {
    private double temperature;
    private int humidity;
    private String description;

    public WeatherInfo(double temperature, int humidity, String description) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getDescription() {
        return description;
    }
}
