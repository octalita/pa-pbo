package PA;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

public class Supermarket {
    //private instance variables
    private String ID;
    private String name;
    private String type;
    private int stock;
    private int price;
    private LocalDate expired;

    public Supermarket(){

    }

    public Supermarket(String ID, String name, int stock, int price){
        this.ID = ID;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public Supermarket(String ID, String name, String type, int stock, int price, LocalDate expired) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.expired = expired;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getExpired() {
        return expired;
    }

    public void setExpired(LocalDate expired) {
        this.expired = expired;
    }

    public String getFormattedPrice() {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        String formattedPrice = formatRupiah.format(price);
        return formattedPrice.replaceAll("Rp", "Rp").replaceAll(",00", "");
    }
}