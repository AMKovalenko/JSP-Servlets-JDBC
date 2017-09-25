package servlets;

import model.JDBCHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Александр on 19.09.2017.
 */
@WebServlet("/UserCreateServlet")
public class UserCreateServlet extends HttpServlet {

    private final JDBCHelper MYSQL_HELPER = JDBCHelper.getInstanceJdbc();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        StringBuilder values = new StringBuilder();
        values.append("'").append(request.getParameter("login")).append("'").append(",");
        values.append("'").append(request.getParameter("password")).append("'").append(",");
        values.append("'").append(request.getParameter("firstName")).append("'").append(",");
        values.append("'").append(request.getParameter("lastName")).append("'").append(",");
        values.append("'").append(request.getParameter("email")).append("'");

        String insertNewUser = "INSERT INTO users (login, password, firstname, lastname, email) VALUES (" + values + ")";

        try (Connection connection = MYSQL_HELPER.getConnection();
             Statement statement = connection.createStatement()){

             statement.executeUpdate(insertNewUser);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(String.format("%s%s", request.getContextPath(), "/views/user/CreateUser.jsp"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(String.format("%s%s", request.getContextPath(), "/views/user/CreateUser.jsp"));
    }
}
