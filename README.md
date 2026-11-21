# Smart Campus Resource and Energy Management (Java)

Java OOP demonstration featuring a smart classroom, computer lab, EV charging station and solar power unit.

## Run
```bash
javac SmartCampusResourceManagement.java
java SmartCampusResourceManagement
```

## Topics demonstrated
- Interfaces: `EnergyMonitor`, `Controllable`, `CostCalculable`, `RenewableEnergy`
- Interface default-method conflict resolution
- Polymorphism via an `EnergyMonitor[]`
- Energy usage, gross operating costs, solar-generation savings and resource switching

The program uses sample values and now prints aggregate campus consumption, solar generation and operating cost. It does not represent live hardware monitoring.
