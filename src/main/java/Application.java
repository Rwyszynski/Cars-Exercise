import car.ElectricCar;
import collection.ModelsCollection;
import model.ElectricModel;
import model.Model;

public class Application {
    public static void main(String[] args) {
        ModelsCollection collection = new ModelsCollection();
        collection.readFromFile("src/main/resources/models.csv");

        System.out.println("All models:");
        collection.getModels().forEach(System.out::println);


    }
}