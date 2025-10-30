
import collection.ModelsCollection;
import model.ElectricModel;
import model.Model;
import model.RegularModel;
import perser.ModelParser;

public class Application {

    public static void main(String[] args) {
        try {

            String electricLine = "Electric,Tesla,Model 3,300,75,18";
            Model electricModel = ModelParser.parse(electricLine);
            System.out.println("Is ElectricModel: " + (electricModel instanceof ElectricModel));
            String regularLine = "Regular,Opel,Astra,81,1199,5.2";
            Model regularModel = ModelParser.parse(regularLine);
            System.out.println("Is RegularModel: " + (regularModel instanceof RegularModel));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}