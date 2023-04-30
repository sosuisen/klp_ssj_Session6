import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MessageBean;

@WebServlet(urlPatterns = { "/message", "/clear" })
public class MessageServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();

		if (session.getAttribute("history") == null) {
			// セッションスコープにArrayListが保存されていなければ保存
			session.setAttribute("history", new ArrayList<MessageBean>());
		}

		// 発展課題 4a
		// ArrayListのクリア
		var path = req.getServletPath();
		if (path.equals("/clear")) {
			var list = (ArrayList<MessageBean>) session.getAttribute("history");
			list.clear();
		}

		req.getRequestDispatcher("/WEB-INF/message.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		var mesBean = new MessageBean(req.getParameter("name"), req.getParameter("message"));
		var session = req.getSession();
		var list = (ArrayList<MessageBean>) session.getAttribute("history");
		list.add(mesBean);

		req.getRequestDispatcher("/WEB-INF/message.jsp").forward(req, res);
	}
}