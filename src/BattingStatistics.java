import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BattingStatistics {

	public static void main(String[] args) {
		
		boolean cont = true;
		System.out.println("Welcome to Batting Average Calculator");
		
		do {
			int nPlayers = numPlayers();
			int[][] atBatArray = creatingArray(nPlayers);
		
			double[] BatAvg = calcPerc(atBatArray, nPlayers,0);
			double[] SlugAvg = calcPerc(atBatArray, nPlayers,1);
		
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
    //Getting number of players;
    public static int numCheck(String enter, int value){
    	int num;
    	while(true){
    		try{
    			System.out.print(enter);
    			num = test();
    			if(value == 0){
    				if (num <= 0) continue;
    				else break;}
    			else if(value==1){
    				if (num < 0) continue;
    				else break;
    			}
    		}catch(InputMismatchException ime){
    			System.out.println("You made an error");
    		}
			
		}
		return num;
    }
    
    //Getting number of players
    public static int numPlayers(){
    	String enterPlayer = "Enter the number of players: ";
    	int numPlayers= numCheck(enterPlayer,0);
		return numPlayers;
    }
	
    //Getting user input for number of at bats
	public static int creatingLength(){
		String atBat = "Enter the number of at bats: ";
		int numAtBat = numCheck(atBat,0);

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
				String enter = "Result for at-bat" +(i+1)+": ";
				int AtBatValue = numCheck(enter,1);
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
	public static double[] calcPerc(int[][] atBatArray, int players, int value){
		double[] batAvg = new double[players];  
		double[] SlugPerc = new double[players];
		
		for (int j=0; j<atBatArray.length; j++){
			double count = 0;
			double total = 0;
			for (int i=0; i<atBatArray[j].length; i++){
				if (atBatArray[j][i] == 0){
					continue;
				}else{
					++count;
					total+=atBatArray[j][i];
				}
			}
			batAvg[j] = count/atBatArray[j].length; 
			SlugPerc[j] = total/atBatArray[j].length;
		}
		if(value == 0){
			return batAvg;
		}
		else{ 
			return SlugPerc;
		}
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