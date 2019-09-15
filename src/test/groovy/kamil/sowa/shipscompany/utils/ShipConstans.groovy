package kamil.sowa.shipscompany.utils

import kamil.sowa.shipscompany.cruise.Cruise
import kamil.sowa.shipscompany.ship.Ship

class ShipConstans {
    private static String[] MODELS = ["X-300", "B-220"]
    private static Integer[] SERIAL_NUMBER = [122, 345]

    static Ship.ShipBuilder createShip1(Long id) {
        return new Ship.ShipBuilder()
                .id(id)
                .model(MODELS[0])
                .serialNumber(SERIAL_NUMBER[0])
    }

    static Ship.ShipBuilder createShip2(Long id) {
        return new Ship.ShipBuilder()
                .id(id)
                .model(MODELS[1])
                .serialNumber(SERIAL_NUMBER[1])
    }
}
