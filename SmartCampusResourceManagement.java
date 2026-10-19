// Smart Campus Resource and Energy Management System

interface EnergyMonitor {
    String getResourceName();
    double getEnergyAmount();
    String getEnergyType();

    // Default method (same signature as in Controllable -> conflict)
    default void showStatus() {
        System.out.println("Energy Monitor: reporting energy data");
    }
}

interface Controllable {
    void turnOn();
    void turnOff();

    // Default method (same signature as in EnergyMonitor -> conflict)
    default void showStatus() {
        System.out.println("Controller: ready for operation");
    }
}

interface CostCalculable {
    double calculateCost();
}

interface RenewableEnergy {
    double getEnergyGenerated();
    double calculateSavings();
}


// Smart Classroom
class SmartClassroom implements EnergyMonitor, Controllable, CostCalculable {
    private double energyConsumed;
    private double tariff;              // Rs. per kWh
    private boolean isOn = false;

    public SmartClassroom(double energyConsumed, double tariff) {
        this.energyConsumed = energyConsumed;
        this.tariff = tariff;
    }

    public String getResourceName() {
        return "Smart Classroom";
    }

    public double getEnergyAmount() {
        return energyConsumed;
    }

    public String getEnergyType() {
        return "consumed";
    }

    public double calculateCost() {
        return energyConsumed * tariff;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    // Resolves the conflicting default methods by calling both versions
    public void showStatus() {
        EnergyMonitor.super.showStatus();
        Controllable.super.showStatus();
        System.out.println("Operational Status: " + (isOn ? "ON" : "OFF"));
    }
}


// Computer Laboratory
class ComputerLaboratory implements EnergyMonitor, Controllable, CostCalculable {
    private double energyConsumed;
    private double tariff;              // Rs. per kWh
    private boolean isOn = false;

    public ComputerLaboratory(double energyConsumed, double tariff) {
        this.energyConsumed = energyConsumed;
        this.tariff = tariff;
    }

    public String getResourceName() {
        return "Computer Laboratory";
    }

    public double getEnergyAmount() {
        return energyConsumed;
    }

    public String getEnergyType() {
        return "consumed";
    }

    public double calculateCost() {
        return energyConsumed * tariff;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    // Resolves the conflicting default methods by calling both versions
    public void showStatus() {
        EnergyMonitor.super.showStatus();
        Controllable.super.showStatus();
        System.out.println("Operational Status: " + (isOn ? "ON" : "OFF"));
    }
}


// EV Charging Station
class EVChargingStation implements EnergyMonitor, Controllable, CostCalculable {
    private double energyConsumed;
    private double tariff;              // Rs. per kWh
    private boolean isOn = false;

    public EVChargingStation(double energyConsumed, double tariff) {
        this.energyConsumed = energyConsumed;
        this.tariff = tariff;
    }

    public String getResourceName() {
        return "EV Charging Station";
    }

    public double getEnergyAmount() {
        return energyConsumed;
    }

    public String getEnergyType() {
        return "consumed";
    }

    public double calculateCost() {
        return energyConsumed * tariff;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    // Resolves the conflicting default methods by calling both versions
    public void showStatus() {
        EnergyMonitor.super.showStatus();
        Controllable.super.showStatus();
        System.out.println("Operational Status: " + (isOn ? "CHARGING" : "IDLE"));
    }
}


// Solar Power Unit (no conflict here, it only implements EnergyMonitor's default)
class SolarPowerUnit implements EnergyMonitor, RenewableEnergy {
    private double energyGenerated;
    private double avoidedCost;         // Rs. per kWh

    public SolarPowerUnit(double energyGenerated, double avoidedCost) {
        this.energyGenerated = energyGenerated;
        this.avoidedCost = avoidedCost;
    }

    public String getResourceName() {
        return "Solar Power Unit";
    }

    public double getEnergyAmount() {
        return energyGenerated;
    }

    public String getEnergyType() {
        return "generated";
    }

    public double getEnergyGenerated() {
        return energyGenerated;
    }

    public double calculateSavings() {
        return energyGenerated * avoidedCost;
    }

    public void showStatus() {
        EnergyMonitor.super.showStatus();
        System.out.println("Operational Status: GENERATING");
    }
}


// Main class
public class SmartCampusResourceManagement {

    public static void main(String[] args) {

        System.out.println("===== SMART CAMPUS RESOURCE AND ENERGY MANAGEMENT SYSTEM =====");

        // All resources are handled through the EnergyMonitor interface reference
        EnergyMonitor[] resources = {
            new SmartClassroom(12.5, 8),
            new ComputerLaboratory(35, 8),
            new EVChargingStation(20, 10),
            new SolarPowerUnit(50, 8)
        };

        for (EnergyMonitor resource : resources) {
            System.out.println();

            System.out.println("Resource Name: " + resource.getResourceName());

            System.out.printf("Energy: %.1f kWh %s%n",
                    resource.getEnergyAmount(), resource.getEnergyType());

            // Switch on the resources that can be controlled
            if (resource instanceof Controllable) {
                Controllable control = (Controllable) resource;
                control.turnOn();
            }

            resource.showStatus();

            // Calculate operating cost where applicable
            if (resource instanceof CostCalculable) {
                CostCalculable cost = (CostCalculable) resource;
                System.out.printf("Operating Cost: Rs. %.2f%n", cost.calculateCost());
            }

            // Report renewable generation and savings
            if (resource instanceof RenewableEnergy) {
                RenewableEnergy renewable = (RenewableEnergy) resource;
                System.out.printf("Energy Generated: %.1f kWh%n", renewable.getEnergyGenerated());
                System.out.printf("Energy Savings: Rs. %.2f%n", renewable.calculateSavings());
            }
        }

        // Switch off the controllable resources at the end
        System.out.println();
        System.out.println("----- Switching off controllable resources -----");
        for (EnergyMonitor resource : resources) {
            if (resource instanceof Controllable) {
                Controllable control = (Controllable) resource;
                control.turnOff();
                System.out.println(resource.getResourceName() + " is now switched off");
            }
        }

        System.out.println();
        System.out.println("All campus resources processed successfully.");
    }
}