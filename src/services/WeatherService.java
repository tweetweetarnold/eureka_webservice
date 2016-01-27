package services;

import net.aksingh.owmjapis.AbstractWeather.Weather;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

import org.json.JSONException;

public class WeatherService {
	private Weather weather;

	public WeatherService() {
		OpenWeatherMap openWeatherMap = new OpenWeatherMap("78577dc02b9f967590f41cc1ce43a795");
		CurrentWeather currentWeather;
		try {
			currentWeather = openWeatherMap.currentWeatherByCityCode(1880252);
			String cityName = currentWeather.getCityName();
			String rawResponse = currentWeather.getRawResponse();
			weather = currentWeather.getWeatherInstance(0);
			currentWeather.getWeatherInstance(0).getWeatherCode();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Weather getWeather() {
		return weather;
	}

}
