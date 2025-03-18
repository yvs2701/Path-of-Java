package com.yashv.practice;

interface Car {
    String getMakeModel();

    default String getMessage() {
        return "I am a car";
    }
}

interface SprotsUtility {
    int getSeats();
}

class CarImpl implements Car, SprotsUtility {
    public String carName;
    public int carSeats;

    CarImpl(String carName, int carSeats) {
        this.carName = carName;
        this.carSeats = carSeats;
    }

    @Override
    public String getMakeModel() {
        return carName;
    }

    @Override
    public int getSeats() {
        return carSeats;
    }
}

public class MultipleInheritance {
    public static void main(String[] args) {
        SprotsUtility car = new CarImpl("Toyota", 5);
        // System.out.println(car.getMakeModel()); // compilation error: "The method getMakeModel() is undefined for the type SprotsUtility"
        // System.out.println(car.getMessage()); // compilation errors: "The method getMessage() is undefined for the type SprotsUtility"
        System.out.println(car.getSeats());
    }
}
