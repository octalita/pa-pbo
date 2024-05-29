package PA;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class SupermarketApp {
    //instance variables
    Scanner scan = new Scanner(System.in);
    DataSupermarket data = new DataSupermarket();
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-M-yyyy");
    Supermarket smt = new Supermarket();

    public static void main(String[] args) {
        SupermarketApp app = new SupermarketApp();
        app.getMenus();
    }

    public void getMenus() {
        System.out.println("+==========================+");
        System.out.println("       Supermarket App      ");
        System.out.println("+==========================+");
        System.out.println(" 1. Menambahkan Item");
        System.out.println(" 2. Menampilkan Item");
        System.out.println(" 3. Pembelian Item");
        System.out.println(" 4. Keluar");
        System.out.println("+==========================+");
        System.out.print(" Pilihan: ");
        getChoice(Integer.parseInt(scan.nextLine()));
    }

    public void getChoice(int choice){
        switch(choice){
            case 1: addItem(); break;
            case 2: displayItem(); break;
            case 3: buyItem(); break;
            case 4:
                System.out.println("Thank you for using this app!"); break;
            default:
                System.out.println("Incorrect menu!");
        }
    }

    public void addItem(){
        System.out.println("+==========================+");
        System.out.println("          Add Item          ");
        System.out.println("+==========================+");
        System.out.print(" ID Item                 : ");
        smt.setID(scan.nextLine());
        System.out.print(" Name                    : ");
        smt.setName(scan.nextLine());
        System.out.print(" Type                    : ");
        String itemType = scan.nextLine().toLowerCase();
        smt.setType(itemType);;
        if ("fruit".equals(itemType) || "vegetable".equals(itemType)) {
            LocalDate expiryDate = LocalDate.now().plusDays(4);
            smt.setExpired(expiryDate);
            System.out.println(" Exp Date                : " + expiryDate.format(dateFormat));
        } else {
            System.out.print(" Expiry Date (dd-MM-yyyy): ");
            String expiryDateString = scan.nextLine();
            LocalDate expiryDate;
            try {
                expiryDate = LocalDate.parse(expiryDateString, dateFormat);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
                return;
            }
            smt.setExpired(expiryDate);
        }
        System.out.print(" Stock                   : ");
        smt.setStock(Integer.parseInt(scan.nextLine()));
        System.out.print(" Price                   : Rp");
        smt.setPrice(Integer.parseInt(scan.nextLine()));
        data.insertSupermarket(smt.getID(), smt.getName(), smt.getType(), smt.getStock(), smt.getPrice(), smt.getExpired());
        System.out.println("+==========================+");
        System.out.println(" Penambahan Item Berhasil!");
        System.out.println("+==========================+");
        System.out.print(" Tambahkan Item Lagi?[Y/T]");
        char val = scan.nextLine().charAt(0);
        if(val == 'y' || val == 'Y') {
            addItem();
        } else if (val == 't' || val == 'T') {
            getMenus();
        }
    }
    private void displayItem() {
        System.out.println("+===========================+");
        System.out.println("         Display Item        ");
        System.out.println("+===========================+");
        System.out.println("==================================================" +
                "=============================================================");
        System.out.printf("%-4s %-10s %-20s %-15s %-10s %-15s %10s%n", "No", "ID", "Nama", "Tipe", "Stock", "Tanggal Kadaluarsa", "Price");
        System.out.print("==================================================" +
                "=============================================================\n");
        List listsupermarket = new ArrayList();
        listsupermarket = data.getAll();
        for (int i = 0; i < listsupermarket.size(); i++) {
            Supermarket a = (Supermarket) listsupermarket.get(i);
            System.out.printf("%-4s %-10s %-20s %-15s %-10s %-15s %16s%n", i + 1, a.getID(), a.getName(), a.getType()
                    ,a.getStock(), a.getExpired().format(dateFormat), a.getFormattedPrice());
        }
        System.out.println("===================================================" +
                "==============================================================");
        System.out.print(" Tetap Lihat data Item[Y/T]");
        char val = scan.nextLine().charAt(0);
        if (val == 'y' || val == 'Y') {
            displayItem();
        } else if (val == 't' || val == 'T') {
            getMenus();
        }
    }

    private void buyItem() {
        System.out.println("+===========================+");
        System.out.println("           Buy Item          ");
        System.out.println("+===========================+");
        System.out.print("Banyak item yang ingin dibeli : ");
        int banyak = Integer.parseInt(scan.nextLine());
        System.out.println("+===========================+");

        List<Supermarket> listsupermarket = new ArrayList<>();

        for (int i = 0; i < banyak; i++) {
            System.out.print("ID Item       : ");
            String id = scan.nextLine();
            Supermarket supermarket = data.find(data.getAll(), id);
            if (supermarket != null) {
                System.out.print("Qty           : ");
                int qty = Integer.parseInt(scan.nextLine());
                if (qty <= supermarket.getStock()) {
                    boolean itemExists = false;
                    for (Supermarket item : listsupermarket) {
                        if (item.getID().equals(supermarket.getID())) {
                            item.setStock(item.getStock() + qty);
                            itemExists = true;
                            break;
                        }
                    }
                    if (!itemExists) {
                        listsupermarket.add(new Supermarket(supermarket.getID(), supermarket.getName(), supermarket.getType(), qty, supermarket.getPrice(), supermarket.getExpired()));
                    }
                    System.out.println("Transaksi berhasil!");
                    supermarket.setStock(supermarket.getStock() - qty);
                    System.out.println("=======================================================================");
                    System.out.printf("%-4s %-10s %-20s %-10s %-10s%n ", "No", "Id Item", "Title", "Qty", "Price");
                    System.out.println("=======================================================================");
                    int totalCost = 0;
                    for (int j = 0; j < listsupermarket.size(); j++) {
                        Supermarket a = listsupermarket.get(j);
                        int cost = a.getPrice() * a.getStock();
                        totalCost += cost;
                        System.out.printf("%-4d %-10s %-20s %-10d Rp.%-10d%n ", j + 1, a.getID(), a.getName(), a.getStock(), a.getPrice());
                    }
                    System.out.println("=======================================================================");
                    System.out.printf("%-34s Rp.%-10d%n", "Total", totalCost);
                    System.out.println("=======================================================================");
                } else {
                    System.out.println("Stok Item Tidak Mencukupi! Silakan pilih item lain...");
                    i--;
                }
            } else {
                System.out.println("Item tidak ditemukan! Silakan pilih item lain...");
                i--;
            }
        }
        System.out.print(" Tetap Membeli Item?[Y/T]");
        char val = scan.nextLine().charAt(0);
        if (val == 'y' || val == 'Y') {
            displayItem();
        } else if (val == 't' || val == 'T') {
            getMenus();
        }
    }
}