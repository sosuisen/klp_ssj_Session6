import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/message", "/clear" })
public class MessageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();

		if (session.getAttribute("history") == null) {
			// セッションスコープにArrayListが保存されていなければ保存
			session.setAttribute("history", new ArrayList<String>());
		}

		// 発展課題 4a
		// ArrayListのクリア
		var path = req.getServletPath();
		if (path.equals("/clear")) {
			var list = (ArrayList<String>) session.getAttribute("history");
			list.clear();
		}

		req.getRequestDispatcher("/WEB-INF/message.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String message = req.getParameter("message");

		HttpSession session = req.getSession();
		ArrayList<String> list = (ArrayList<String>) session.getAttribute("history");
		list.add(message);

		req.getRequestDispatcher("/WEB-INF/message.jsp").forward(req, res);
	}
}