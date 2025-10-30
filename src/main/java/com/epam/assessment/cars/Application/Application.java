package com.epam.assessment.cars.Application;

import com.epam.assessment.cars.Application.model.ElectricModel;
import com.epam.assessment.cars.Application.model.Model;
import com.epam.assessment.cars.Application.model.RegularModel;
import com.epam.assessment.cars.Application.parser.ModelParser;

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