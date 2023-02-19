package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CheckGuessNumberServletTemp")
public class CheckGuessNumberServletTemp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
//		HttpSession session = request.getSession();
//		
//		// 產生五個隨機數字陣列，並將它們存儲在 session 中
//		int[] numbers = CreateNewCards.generateRandomArray();
//		session.setAttribute("numbers", numbers);
//		
//		// 將隨機數字陣列顯示在網頁上
//		request.setAttribute("numbers", numbers);
//		request.getRequestDispatcher("guess.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		// 取出 session 中存儲的隨機數字陣列
        int[] ThisRoundRandomArray = (int[]) session.getAttribute("numbers");
        
        
        
        
        
        
        
		
		if(ThisRoundRandomArray == null) {
		

		int guess1 = Integer.parseInt(request.getParameter("guess1"));
		int guess2 = Integer.parseInt(request.getParameter("guess2"));
		int guess3 = Integer.parseInt(request.getParameter("guess3"));
		int guess4 = Integer.parseInt(request.getParameter("guess4"));
		int guess5 = Integer.parseInt(request.getParameter("guess5"));

		
		// 產生五個隨機數字陣列，並將它們存儲在 session 中
		int[] randomArray = CreateNewCards.generateRandomArray();
		
		session.setAttribute("numbers", randomArray);
		
		
		// 將隨機數字陣列顯示在網頁上
		//request.setAttribute("numbers", randomArray);
		//request.getRequestDispatcher("guess.jsp").forward(request, response);
		
		
		ArrayList<Integer> correctGuesses = new ArrayList<Integer>();
		
		//ArrayList<Integer> wrongGuesses = new ArrayList<Integer>();
		
		ArrayList<Integer> dealsCards = new ArrayList<Integer>();
		
		ArrayList<Integer> playersCards = new ArrayList<Integer>();
		
		
		for(int i = 0 ; i <randomArray.length; i++) {
			
			dealsCards.add(randomArray[i]);
			
		}
		
		playersCards.add(guess1);
		playersCards.add(guess2);
		playersCards.add(guess3);
		playersCards.add(guess4);
		playersCards.add(guess5);
		

		for (int i = 0; i < 5; i++) {
			
			if (randomArray[i] == guess1) {

				correctGuesses.add(guess1);

			} else if (randomArray[i] == guess2) {
				
				correctGuesses.add(guess2);
				
			} else if (randomArray[i] == guess3) {
				
				correctGuesses.add(guess3);
				
			} else if (randomArray[i] == guess4) {
				
				correctGuesses.add(guess4);
				
			} else if (randomArray[i] == guess5) {
				
				correctGuesses.add(guess5);
				
			} 
			
		}
		
		

//		response.getWriter().println("You guessed " + correctGuesses.size() + " numbers correctly!<br><br>");
//		
//		response.getWriter().println("Correct guesses: " + correctGuesses + "<br><br>");
//		
//		response.getWriter().println("Dealer's Cards: " + dealsCards + "<br>");
//		
//		response.getWriter().println("playerer's Cards: " + playersCards + "<br>");
		
		
		
		String responseMsg = String.format("恭喜您猜對了【%d】個數字 <br><br> 猜對的數字有：%s<br><br> 您輸入的數字為：%s<br><br>電腦的隨機亂數為：%s"
				,correctGuesses.size(),correctGuesses,playersCards,dealsCards);
		
		//Dispatcher
		////////////////////////////////////////////////////////////////////////////////////
		
		
//		request.setAttribute("message", responseMsg);
//		
//		
//		request.getRequestDispatcher("/Game007Temp.jsp").forward(request, response);
		
		////////////////////////////////////////////////////////////////////////////////////
		
		
		session.setAttribute("responseMsg", responseMsg); 
		
		
		
		session.setAttribute("correctSize", correctGuesses.size()); 
		
		session.setAttribute("correctNumbers", correctGuesses); 
		
		session.setAttribute("playersCards", playersCards); 
		
		session.setAttribute("dealerNumbers", dealsCards); 
		
		
		
		
		response.sendRedirect(request.getContextPath() + "/Game007Temp.jsp");
		
		
		
		

		}else {
			
			
			int guess1 = Integer.parseInt(request.getParameter("guess1"));
			int guess2 = Integer.parseInt(request.getParameter("guess2"));
			int guess3 = Integer.parseInt(request.getParameter("guess3"));
			int guess4 = Integer.parseInt(request.getParameter("guess4"));
			int guess5 = Integer.parseInt(request.getParameter("guess5"));
			
			
			ArrayList<Integer> correctGuesses = new ArrayList<Integer>();
			
			//ArrayList<Integer> wrongGuesses = new ArrayList<Integer>();
			
			ArrayList<Integer> dealsCards = new ArrayList<Integer>();
			
			ArrayList<Integer> playersCards = new ArrayList<Integer>();
			
			
			for(int i = 0 ; i <ThisRoundRandomArray.length; i++) {
				
				dealsCards.add(ThisRoundRandomArray[i]);
				
			}
			
			playersCards.add(guess1);
			playersCards.add(guess2);
			playersCards.add(guess3);
			playersCards.add(guess4);
			playersCards.add(guess5);
			

			for (int i = 0; i < 5; i++) {
				
				if (ThisRoundRandomArray[i] == guess1) {

					correctGuesses.add(guess1);

				} else if (ThisRoundRandomArray[i] == guess2) {
					
					correctGuesses.add(guess2);
					
				} else if (ThisRoundRandomArray[i] == guess3) {
					
					correctGuesses.add(guess3);
					
				} else if (ThisRoundRandomArray[i] == guess4) {
					
					correctGuesses.add(guess4);
					
				} else if (ThisRoundRandomArray[i] == guess5) {
					
					correctGuesses.add(guess5);
					
				} 
				
			}

//			response.getWriter().println("You guessed " + correctGuesses.size() + " numbers correctly!<br><br>");
//			
//			response.getWriter().println("Correct guesses: " + correctGuesses + "<br><br>");
//			
//			response.getWriter().println("Dealer's Cards: " + dealsCards + "<br>");
//			
//			response.getWriter().println("playerer's Cards: " + playersCards + "<br>");
			
			
			
			
			String responseMsg = String.format("恭喜您猜對了【%d】個數字 <br><br> 猜對的數字有：%s<br><br> 您輸入的數字為：%s<br><br>電腦的隨機亂數為：%s"
					,correctGuesses.size(),correctGuesses,playersCards,dealsCards);
			
			//Dispatcher
			////////////////////////////////////////////////////////////////////////////////////
			
			
//			request.setAttribute("message", responseMsg);
//			
//			
//			request.getRequestDispatcher("/Game007Temp.jsp").forward(request, response);
			
			////////////////////////////////////////////////////////////////////////////////////
			
			
			session.setAttribute("responseMsg", responseMsg); 
			
			
			
			session.setAttribute("correctSize", correctGuesses.size()); 
			
			session.setAttribute("correctNumbers", correctGuesses); 
			
			session.setAttribute("playersCards", playersCards); 
			
			session.setAttribute("dealerNumbers", dealsCards); 
			
			
			
			
			response.sendRedirect(request.getContextPath() + "/Game007Temp.jsp");
			
			////////////////////////////////////////////////////////////////////////////////////
			
			
			
			
			
			
			
			
		}
		
		
		
	}
	

}
