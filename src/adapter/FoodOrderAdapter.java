package adapter;

import java.lang.reflect.Type;

import model.FoodOrder;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class FoodOrderAdapter implements JsonSerializer<FoodOrder> {
	
	@Override
	public JsonElement serialize(FoodOrder order, Type arg1, JsonSerializationContext arg2) {
		// TODO Auto-generated method stub
		System.out.println("running json serialize");
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", order.getStatus());
		jsonObject.addProperty("admin", order.getAdmin().toString());
		jsonObject.addProperty("employee", order.getEmployee().toString());
		jsonObject.addProperty("createDate", order.getCreateDate().toString());
		jsonObject.addProperty("foodOrderId", order.getFoodOrderId());
		jsonObject.addProperty("foodOrderList", order.getFoodOrderList().toString());
		return jsonObject;
	}

}
