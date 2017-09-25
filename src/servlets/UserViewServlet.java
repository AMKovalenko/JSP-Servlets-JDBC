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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 19.09.2017.
 */
@WebServlet("/UserViewServlet")
public class UserViewServlet extends HttpServlet {

    private final JDBCHelper MYSQL_HELPER = JDBCHelper.getInstanceJdbc();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<User> listUsers = new ArrayList<User>();
        try (Connection connection = MYSQL_HELPER.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM usersdb.users")){
            while (resultSet.next()){
                User user = new User(resultSet.getInt("id"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getString("firstname"),
                        resultSet.getString("lastname"), resultSet.getString("email"));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("users", listUsers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/user/UsersView.jsp");
        dispatcher.forward(request, response);
    }
}
