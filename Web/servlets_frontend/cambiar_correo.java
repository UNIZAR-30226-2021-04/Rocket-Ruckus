package servlets_frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class cambiar_correo
 */
@WebServlet("/cambiar_correo")
public class cambiar_correo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cambiar_correo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String) request.getSession().getAttribute("id_user");
		String email = request.getParameter("email");
		
		if (email == null || !email.matches("^[a-zA-Z0-9@.]*$")) 
		{ 
			request.getSession().setAttribute("error", "Correo inválido");
		}
		else
		{
			Rck_conn con = new Rck_conn();
			JSONObject obj = con.connect("Perfil?id_user=" + user + "&email=" + email + "&f=e");
			boolean result = obj.getBoolean("result");
			if (result)
			{
				request.getSession().setAttribute("email", email);
			}
			else
			{
				request.getSession().setAttribute("error","No se ha podido realizar el cambio");
			}
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
