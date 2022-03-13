package basketball.statistics.program;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

enum POSITION{PG, SG, SF, PF, C}

public class BasketballPlayer implements Comparable<BasketballPlayer> 
{
    
    protected String name;
    protected POSITION position;
    protected double points;
    protected double rebounds;
    protected double assists;
    protected double steals;
    protected double blocks;
    protected double turnovers;
	
    public BasketballPlayer() 
    {
        name = "Unknown Name";
	position = null;
	points = 0.0;
	rebounds = 0.0;
	assists = 0.0;
	steals = 0.0;
	blocks = 0.0;
	turnovers = 0.0;
    }
    
    public BasketballPlayer(String n, POSITION pos, double poi, double r, double a, double s, double b, double t)
    {
        name = n;
        position = pos;
        points = poi;
        rebounds = r;
        assists = a;
        steals = s;
        blocks = b;
        turnovers = t;
    }
    
    public void assignName(String n) 
    {
	name = n;
    }
  
    public BasketballPlayer createPlayer(BasketballPlayer player)
    {
        System.out.println("====================================================================================================================");
        String name = inputStringNotEmpty("Enter the basketball player's name: ");
        player.assignName(name);       
        boolean answer = yesOrNo("Do you want to add a position to this player (Yes or No)?");
        System.out.println("====================================================================================================================");
        
        if (answer) 
        {
            String input = inputStringNotEmpty("Enter your player's position: ");
            player.setPosition(input);
        }

        else 
            System.out.println("That's okay! You can always add the position later on.");
        

        answer = yesOrNo("Do you want this player to have statistics entered (Yes or No)?");
        
        if (answer) 
            player.setStats();

        else
            System.out.println("That's okay! You can always add the statistics later on.");
            
        return player;
    }
    
    @Override
    public int compareTo(BasketballPlayer other)
    {
        return name.compareTo(other.name);
    }
	
    public void enterAllStats(double p, double r, double a, double s, double b, double t) 
    {
	points = p;
	rebounds = r;
	assists = a;
	steals = s;
	blocks = b;
	turnovers = t;
    }
    
    public static String inputStringNotEmpty(String prompt) 
    {
        Scanner scan = new Scanner(System.in);	
        String value = "";
        boolean flag = false;
        
        // Assuming it is false to start the validation.
        while (!flag) 
        {
            System.out.println(prompt);
            value = scan.nextLine();
            
            // Checking to see if the user entered something.
            if (value.length() > 0) 
                flag = true;

            else 
                System.out.println("Try again.");
        }
        return value;
    }
    
    public double inputValidation(String prompt) 
    {
	double value = 0;
	Scanner scan = new Scanner(System.in);
	
        System.out.println(prompt);
	
        // making sure the input is a double.
	while (!scan.hasNextDouble()) 
        {
		scan.nextLine();
		System.out.println("Input value must be a whole number.");
		System.out.println(prompt);
	}
	
        value = scan.nextDouble();
	
        // making sure the input is greater than the intended value.
	
        while (value <= 0) 
        {
		System.out.println("Input value must be greater than zero.");
		System.out.println("Enter a decimal greater an zero.");
		value = scan.nextDouble();
	}
	return value;
    }
    
    public void playerStats() 
    {
        System.out.printf("%s, %s\n%.1f points\n%.1f rebounds\n%.1f assists\n%.1f steals\n%.1f blocks\n%.1f turnovers\n=======================================\n", name, position, points, rebounds, assists, steals, blocks, turnovers);
    }
    
    public void printPlayerStats(PrintWriter out) throws FileNotFoundException
    {
        out.printf("%s, %s\n%.1f points\n%.1f rebounds\n%.1f assists\n%.1f steals\n%.1f blocks\n%.1f turnovers\n=======================================\n", name, position, points, rebounds, assists, steals, blocks, turnovers);
    }
    
    public void setPosition(String input)
    {
        Scanner scan = new Scanner(System.in);
        
        while (!(input.toUpperCase().equals("PG") || input.toUpperCase().equals("SG") || input.toUpperCase().equals("SF") || input.toUpperCase().equals("PF") || input.toUpperCase().equals("C")))
        {
            System.out.println("Enter a valid position (PG, SG, SF, PF, or C).");
            input = scan.nextLine();
        }
        
        POSITION p = POSITION.valueOf(input);
        position = p;
    }
    
    public void setStats() 
    {
	Scanner scan = new Scanner(System.in);
	boolean finish = false;
        
	while (!finish) 
        {
            System.out.println("====================================================================================================================");
            System.out.println("What statistic did you want to enter for " + name + "? Enter 'choices' if unsure what to enter. Enter 'done' to finish.");
            String input = scan.nextLine();
            
            System.out.println("====================================================================================================================");
            if (input.toLowerCase().equals("points")) 
            {
                points = inputValidation("What do you want to change " + name + "'s average points per game to?");
                System.out.println("Success!");
            }

            else if (input.toLowerCase().equals("rebounds")) 
            {
                rebounds = inputValidation("What do you want to change " + name + "'s average rebounds per game to?");
                System.out.println("Success!");
            }

            else if (input.toLowerCase().equals("assists"))
            {
                assists = inputValidation("What do you want to change " + name + "'s average assists per game to?");
                System.out.println("Success!");
            }

            else if (input.toLowerCase().equals("steals")) 
            {
                steals = inputValidation("What do you want to change " + name + "'s average steals per game to?");
                System.out.println("Success!");
            }

            else if (input.toLowerCase().equals("blocks"))
            {
                blocks = inputValidation("What do you want to change " + name + "'s average blocks per game to?");
                System.out.println("Success!");
            }

            else if (input.toLowerCase().equals("turnovers")) 
            {
                turnovers = inputValidation("What do you want to change " + name + "'s average turnovers per game to?");
                System.out.println("Success!");
            }

            else if (input.toLowerCase().equals("choices"))
                System.out.println("You can enter points, rebounds, assists, steals, blocks, and turnovers for a player.");

            else if (input.toLowerCase().equals("done"))
            {
                finish = true;
                System.out.println();
            }

            else
                System.out.println("That is not a valid statistic. Please choose points, rebounds, assists, steals, blocks, or turnovers. Enter 'done' to be done.");
        }
        System.out.println("====================================================================================================================");
    }
    
    public static boolean yesOrNo(String prompt) 
    {
	System.out.println("====================================================================================================================");
        boolean flag = false;
	boolean answer = false;
	
        while (!flag)
        {
            String input = inputStringNotEmpty(prompt);
		
            if (input.toLowerCase().equals("yes")) 
            {
                answer = true;
                flag = true;
            }
            
            else if (input.toLowerCase().equals("no")) 
            {
                answer = false;
                flag = true;
            }
            
            else 
            {
                System.out.println("Try again.");
                System.out.println("====================================================================================================================");
            }
	}
	return answer;
    }
}
