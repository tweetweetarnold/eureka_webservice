package servlet.load.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.OrderPeriodController;
import model.OrderPeriod;

/**
 * Servlet implementation class LoadAdminEditOrderPeriodServlet
 */
@WebServlet("/LoadAdminEditOrderPeriodServlet")
public class LoadAdminEditOrderPeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadAdminEditOrderPeriodServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		OrderPeriodController orderPeriodCtrl = new OrderPeriodController();

		try {
			String orderPeriodIdString = request.getParameter("periodId");
			int orderPeriodId = Integer.parseInt(orderPeriodIdString);
			OrderPeriod period = orderPeriodCtrl.getOrderPeriod(orderPeriodId);

			session.setAttribute("period", period);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());

		}

		response.sendRedirect("/eureka_webservice/admin/orderperiod/edit.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
