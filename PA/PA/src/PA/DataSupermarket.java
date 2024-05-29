package PA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataSupermarket {
    //private instance variables
    private List listsupermarket = new ArrayList();

    //Constructor class
    public DataSupermarket() {
        this.listsupermarket = new ArrayList();
    }

    //Return the listsupermarket value
    public List getAll() {
        return listsupermarket;
    }

    //method for add the medicine into the list
    public void insertSupermarket(String ID, String name, String type, int stock, int price, LocalDate expired) {
        Supermarket supermarket = new Supermarket(ID, name, type, stock, price, expired);
        listsupermarket.add(supermarket);
    }

    public Supermarket find(List<Supermarket> medicines, String Id) {
        for(int i = 0; i < medicines.size(); i++) {
            if(medicines.get(i).getID().equals(Id)) {
                return medicines.get(i);
            }
        }
        return null;
    }

}