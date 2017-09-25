package servlets;

import model.JDBCHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Александр on 20.09.2017.
 */
@WebServlet("/UserAuthorizationServlet")
public class UserAuthorizationServlet extends HttpServlet {

    private final JDBCHelper MYSQL_HELPER = JDBCHelper.getInstanceJdbc();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredLogin = request.getParameter("login");
        String enteredPassword =  request.getParameter("password");
        boolean isRegistered = false;

        try(Connection connection = MYSQL_HELPER.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT login, password FROM usersdb.users WHERE login='" + enteredLogin + "'")) {
            while (resultSet.next()) {
                if (resultSet.getString("login").equalsIgnoreCase(enteredLogin) && resultSet.getString("password").equalsIgnoreCase(enteredPassword)) {
                    isRegistered = true;
                    request.setAttribute("login", enteredLogin);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                }
            }
        }catch(SQLException e){
                e.printStackTrace();
            }
        if (!isRegistered) {
            response.sendRedirect(String.format("%s%s", request.getContextPath(), "/user/create"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(String.format("%s%s", request.getContextPath(), "/views/user/AuthorizationUser.jsp"));
    }
}
