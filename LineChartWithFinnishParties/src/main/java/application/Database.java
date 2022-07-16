package application;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Database {
    
    private List<Party> parties;

    public Database(String file) {
        this.parties = new ArrayList<>(readFile(file));

    }

    public List<Party> readFile (String file) {
        try {
            return Files.lines(Paths.get(file))
            .map(s -> s.split("\t"))
            .map(parts -> new Party(parts))
            .collect(Collectors.toList());


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    public void printAllParties() {
        for (Party party : this.parties){
            System.out.println(party);
            System.out.println("==========");
        }
    }



    public List<Party> getParties() {
        
        return this.parties;
    }

    public Party getParty(int index) {
        return this.parties.get(index);
    }



}
