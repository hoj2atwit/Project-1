import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
	
	
	// Number determining the # of integers to guess - 1; +1 is added later on to ensure you don't have to guess 0 
	private static int randAmnt;
	// Sets a maximum range for the numbers to be guessed
	private static int Max = 10;
	// Random to be used for determining # of ints to be guessed and value of those ints
	private static Random rand = new Random();
	// LinkedBag to be used to contain the answers
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
		
		//Declares Scanner for user input
		Scanner in = new Scanner(System.in);
		
		//Sets boolean for if the player wants to play
		boolean doPlay = true;
		
		//Boolean to see if this is the users first guess or not.
		boolean firstGuess = true;
		
		while(doPlay) {
			
			//Declares random amount of numbers the game wants the user to guess.
			randAmnt = rand.nextInt(Max) + 1;
			
			setAnswerRandom();
			
			//Asks user for their guesses.
			System.out.printf("Enter %d integers in the range from 1 to %d. Entries may be duplicate.%n", randAmnt, Max);
			
			//Creates a copy linked bag of the answer.
			LinkedBag<Integer> answerCopy = answer.copy();
			
			//Takes user guesses. Repeats if they guess incorrectly.
			while(!guess(in, firstGuess, answerCopy)) {
				System.out.printf("%d of your guesses are correct.", (randAmnt - answerCopy.getCurrentSize()));
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

	/**
	 * Gets random integers and enters them into the answer LinkedBag to use for answer-checking purposes
	 */
	private static void setAnswerRandom() {
		//Declares and sets a linked bag that holds the answer to the game.
		answer = new LinkedBag<Integer>();
		//Prints the answer for testing purposes
		System.out.printf("Answer: ");
		for(int i = 0; i < randAmnt; i++) {
			int numberAns = rand.nextInt(Max) + 1;
			System.out.printf("%d ", numberAns);
			answer.add(numberAns);
		}
		System.out.printf("%n");
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
		for(int i = 0; i < randAmnt; i++) {
			try {
				answerCopy.remove(in.nextInt());
			}catch(Exception e) {
				System.out.printf("Please enter a valid number.%n");
				in.nextLine();
				i--;
			}
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
		in.nextLine();
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
	 * Getter for random amount
	 * @return
	 */
	public static int getRandAmnt() {
		return randAmnt;
	}
	
	/**
	 * Setter for randAmnt
	 * @param input
	 * @return
	 */
	public static boolean setRandAmnt(int input) {
		boolean hasChanged = false;
		if (input > 0) {
			hasChanged = true;
			randAmnt = input;
		}
		return hasChanged;
	}
	
	/**
	 * Getter for Max value
	 * @return
	 */
	public static int getMax() {
		return Max;
	}
	
	/**
	 * Setter for Max value
	 * @param input
	 * @return
	 */
	public static boolean setMax(int input) {
		boolean hasChanged = false;
		if (input > 0) {
			hasChanged = true;
			Max = input;
		}
		return hasChanged;
	}
	
}
