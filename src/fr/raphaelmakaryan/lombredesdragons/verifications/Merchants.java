package fr.raphaelmakaryan.lombredesdragons.verifications;

import fr.raphaelmakaryan.lombredesdragons.configurations.*;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Potion;
import fr.raphaelmakaryan.lombredesdragons.configurations.objects.*;
import fr.raphaelmakaryan.lombredesdragons.game.*;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;

import java.sql.Connection;
import java.util.Random;

public class Merchants extends Admin {
    Tools tools = new Tools();

    /**
     * Function when the player is on the cashier merchants
     *
     * @param menu
     * @param boardClass
     * @param user
     * @param game
     * @param boardInt
     * @param caseNumber
     * @param database
     * @param level
     */
    public void haveMerchants(Menu menu, Board boardClass, User user, Game game, int[] boardInt, int caseNumber, Database database, Level level) {
        menu.cellMerchants(boardClass, user, game, boardInt, caseNumber, database, level, this);
    }

    /**
     * Function to retrieve the name and price of the player’s weapon to sell it
     *
     * @param user
     * @return
     */
    public String[][] getWeapon(User user) {
        Character character = user.getCharacterPlayer();
        OffensiveEquipment weapon = character.getOffensiveEquipment();
        if (weapon != null) {
            String name = weapon.getName();
            String price = String.valueOf(weapon.getValuePrice());
            return new String[][]{new String[]{name}, new String[]{price}};
        }
        return null;
    }

    /**
     * Function to retrieve the player’s name and potion price to sell it
     *
     * @param user
     * @return
     */
    public String[][] getPotion(User user) {
        Character character = user.getCharacterPlayer();
        DefensiveEquipment potion = character.getDefensiveEquipment();
        if (potion != null) {
            String name = potion.getName();
            String price = String.valueOf(potion.getValuePrice());
            return new String[][]{new String[]{name}, new String[]{price}};
        }
        return null;
    }

    /**
     * Function if the player has chosen to sell a weapon
     *
     * @param user
     * @param menu
     * @param database
     */
    public void sellWeapon(User user, Menu menu, Database database) {
        Character character = user.getCharacterPlayer();
        OffensiveEquipment weapon = character.getOffensiveEquipment();
        String name = weapon.getName();
        int price = weapon.getValuePrice();
        int money = character.getMoney();
        character.setMoney(money + price);
        character.setOffensiveEquipment(null);
        database.addOffensiveEquipment(user, null);
        menu.playerSellObjets(name);
    }

    /**
     * Function if the player chose to sell a potion
     * @param user
     * @param menu
     * @param database
     */
    public void sellPotion(User user, Menu menu, Database database) {
        Character character = user.getCharacterPlayer();
        DefensiveEquipment potion = character.getDefensiveEquipment();
        String name = potion.getName();
        int price = potion.getValuePrice();
        int money = character.getMoney();
        character.setMoney(money + price);
        character.setDefensiveEquipment(null);
        database.addDefensiveEquipment(user, null);
        menu.playerSellObjets(name);
    }

    /**
     * Random function to choose items for the player to buy
     * @param user
     * @return
     */
    public String[][] articleBuy(User user) {
        Random rand = new Random();
        int randomArticle = rand.nextInt(0, boxValue.length);
        return whatObject(boxValue[randomArticle]);
    }

    /**
     * Function to retrieve the information from the random object
     * @param idObject
     * @return
     */
    public String[][] whatObject(int idObject) {
        int objet = idObject - 300;
        String[][] response;
        if (objet == 0) {
            Potion potion = new StandardPotion();
            String name = potion.getName();
            String price = String.valueOf(potion.getValuePrice());
            response = new String[][]{{name}, {price}};
            return response;
        } else if (objet == 1) {
            Potion potion = new BigPotion();
            String name = potion.getName();
            String price = String.valueOf(potion.getValuePrice());
            response = new String[][]{{name}, {price}};
            return response;
        } else if (objet == 10) {
            Flash weapon = new Flash();
            String name = weapon.getName();
            String price = String.valueOf(weapon.getValuePrice());
            response = new String[][]{{name}, {price}};
            return response;
        } else if (objet == 11) {
            Fireball weapon = new Fireball();
            String name = weapon.getName();
            String price = String.valueOf(weapon.getValuePrice());
            response = new String[][]{{name}, {price}};
            return response;
        } else if (objet == 12) {
            Invisibility weapon = new Invisibility();
            String name = weapon.getName();
            String price = String.valueOf(weapon.getValuePrice());
            response = new String[][]{{name}, {price}};
            return response;
        } else if (objet == 20) {
            Mace weapon = new Mace();
            String name = weapon.getName();
            String price = String.valueOf(weapon.getValuePrice());
            response = new String[][]{{name}, {price}};
            return response;
        } else if (objet == 21) {
            Sword weapon = new Sword();
            String name = weapon.getName();
            String price = String.valueOf(weapon.getValuePrice());
            response = new String[][]{{name}, {price}};
            return response;
        } else if (objet == 22) {
            Bow weapon = new Bow();
            String name = weapon.getName();
            String price = String.valueOf(weapon.getValuePrice());
            response = new String[][]{{name}, {price}};
            return response;
        } else if (objet == 30) {
            Thunderclap weapon = new Thunderclap();
            String name = weapon.getName();
            String price = String.valueOf(weapon.getValuePrice());
            response = new String[][]{{name}, {price}};
            return response;
        } else {
            response = new String[][]{{"Unknown"}, {"ERROR"}};
            return response;
        }
    }

    /**
     * Verification that if the player has really bought
     * @param user
     * @param article
     * @return
     */
    public boolean processPurchase(User user, String[][] article) {
        Character character = user.getCharacterPlayer();
        int prix = Integer.parseInt(article[1][0]);
        String nom = article[0][0];
        if (character.getMoney() >= prix) {
            tools.clearLine();
            if (nom.equals("BUY")) {
                System.out.println("❌ Vous avez deja acheter précedement cet objet !");
                tools.clearLine();
                return false;
            }
            boolean canHave = canHaveObject(user, nom);
            if (canHave) {
                character.setMoney(character.getMoney() - prix);
                addToInventory(character, nom);
                System.out.println("✅ Vous avez acheté : " + nom + " pour " + prix + " !");
                tools.clearLine();
                return true;
            }
            System.out.println("❌ Vous ne pouvez pas prendre cet objet selon votre classe !");
            tools.clearLine();
            return false;
        } else {
            System.out.println("❌ Vous n'avez pas assez d'argent !");
            tools.clearLine();
            return false;
        }
    }

    /**
     * Add in the player’s inventory
     * @param character
     * @param itemName
     */
    public void addToInventory(Character character, String itemName) {
        switch (itemName) {
            case "Standard Potion" -> character.setDefensiveEquipment(new StandardPotion());
            case "Big Potion" -> character.setDefensiveEquipment(new BigPotion());
            case "Flash" -> character.setOffensiveEquipment(new Flash());
            case "Fireball" -> character.setOffensiveEquipment(new Fireball());
            case "Invisibility" -> character.setOffensiveEquipment(new Invisibility());
            case "Mace" -> character.setOffensiveEquipment(new Mace());
            case "Sword" -> character.setOffensiveEquipment(new Sword());
            case "Bow" -> character.setOffensiveEquipment(new Bow());
            case "Thunderclap" -> character.setOffensiveEquipment(new Thunderclap());
        }
    }

    /**
     * Check if the player according to their class can take it in their inventory
     * @param user
     * @param name
     * @return
     */
    public boolean canHaveObject(User user, String name) {
        Character character = user.getCharacterPlayer();
        String typePlayer = character.getType();

        if (typePlayer.equals("Warrior")) {
            switch (name) {
                case "Standard Potion", "Big Potion", "Mace", "Sword", "Bow", "Thunderclap" -> {
                    return true;
                }
                default -> {
                    return false;
                }
            }
        } else if (typePlayer.equals("Wizard")) {
            switch (name) {
                case "Standard Potion", "Big Potion", "Flash", "Fireball", "Invisibility", "Thunderclap" -> {
                    return true;
                }
                default -> {
                    return false;
                }
            }
        }
        return false;
    }
}