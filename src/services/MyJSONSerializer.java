package services;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class MyJSONSerializer {

	public final static JsonSerializer<Date> dateSerialize = new JsonSerializer<Date>() {

		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			final long dateString = src.getTime();
			return new JsonPrimitive(dateString);
		}

	};

}
