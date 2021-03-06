package servlets_frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class aceptar_solicitud
 */
@WebServlet("/aceptar_solicitud")
public class aceptar_solicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public aceptar_solicitud() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String) request.getSession().getAttribute("id_user");
		String amigo = request.getParameter("amigo");
		
		Rck_conn con = new Rck_conn();
		JSONObject obj = con.connect("Amigos?user=" + user + "&user_d=" + amigo + "&f=a");
		boolean result = obj.getBoolean("result");
		if(!result)
		{
			request.getSession().setAttribute("error", "Error al aceptar la solicitud");
		}
		response.sendRedirect("cargarPerfil");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
