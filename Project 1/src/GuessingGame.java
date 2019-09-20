import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
	
	
	// Number determining the # of integers to guess.
	private static int pickAmnt;
	// Sets a maximum range for the numbers to be guessed.
	private static int max = 10;
	// Random to be used for determining # of ints to be guessed and value of those ints.
	private static Random rand;
	// LinkedBag to be used to contain the answers.
	private static LinkedBag<Integer> answer;
	
	/**
	 * Generic main method to start program
	 * @param args
	 */
	public static void main(String[] args) {
		gameStart();
	}
	
	/**
	 * Actual game method that contains all game processes
	 */
	private static void gameStart() {
		 rand = new Random();
		 
		//Declares Scanner for user input
		Scanner in = new Scanner(System.in);
		
		//Sets boolean for if the player wants to play
		boolean doPlay = true;
		
		//Boolean to see if this is the users first guess or not.
		boolean firstGuess = true;
		
		while(doPlay) {
			
			//Asks user to pick how many numbers they want to guess for.
			requestPickAmnt(in);
			
			//Sets a random answer.
			setAnswerRandom();
			
			//Asks user for their guesses.
			System.out.printf("Enter %d integers in the range from 1 to %d. Entries may be duplicate.%n", pickAmnt, max);
			
			//Creates a copy linked bag of the answer.
			LinkedBag<Integer> answerCopy = answer.copy();
			
			//Takes user guesses. Repeats if they guess incorrectly.
			while(!guess(in, firstGuess, answerCopy)) {
				System.out.printf("%d of your guesses are correct.", (pickAmnt - answerCopy.getCurrentSize()));
				if (firstGuess) {
					System.out.printf(" Guess again.%n");
					firstGuess = false;
				}else {
					System.out.printf("%n");
				}
				
				//Resets answer copy.
				answerCopy = answer.copy();
				
			}
			
			//Asks user if they want to try again
			if(!tryAgain(in)) {
				doPlay = false;
				System.out.printf("Goodbye!");
				in.close();
			}
			
		}
	}
	
	private static void requestPickAmnt(Scanner in) {
		String input = "";
		int num = 0;
		
		//Asks user to input the amount they want to guess for.
		System.out.printf("Enter how many numbers you would like to guess for.(1-%d) or (r for random)%n", max);
		
		//Keeps asking user for input if they do not input a valid number or letter.
		while(!input.equals("r") && (num < 1 || num > max)) {
			try {
				num = in.nextInt();
				while(num > max || num < 1) {
					num = in.nextInt();
				}
				pickAmnt = num;
			}catch(Exception e) {
				input = in.nextLine();
				if(input.equals("r")) {
					pickAmnt = rand.nextInt(max) + 1;
				}
			}
		}

	}

	/**
	 * Gets random integers and enters them into the answer LinkedBag to use for answer-checking purposes
	 */
	private static void setAnswerRandom() {
		//Declares and sets a linked bag that holds the answer to the game.
		answer = new LinkedBag<Integer>();
		//Prints the answer for testing purposes
		//System.out.printf("Answer: ");
		for(int i = 0; i < pickAmnt; i++) {
			int numberAns = rand.nextInt(max) + 1;
			//System.out.printf("%d ", numberAns);
			answer.add(numberAns);
		}
		//System.out.printf("%n");
	}
	
	/**
	 * Takes in the user's guesses until the number of integers entered == answer bag's copy's numberOfEntries
	 * Any invalid input will ask user to enter a valid number
	 * @param in
	 * @param firstGuess
	 * @param answerCopy
	 * @return
	 */
	private static boolean guess(Scanner in, boolean firstGuess, LinkedBag<Integer> answerCopy) {
		//Takes in user's guesses and removes them from the copy answer.
		for(int i = 0; i < pickAmnt; i++) {
			try {
				answerCopy.remove(in.nextInt());
			}catch(Exception e) {
				System.out.printf("Please enter a valid number.%n");
				in.nextLine();
				i--;
			}
		}
		
		//Clears extra inputs if there are any.
		if(in.hasNextLine()) {
			in.nextLine();
		}
		//they are correct if the copied answer is empty
		return answerCopy.isEmpty();
	}
	
	/**
	 * Method for giving an option to play the game again or not
	 * Entering yes re-runs the game
	 * Entering no ends the game
	 * Entering anything else prompts the user to enter yes or no
	 * @param in
	 * @return
	 */
	private static boolean tryAgain(Scanner in) {
		System.out.printf("You are correct! Play again? (yes, no)%n");
		String yesNo = in.nextLine();
		
		//Only takes yes or no for an answer.
		while(!yesNo.toLowerCase().equals("yes") && !yesNo.toLowerCase().equals("no")) {
			System.out.printf("Please enter yes or no.%n");
			yesNo = in.nextLine();
		}
		
		if(yesNo.toLowerCase().equals("no")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Getter for pick amount
	 * @return
	 */
	public static int getPickAmnt() {
		return pickAmnt;
	}
	
	/**
	 * Setter for randAmnt
	 * @param input
	 */
	public static void setPickAmnt(int input) {
		pickAmnt = 0;
		if (input > 0) {
			pickAmnt = input;
		}
	}
	
	/**
	 * Getter for max value
	 * @return value of max
	 */
	public static int getMax() {
		return max;
	}
	
	/**
	 * Setter for max value
	 * @param newmax Input used to set the new max 
	 */
	public static void setMax(int newMax) {
		max = 0;
		if (newMax > 0) {
			max = newMax;
		}
	}
	
}
