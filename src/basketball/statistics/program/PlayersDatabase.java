package basketball.statistics.program;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class PlayersDatabase extends BasketballPlayer {
    
    protected ArrayList<BasketballPlayer> players = new ArrayList<>();
    
    public PlayersDatabase() {}
    
    public void addPlayer()
    {
        BasketballPlayer player = new BasketballPlayer();
        player = createPlayer(player);
        players.add(player);
    }
    
    public void addPlayer(BasketballPlayer filledPlayer)
    {
        players.add(filledPlayer);
    }
    
    public BasketballPlayer containsName(String name)
    {
        for (BasketballPlayer p: players)
            if (name.equals(p.name))
                return p;
        
        return null;
    }
    
    public void editPlayer(BasketballPlayer player)
    {
        boolean answer = yesOrNo("Do you want to change your player's name (Yes or No)?");
        
        if (answer)
        {    
            String name = inputStringNotEmpty("What do you want to change this player's name to?");
            player.assignName(name);
        }
                
        answer = yesOrNo("Do you want to change this player's position (Yes or No)?");
        
        if (answer) 
        {
            String input = inputStringNotEmpty("Enter your player's position: ");
            player.setPosition(input);
        }

        else 
            System.out.println("Sweet, let's move on.");

        System.out.println("====================================================================================================================");
        answer = yesOrNo("Do you want this player to have his statistics edited?");

        if (answer) 
            player.setStats();

        else 
            System.out.println("Sweet, let's move on.");
        
        System.out.println("====================================================================================================================");
    }
    
    public boolean isEmpty() 
    {
        return players.isEmpty();
    }
    
    public void listDatabase()
    {
        Collections.sort(players);
        
        for (BasketballPlayer p: players)
            p.playerStats();
    }
    
    public ArrayList<String> listNames()
    {
        ArrayList<String> names = new ArrayList<>();
        
        for(BasketballPlayer p: players)
            names.add(p.name);
        
        return names;
    }
    
    public void printDatabase() throws FileNotFoundException 
    {
        System.out.println("====================================================================================================================");
        String fileName = textFileChecker("What is the name of this file, followed by \".txt\"");
        
        printPlayerStats(players, fileName);    
    }
    
    public void printPlayerStats(ArrayList<BasketballPlayer> players, String fileName) throws FileNotFoundException
    {
        PrintWriter out = new PrintWriter(fileName);
        
        for (BasketballPlayer p: players)
            p.printPlayerStats(out);
        
        out.close();
    }
    
    public static String textFileChecker(String prompt) 
    {
        
        String value = inputStringNotEmpty(prompt);
        
        if (!(value.substring(value.length() - 4, value.length()).equals(".txt")))                    
            value = value + ".txt";
            
        return value;
    }
    
}
