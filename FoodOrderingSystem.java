class Kitchen {
    synchronized void prepareFood(String food) {
        System.out.println(food + " order received");

        try {
            System.out.println(food + " is preparing...");
            Thread.sleep(1000);
            System.out.println(food + " is ready!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Food preparation interrupted");
        }
    }
}

class Order extends Thread {
    Kitchen kitchen;
    String food;

    Order(Kitchen kitchen, String food) {
        this.kitchen = kitchen;
        this.food = food;
    }

    public void run() {
        kitchen.prepareFood(food);
    }
}

public class FoodOrderingSystem {
    public static void main(String[] args) {
        Kitchen kitchen = new Kitchen();

        Order o1 = new Order(kitchen, "Burger");
        Order o2 = new Order(kitchen, "Pizza");
        Order o3 = new Order(kitchen, "Sandwich");

        o1.start();
        o2.start();
        o3.start();
    }
}
