package machine;

import java.util.*;


class MissingIngredientException extends Exception {

    private final String ingredient;

    public MissingIngredientException(String ingredient) {
        super("Sorry, not enough " + ingredient + "!");
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }
}



public class CoffeeMachine {

    public enum CoffeeTypes {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);
        final int water;
        final int milk;
        final int beans;
        final int price;

        CoffeeTypes(int water, int milk, int beans, int price) {
            this.price = price;
            this.beans = beans;
            this.milk = milk;
            this.water = water;
        }
    }

    private int milk;
    private int water;
    private int beans;
    private int money;
    private int cups;

    public CoffeeMachine() {
        this.money = 550;
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
    }

    @Override
    public String toString() {
        return String.format("The coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "$%d of money", getWater(), getMilk(), getBeans(), getCups(), getMoney());
    }

    public void buy(CoffeeTypes coffeeType) throws MissingIngredientException {

        int _beans = getBeans() - coffeeType.beans;
        int _milk = getMilk() - coffeeType.milk;
        int _water = getWater() - coffeeType.water;

        int _cups = getCups() - 1;

        if (_beans < 0) {
            throw new MissingIngredientException("beans");
        }

        if(_milk < 0) {
            throw new MissingIngredientException("milk");
        }

        if(_water < 0) {
            throw new MissingIngredientException("water");
        }

        if(_cups < 0) {
            throw new MissingIngredientException("cups");
        }

        setBeans(_beans);
        setCups(_cups);
        setMilk(_milk);
        setWater(_water);
        setMoney( getMoney() + coffeeType.price);
    }

    public int getBeans() {
        return beans;
    }

    public int getCups() {
        return cups;
    }

    public int getMilk() {
        return milk;
    }

    public int getMoney() {
        return money;
    }

    public int getWater() {
        return water;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = scanner.next();
            System.out.println();
            switch (action) {
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    String input = scanner.next();

                    CoffeeTypes selectedCoffee;
                    switch (input) {
                        case "1":
                            selectedCoffee = CoffeeTypes.ESPRESSO;
                            break;
                        case "2":
                            selectedCoffee = CoffeeTypes.LATTE;
                            break;
                        case "3":
                            selectedCoffee = CoffeeTypes.CAPPUCCINO;
                            break;
                        default:
                            continue;
                    }
                    try {
                        coffeeMachine.buy(selectedCoffee);
                        System.out.println("I have enough resources, making you a coffee!");

                    } catch (MissingIngredientException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "fill":
                    System.out.println("Write how many ml of water you want to add: ");
                    coffeeMachine.setWater(coffeeMachine.getWater() + scanner.nextInt());
                    System.out.println("Write how many ml of milk you want to add: ");
                    coffeeMachine.setMilk(coffeeMachine.getMilk() + scanner.nextInt());
                    System.out.println("Write how many grams of coffee beans you want to add: ");
                    coffeeMachine.setBeans(coffeeMachine.getBeans() + scanner.nextInt());
                    System.out.println("Write how many disposable cups of coffee you want to add: ");
                    coffeeMachine.setCups(coffeeMachine.getCups() + scanner.nextInt());
                    break;
                case "take":
                    System.out.printf("I gave you $%d\n", coffeeMachine.getMoney());
                    coffeeMachine.setMoney(0);
                    break;
                case "remaining":
                    System.out.println(coffeeMachine.toString());
                    break;
                case "exit":
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid Action");
                    break;
            }
            System.out.println();
        }
    }
}
