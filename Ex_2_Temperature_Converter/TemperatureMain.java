/*
 * =====================================================================
 * EX.NO: 2
 * TITLE: IMPLEMENTATION OF PACKAGE FOR TEMPERATURE CONVERTER
 * FILE : TemperatureMain.java
 * =====================================================================
 */

import temperature.Converter;

public class TemperatureMain {

    public static void main(String[] args) {

        double c = 25;

        System.out.println("Celsius to Fahrenheit: " +
                Converter.celsiusToFahrenheit(c));

        System.out.println("Celsius to Kelvin: " +
                Converter.celsiusToKelvin(c));

        System.out.println("Fahrenheit to Celsius: " +
                Converter.fahrenheitToCelsius(77));

        System.out.println("Kelvin to Celsius: " +
                Converter.kelvinToCelsius(298.15));
    }
}

/*
OUTPUT:

Celsius to Fahrenheit: 77.0
Celsius to Kelvin: 298.15
Fahrenheit to Celsius: 25.0
Kelvin to Celsius: 25.0
*/
