package basketball.statistics.program;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class BasketballProgram extends BasketballPlayer
{
    
    /*
    public static void editFile() throws FileNotFoundException
    {
        File directory = new File("/users/brycedoyle/NetBeansProjects/Basketball Statistics Program");
        String fileName = inputStringNotEmpty("Type in the name of the file you would like to edit:");
        String[] fileList = directory.list();
        boolean found = false;
        
        if(fileList == null)
        {
            System.out.println("Directory is empty.");
            System.out.println("====================================================================================================================");
        }
            
        
        else
        {
            for(int i = 0; i < fileList.length; i++)
            {
                String foundFile = fileList[i];
                
                if(foundFile.equalsIgnoreCase(fileName))
                {
                    System.out.println("\n" + fileName + " was found.");
                    found = true;
                }
            }
        }
        
        if(!found)
        {
            System.out.println("File not found.");
            System.out.println("====================================================================================================================");
        }
            
        
        else 
        {
            System.out.println("====================================================================================================================");
            File editedFile = new File(fileName);
            PlayersDatabase uneditedFile = readFile(editedFile);
            boolean done = false;
            
            while (!done)
            {
                ArrayList<String> choices = uneditedFile.listNames();
                boolean flag = false;
                String answer = inputStringNotEmpty("Name the player you would like to edit:");
                
                for(String c: choices)    
                    if(c.equals(answer))
                        flag = true;
                
                if (flag)
                {
                    BasketballPlayer change = uneditedFile.containsName(answer);
                    uneditedFile.editPlayer(change);
                }
                else
                    System.out.println("Name not found. Please try again.");
                
                done = yesOrNo("Are you finished editing?");
                
            }
            
            uneditedFile.printPlayerStats(uneditedFile.players, fileName);
            
        }
    }
    */

    public static void newFile() throws FileNotFoundException 
    {
        PlayersDatabase database = new PlayersDatabase();
        boolean stopTheLoop = false;
        boolean answer;
        
        database.addPlayer();
        
        while (!stopTheLoop)
        {
            answer = yesOrNo("Would you like to continue?");
            
            if (answer)
                database.addPlayer();
            
            else
                stopTheLoop = true;
            
        }
        
        if (!(database.isEmpty()))
        {
            database.listDatabase();
            answer = yesOrNo("Would you like to save this database on a .txt file?");
            
            if (answer)
                database.printDatabase();
            
            else
            {
                answer = yesOrNo("Are you sure? If you say no again, none of your progress will be saved.");
                
                if (answer)
                    database.printDatabase();
            }
            
        }
        
    }
    
    public static PlayersDatabase readFile(File input) throws FileNotFoundException
    {
        Scanner scan = new Scanner(input);
        PlayersDatabase edit = new PlayersDatabase();
        boolean run = true;
        
        while (run)
        {
            if (scan.hasNext("=======================================")) 
            { 
                scan.nextLine();
                
                if (!scan.hasNextLine())
                    run = false;
            }
            
            if (run)
            {
                
                String[] player = new String[8];
                String[] firstLine = scan.nextLine().split(", ");
            
                for(int i = 0; i < firstLine.length; i++)
                    player[i] = firstLine[i];
            
                int lineCount = 2;
                
                while (!scan.hasNext("======================================="))
                {                
                    String[] temp = scan.nextLine().split(" ");
                    player[lineCount] = temp[0];
                    lineCount++;
                }
            
                String name = player[0];
                POSITION position = POSITION.valueOf(player[1]);
                double points = Double.valueOf(player[2]);
                double rebounds = Double.valueOf(player[3]);
                double assists = Double.valueOf(player[4]);
                double steals = Double.valueOf(player[5]);
                double blocks = Double.valueOf(player[6]);
                double turnovers = Double.valueOf(player[7]);
            
                edit.addPlayer(new BasketballPlayer(name, position, points, rebounds, assists, steals, blocks, turnovers));
            
            }
        }  
        
        scan.close();
        
        return edit;
    }
    
    public static void main(String args[]) throws FileNotFoundException
    {
        System.out.println("Welcome to a basketball database created by Bryce Doyle.\nHere you can enter basketball players and their statistics for the season in this database.\nIn addition, you can also display their stats and even edit the stats.\nLet's begin!");
        boolean done = false;
        
        while (!done)
        {
            String answer = inputStringNotEmpty("\nWould you like to create a new database or edit an existing one (Type \"create\" or \"edit\")");
        
            if (answer.equals("create"))
                newFile();
            // else if (answer.equals("edit"))
            //    editFile();
            else
                System.out.println("That was not one of the choices. Try again.");    
                            
            done = yesOrNo("Are you ready to exit the database?");
        
        }   
    }       
}
