package selcpkg;

import java.util.Scanner;

class Interactive {
	static Scanner sc = new Scanner(System.in);
	
	static String askUser(String question, String allowedAnswers) {
		String input;
		String notAllowedAnswer = "Введен недопустимый ответ! Повторите ввод!";
		while (true) {
			System.out.print(question + ": ");
			input = sc.nextLine();
			if (input.matches(allowedAnswers)) {
				break;
			} else {
				System.out.println(notAllowedAnswer);
			}
		}
		return input;
	}
}
