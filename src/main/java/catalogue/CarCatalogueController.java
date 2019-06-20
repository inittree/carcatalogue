package catalogue;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

public class CarCatalogueController {

    private static Scanner inStream = new Scanner(System.in);

    private static int readInteger() {
        while (!inStream.hasNextInt()){
            CarCatalogueView.showString("Entering string is not integer\n");
            inStream.nextLine();
        }
        return Integer.parseInt(inStream.nextLine());
    }

    private CarCatalogueInfo info;
    private CarCatalogueView view;

    public CarCatalogueController(CarCatalogueInfo info, CarCatalogueView view) {

        this.info = info;
        this.view = view;
    }

    public void start() {

        boolean isSessionActive = true;

        while (isSessionActive) {

            view.showActions();

            switch (readInteger())
            {
                case 0:
                {
                    isSessionActive = false;
                    break;
                }
                case 1:
                {
                    this.showTable();
                    break;
                }
                case 2:
                {
                    this.addCar();
                    break;
                }
                case 3:
                {
                    this.deleteCar();
                    break;
                }
                case 4:
                {
                    this.editCar();
                    break;
                }
                default:
                {
                    CarCatalogueView.showString("Unsupported command\n");
                }
            }
        }

    }

    private void showTable() {

        view.showTable(CarCatalogueInfo.DOCUMENT_FIELDS, this.getAllCarsSortedList());

    }

    private void addCar() {

        CarCatalogueView.showString("Adding car\n");

        Document newCar = new Document();
        for (String carField : CarCatalogueInfo.DOCUMENT_FIELDS) {
            CarCatalogueView.showString("Please enter " + carField + ": ");
            newCar.append(carField, inStream.nextLine());
        }

        if (info.insertCar(newCar))
        {
            CarCatalogueView.showString("\nCar successfully added\n");
        }
        else
        {
            CarCatalogueView.showString("\nCar already exists or something went wrong (check err log), car not added\n");
        }

    }

    private void deleteCar() {

        showTable();

        CarCatalogueView.showString("Deleting car\n");

        long carsCount = info.getAllCarsCount(); //@CHECK: need change to int

        CarCatalogueView.showString("Please enter car number you want to delete [1 .. " + carsCount + "]: \n");

        int carId = this.getValidCarId(carsCount);

        Document deletingCar = getAllCarsSortedList().get(carId);

        if (info.deleteCar(deletingCar)) {
            CarCatalogueView.showString("Car successfully deleted\n");
        }
        else
        {
            CarCatalogueView.showString("Car deleting problem, something went wrong (check err log)\n");
        }

    }

    private void editCar() {

        showTable();

        CarCatalogueView.showString("Editing car\n");

        long carsCount = info.getAllCarsCount();

        CarCatalogueView.showString("Please enter car number you want to edit [1 .. " + carsCount + "]: \n");

        int carId = this.getValidCarId(carsCount);

        Document editingCar = getAllCarsSortedList().get(carId);

        Document editingCarNewInstance = new Document();
        for (String carField : CarCatalogueInfo.DOCUMENT_FIELDS) {

            String carFieldCurrentValue = editingCar.getString(carField);

            CarCatalogueView.showString(
                    "Please enter " + carField +
                    " (use empty string for old value: " + carFieldCurrentValue + "): ");

            String carFieldNewValue = inStream.nextLine();

            if (carFieldNewValue.equals(""))
            {
                carFieldNewValue = carFieldCurrentValue;
            }

            editingCarNewInstance.append(carField, carFieldNewValue);
        }

        if (info.deleteCar(editingCar) && info.insertCar(editingCarNewInstance)) {
            CarCatalogueView.showString("Car successfully edited\n");
        }
        else
        {
            CarCatalogueView.showString("Car editing problem, something went wrong (check err log)\n");
        }
    }

    private int getValidCarId(long carsCount) { //in normal numeration

        int carId;

        while (true)
        {
            carId = readInteger();

            if (carId > 0 && carId <= carsCount)
            {
                break;
            }
            CarCatalogueView.showString(
                    "Wrong number\n" +
                    "Please enter car number you want to deleteCar [1 .. " + carsCount + "]: \n"
            );
        }

        return --carId; //user friendly numeration starts from 1
    }

    private ArrayList<Document> getAllCarsSortedList() {
        FindIterable<Document> allCars = info.getAllCars().sort(Sorts.ascending(CarCatalogueInfo.DF_MODEL));

        ArrayList<Document> allCarsList = new ArrayList<Document>();
        for (Document car : allCars) {
            allCarsList.add(car);
        }

        return allCarsList;
    }

}
