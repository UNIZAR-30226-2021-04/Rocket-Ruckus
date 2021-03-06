package servlets_frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Permite al usuario loggearse. Si los datos son correctos, redirige al home. Si no lo son, muestra un error.
	 * 
	 * @param user :Nombre del usuario
	 * @param pass :Contraseña del usuario
	 * 
	 * @return id_user (Nombre del usuario) si el login tiene éxito
	 * 		   error (Mensaje de error) si el login falla
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		boolean todoenorden = true;
		if (user == null || !user.matches("^[a-zA-Z0-9]*$")) { todoenorden = false; }
		if (pass == null || !pass.matches("^[a-zA-Z0-9]*$")) { todoenorden = false; }
		if (todoenorden) 
		{
			JSONObject obj = new Rck_conn().connect("LoginUser?user=" + user + "&pass=" + pass);
			boolean result = obj.getBoolean("result");
			if (result)
			{
				int id_lobby = obj.getInt("id_lobby");
				int pstart = obj.getInt("pstart");
				int muerte = obj.getInt("muerte");
				user = user.toUpperCase();
				String email = obj.getString("email");
				String foto = obj.getString("image");
				request.getSession().setAttribute("id_user", user);
				request.getSession().setAttribute("email", email);
				request.getSession().setAttribute("foto", foto);
				request.setAttribute("id_lobby", id_lobby);
				request.setAttribute("pstart", pstart);
				request.setAttribute("muerte", muerte);
				request.getRequestDispatcher("buscar_partidas").forward(request,response);
			} 
			else 
			{
				request.setAttribute("error", "Datos erroneos");
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
		}
		else
		{
			request.setAttribute("error", "Solo se admiten caracteres alfanuméricos");
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
