package servlets;

import model.JDBCHelper;
import model.User;

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
 * Created by Александр on 19.09.2017.
 */
@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {

    private final JDBCHelper MYSQL_HELPER = JDBCHelper.getInstanceJdbc();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection connection = MYSQL_HELPER.getConnection();
             Statement statement = connection.createStatement()){
        statement.executeUpdate("UPDATE usersdb.users SET " +
                            "login=" + "'" + request.getParameter("login") + "'" +
                            ",password=" + "'" + request.getParameter("password") + "'" +
                            ",firstname=" + "'" + request.getParameter("firstName") + "'" +
                            ", lastname=" + "'" + request.getParameter("lastName") + "'" +
                            ", email=" + "'" + request.getParameter("email") + "'" +
                            " WHERE id=" + Integer.valueOf(request.getParameter("id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect(String.format("%s%s", request.getContextPath(), "/user/view"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = MYSQL_HELPER.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM usersdb.users WHERE id=" + request.getParameter("id"))){
            resultSet.next();
            User editUser = new User(resultSet.getInt("id"), resultSet.getString("login"),
                    resultSet.getString("password"), resultSet.getString("firstname"),
                    resultSet.getString("lastname"), resultSet.getString("email"));
            request.setAttribute("user", editUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/EditUser.jsp");
        dispatcher.forward(request, response);
    }
}
