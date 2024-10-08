import DAOJungleSpeed.DAO;
import DAOJungleSpeed.DAOException;
import DAOJungleSpeed.JungleSpeedDAOFactory;
import DAOJungleSpeed.JungleSpeed_JPA_DAOFactory;
import donnees.Joueur;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/infojoueur"})
public class InfoJoueur extends HttpServlet {
    
    protected int compteur = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        response.setContentType("text/html;charset=UTF-8");
    
        PrintWriter out = response.getWriter();
        String pseudo = request.getParameter("pseudo");
        System.out.println(pseudo);
        try {
            JungleSpeedDAOFactory factory = new JungleSpeed_JPA_DAOFactory();
            DAO<Joueur> daoJoueurs = factory.getDAOJoueur();
            Joueur joueur = daoJoueurs.find(pseudo);
            String info_joueurs = joueur.toString();
            String[] infos = info_joueurs.split("/n");
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + pseudo + "</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + pseudo +"</h1>");
            out.println("<table>");
            
            for (String info : infos){
                out.println("<td><tr>" + info + "</tr></td>");
            }
            
            
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close(); 
        }
    }

    // Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(InfoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Handle POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(InfoJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
