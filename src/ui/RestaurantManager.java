package ui;

import Persistence.Savable;
import model.Customer;
import model.Reservation;
import model.Table;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class RestaurantManager implements Savable {
    HashMap<Integer, List<Reservation>> reservations;

//    initialize variable
     public RestaurantManager() {
         reservations = new HashMap<>();
         for (int i = 0; i < 24; i++) {
             reservations.put(i, new LinkedList<Reservation>());
         }
    }

//   corresponding table at time is added to the reservations hashmap
//    updates ui
    public void addReservation(Integer time, TableDisplay tableDisplay, Customer customer) {
         Boolean contains = false;
         if (time < 24) {
             List<Reservation> reservs = reservations.get(time);
             if (!reservs.isEmpty()) {
                 for (Reservation r : reservs) {
                     if (r.getTable().equals(tableDisplay)) {
                         contains = true;
                     }
                 }
             }
             if (contains.equals(false)) {
                 reservations.get(time).add(new Reservation(tableDisplay.getTable(), customer));
             }
         }
    }

//    removes corresponding reservations
//    updates ui
    public void removeReservation(Integer time, Table table) {
        List<Reservation> reservs = reservations.get(time);
        for (Reservation r: reservs) {
            if (r.getTable().equals(table)) {
                reservations.get(time).remove(r);
            }
        }
    }

    public List<Reservation> getReservations(Integer time) {
         if (time <= 24) {
             return reservations.get(time);
         }
         return null;
    }
  
      @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(reservations);
    }
}
