package servlets;

import model.JDBCHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Александр on 19.09.2017.
 */
@WebServlet(name = "UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {

    private final JDBCHelper MYSQL_HELPER = JDBCHelper.getInstanceJdbc();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deletedID = request.getParameter("id");
        try(Statement statement = MYSQL_HELPER.getStatement()){

        statement.executeUpdate("DELETE FROM usersdb.users WHERE id=" + deletedID );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/view");
        dispatcher.forward(request, response);
    }
}
