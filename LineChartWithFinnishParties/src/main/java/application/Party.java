package application;

import java.util.ArrayList;
import java.util.List;

public class Party {
    
    private String name;
    private List<String> supportList;
    
    public Party (String[] parts) {
        this.name = parts[0];
        this.supportList = new ArrayList<>(addSupport(parts));
    }

    public ArrayList<String> addSupport(String[] parts) {
        ArrayList<String> list = new ArrayList();
        

        for (int i = 1; i < parts.length; i++) {
            list.add(parts[i]);
        }

        return list;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getSupportData() {
        return this.supportList;
    }

    public String getNumber(int index) {
        return this.supportList.get(index);
    }

    public void printAllNumbers() {
        System.out.println(this.name);
        for (String value: supportList) {
            System.out.println(value);
        }
    }


    @Override
    public String toString() {
        String output = this.name + "\n";
        for (String value: this.supportList) {
            output = output + value + "\n";
        }

        return output;
    }
}
