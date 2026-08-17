import temperature.Converter;

public class TemperatureMain {
    public static void main(String[] args) {
        double c = 25;

        System.out.println("Celsius to Fahrenheit: "
                + Converter.celsiusToFahrenheit(c));
        System.out.println("Celsius to Kelvin: "
                + Converter.celsiusToKelvin(c));
        System.out.println("Fahrenheit to Celsius: "
                + Converter.fahrenheitToCelsius(77));
        System.out.println("Kelvin to Celsius: "
                + Converter.kelvinToCelsius(298.15));
    }
}
