package services;

import java.io.IOException;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

public class WeatherService {

	public WeatherService() throws JSONException {
		OpenWeatherMap openWeatherMap = new OpenWeatherMap("78577dc02b9f967590f41cc1ce43a795");

		CurrentWeather currentWeather = openWeatherMap.currentWeatherByCityCode(1880252);
		String cityName = currentWeather.getCityName();
		String rawResponse = currentWeather.getRawResponse();
		System.out.println(currentWeather.getWeatherInstance(0).getWeatherDescription());
		System.out.println("Current City : " + cityName + " The weather stuff : " + rawResponse);
	}
}
