package catalogue;

import org.bson.Document;

import java.util.ArrayList;

public class CarCatalogueView {

    static void showString(String string) {
        System.out.print(string);
    }

    private static final String CELL_FORMAT = "%20s";

    private static String rowFormat (int columnsCount) {

        String result = "";
        for (int i = 0; i < columnsCount; i++) {
            result += CELL_FORMAT; //@CHECK: String is immutable
        }
        return result;
    }

    void showActions() {
        System.out.print(
                "\n" +
                "1 - Show all cars\n" +
                "2 - Add new car\n" +
                "3 - Delete car\n" +
                "4 - Edit car info\n" +
                "0 - Exit\n" +
                "Please enter the command:\n"
        );
    }

    void showTable(String[] headers, ArrayList<Document> allCars) {

        this.printTableHeaders(headers);

        int i = 1; //user friendly numeration starts from 1
        for (Document car : allCars) {
            System.out.printf(CELL_FORMAT, i++);

            printCar(headers, car);

            System.out.print("\n");
        }

        System.out.print("\n\n");
    }

    private void printTableHeaders(String[] headers){

        System.out.printf(CELL_FORMAT, "#");

        for (String header : headers) {
            System.out.printf(CELL_FORMAT, header);
        }
        System.out.print("\n");
    }

    private void printCar(String[] headers, Document car) {

        for (String header : headers) {
            System.out.printf(CELL_FORMAT, car.get(header));
        }

    }
}
