import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
	
	//Generic main method to start program.
	public static void main(String[] args) {
		gameStart();
	}
	
	//Actual game method
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public static void gameStart() {
		//Declares Scanner for user input
		Scanner in = new Scanner(System.in);
		
		//Sets a maximum for what the numbers can range up to.
		int Max = 10;
		Random rand = new Random();
		int randAmnt;
		
		//Sets boolean for if the player wants to play
		boolean doPlay = true;
		
		while(doPlay) {
			//Declares random amount of numbers the game wants the user to guess.
			randAmnt = rand.nextInt(Max) + 1;
			
			//Boolean to see if the player is still wrong
			boolean wrong = true;
			
			//Boolean to see if this is the users first guess or not.
			boolean firstGuess = true;
			
			//Declares and sets a linked bag that holds the answer to the game.
			LinkedBag answer = new LinkedBag();
			//Prints the answer for testing perposes
			System.out.printf("Answer: ");
			for(int i = 0; i < randAmnt; i++) {
				int numberAns = rand.nextInt(Max) + 1;
				System.out.printf("%d ", numberAns);
				answer.add(numberAns);
			}
			System.out.printf("%n");
			
			//Asks user for their guesses.
			System.out.printf("Enter %d integers in the range from 1 to %d. Entries may be duplicate.%n", randAmnt, Max);
			
			//Takes user guesses. Repeats if they guess incorrectly.
			while(wrong) {
				
				//Creates a copy linked bag of the answer.
				LinkedBag answerCopy = answer.copy();
				
				//Takes in user's guesses and removes them from the copy answer.
				for(int i = 0; i < randAmnt; i++) {
					//Requires a try and catch.
					answerCopy.remove(in.nextInt());
				}
				
				//they are correct if the copied answer is empty
				if(answerCopy.isEmpty()) {
					wrong = false;
					System.out.printf("You are correct! Play again? (yes,no)%n");
					String yesNo = "";
					
					//Only takes yes or no for an answer.
					while(!yesNo.toLowerCase().equals("yes") && !yesNo.toLowerCase().equals("no")) {
						yesNo = in.nextLine();
					}
					
					if(yesNo.toLowerCase().equals("no")) {
						doPlay = false;
						System.out.printf("Goodbye!");
						in.close();
					}
					
				}else {
					System.out.printf("%d of your guesses are correct.", (randAmnt - answerCopy.getCurrentSize())); 
					if (firstGuess) {
						System.out.printf(" Guess again.%n");
						firstGuess = false;
					}else {
						System.out.printf("%n");
					}
					
				}
				
			}
			
		}
	}

}
