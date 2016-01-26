package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Food;
import model.OrderWindow;
import net.aksingh.owmjapis.AbstractWeather.Weather;
import services.WeatherService;
import controller.FoodController;

/**
 * Servlet implementation class RetrieveWeatherRecommendationServlet
 */
@WebServlet("/RetrieveWeatherRecommendationServlet")
public class RetrieveWeatherRecommendationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveWeatherRecommendationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	
	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		System.out.println("*********** RegistrationServlet ***********");
		Weather weather;
		HttpSession session = request.getSession();
		try{
			OrderWindow orderWindow = (OrderWindow)session.getAttribute("orderWindow");
			WeatherService weatherService = new WeatherService();
			weather = weatherService.getWeather();
			FoodController foodController = new FoodController();
			Food recommendation = foodController.getFoodForWeather(weather, orderWindow);
			
		}catch(Exception e){
			
		}
		
	}
}
