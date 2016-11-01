import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BattingStatisticBasic {

	public static void main(String[] args) {
		
		boolean cont = true;
		System.out.print("Welcome to Batting Average Calculator");
		
		do {
			int nPlayers = numPlayers();
			int[][] atBatArray = creatingArray(nPlayers);
		
			double[] BatAvg = calcBatAvg(atBatArray, nPlayers);
			double[] SlugAvg = calcSlugging(atBatArray, nPlayers);
		
			BigDecimal[] BatAvgRounded = getBigDecimal(BatAvg);
			BigDecimal[] SlugPercRounded = getBigDecimal(SlugAvg);
			
			for(int i=0; i<nPlayers;i++){
				System.out.printf("\nBatter %d Batting average: " + 
						BatAvgRounded[i] + " Slugging Percentage: " + 
						SlugPercRounded[i], (i+1));
			}
		
			char response = checkingForYesOrNo();
			cont = translatingAgain(response);
			exitting(cont);
		
		}while(cont);
	}
	
	//Scanner for int values
    private static int test() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    
    //Getting number of players
    public static int numPlayers(){
    	int numPlayers;
    	while(true){
    		try{System.out.print("\nEnter number of players: ");
			numPlayers = test();
			if (numPlayers <= 0) continue;
			else break;
    		} catch(InputMismatchException ime){
    			System.out.println("You made an error");
    		}
			
		}
		return numPlayers;
    }
	
    //Getting user input for number of at bats
	public static int creatingLength(){
		int numAtBat;
		
		while (true){
			try{System.out.println("Enter number of times at bat: ");
			numAtBat = test();
			
			if (numAtBat <= 0) continue;
			else break;
			} catch(InputMismatchException ime){
				System.out.println("You made an error");
			}
		}
		return numAtBat;
	}
	
	//Calculate BattingAvg
	public static int[][] creatingArray(int nPlayers){
		
		int numAtBat = creatingLength();
		int numPlayers = nPlayers;
		int[][] atBatArray = new int[numPlayers][numAtBat];
		System.out.println("0=out, 1=single, 2=double, 3=triple, 4=home run\n");
		
		for (int j=0;j<numPlayers;j++){
			System.out.printf("Player %d at-bat result \n\n",(j+1));
			for(int i=0; i<numAtBat;i++){
				System.out.printf("Result for at-bat %d: ",(i+1));
				int AtBatValue = test();
				if (AtBatValue < 0 || AtBatValue > 4){ 
					--i;
					continue;}
				else atBatArray[j][i] = AtBatValue;
			}
			System.out.print("\n");
		}
		return atBatArray;
	}
	
	//Calculate the BattingAvg
	public static double[] calcBatAvg(int[][] atBatArray, int players){
		double[] batAvg = new double[players];  
		for (int j=0; j<atBatArray.length; j++){
			double count = 0;
			for (int i=0; i<atBatArray[j].length; i++){
				if (atBatArray[j][i] == 0){
					continue;
				}else{
					++count;
				}
			}
			batAvg[j] = count/atBatArray[j].length; 
		}
		return batAvg;
	}
	
	//Calculate SluggingPercentage
	public static double[] calcSlugging(int[][] atBatArray, int players){
		double[] SlugPerc = new double[players];  
		
		for (int j=0; j<atBatArray.length; j++){
			double total=0;
			for (int i=0; i<atBatArray[j].length; i++){
				if (atBatArray[j][i] == 0){
					continue;
				}else{
					total+=atBatArray[j][i];
				}
			}
			SlugPerc[j] = total/atBatArray[j].length; 
		}
		return SlugPerc;
	}
	
	//Creating Big Decimal 3 decimal long
	public static BigDecimal[] getBigDecimal(double[] Avg){
		BigDecimal[] BatAvgRounded = new BigDecimal[Avg.length];
		for(int i=0; i<Avg.length; i++){
			BigDecimal BatAvgBD = new BigDecimal(Avg[i]);
			BatAvgRounded[i] = BatAvgBD.setScale(3, RoundingMode.CEILING);
		}
		return BatAvgRounded; 
	}
	
	//Checking if response was yes or no
	public static char checkingForYesOrNo(){
		Scanner sc = new Scanner(System.in);
		
		String response = "";
		boolean cont = true;
		while(cont){
			System.out.print("\nContinue to calculate? (y/n): ");
			response = sc.nextLine();
			if (response.toLowerCase().charAt(0) != 'y' && response.toLowerCase().charAt(0) != 'n'){
				continue;
				}
			else{
				break;
			}
		}
		return response.toLowerCase().charAt(0);
	}
	//Breaking while loop if false 
	public static boolean translatingAgain(char response){
		if (response == 'y'){
			return true;
		} else{
			return false;
		}
	}
	
	//Closing message
	public static void exitting(boolean cont){
		if (cont == false){
			System.out.println(	"\nHave an awesome day!");
		}
	}
}

