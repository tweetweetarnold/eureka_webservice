package services;

import net.aksingh.owmjapis.AbstractWeather.Weather;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import org.json.JSONException;

public class WeatherService {
	private Weather weather;
	
	public WeatherService() throws JSONException {
		OpenWeatherMap openWeatherMap = new OpenWeatherMap("78577dc02b9f967590f41cc1ce43a795");

		CurrentWeather currentWeather = openWeatherMap.currentWeatherByCityCode(1880252);
		String cityName = currentWeather.getCityName();
		String rawResponse = currentWeather.getRawResponse();
		weather = currentWeather.getWeatherInstance(0);
		currentWeather.getWeatherInstance(0).getWeatherCode();
	}

	public Weather getWeather() {
		return weather;
	}

	
	
	
	
	
}
