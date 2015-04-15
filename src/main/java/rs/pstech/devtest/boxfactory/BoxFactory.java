package rs.pstech.devtest.boxfactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import rs.pstech.devtest.boxfactory.box.*;

public class BoxFactory {

	public static String readUserInput() throws IOException {
		// Function reads user input until user inserts 'done', Only characters A, B, C, D, E are allowed

		List<String> awailableCharacters = Arrays.asList("A", "B", "C", "D", "E");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = null;

		StringBuilder sb = new StringBuilder(20);

		System.out.print('?');

		while (!(input = br.readLine()).equals("done")) {

			if ((input.length() == 1)
					&& (awailableCharacters.contains(input.toUpperCase()))) {
				sb.append(input.toUpperCase() + " ");
				System.out.print('?');
			}
		}

		System.out.println("User input finished.");
		System.out.println("Desired production list: " + sb.toString());

		return sb.toString();
	}

	public static void main(String[] args) throws IOException {

		System.out.println("Welcome to Box Factory application !!!");
		System.out.println("Legend: This application is used for BOX creation");
		System.out.println("Please enter box types which you want to create one by one and press enter after each insert:");
		System.out.println("Available box types and production duration times are shown below:\nA [1s]\nB [2s]\nC [3s]\nD [4s]\nE [5s]");
		System.out.println("Enter \"done\" to finish user input and start processing.");
		System.out.println("Error inputs are ignored - threads won't be created for invalid input characters !!!");

		// Creates thread pool with 10 Threads

		Runnable worker = null;

		ExecutorService executor = Executors.newFixedThreadPool(10);

		String userInput = readUserInput();

		// Split userInput String and iterate over it's elements and create class for each user prefered action and add it to tasks pool

		String[] splitArray = userInput.split(" ");

		for (String str : splitArray) {

			switch (str.charAt(0)) {
			case 'A':
				worker = new BoxA();
				break;
			case 'B':
				worker = new BoxB();
				break;
			case 'C':
				worker = new BoxC();
				break;
			case 'D':
				worker = new BoxD();
				break;
			case 'E':
				worker = new BoxE();
				break;
			default:
				break;
			}
			executor.execute(worker);
		}

		executor.shutdown();

		while (!executor.isTerminated());

		System.out.println("Finished all threads");

		System.out.print("Program execution finished !!!");
	}
}