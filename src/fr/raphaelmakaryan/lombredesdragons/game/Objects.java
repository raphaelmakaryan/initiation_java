package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.*;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Potion;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Spell;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Weapon;
import fr.raphaelmakaryan.lombredesdragons.configurations.objects.*;
import fr.raphaelmakaryan.lombredesdragons.tools.Tools;

import java.sql.Connection;

public class Objects extends Admin {
    public Weapon isWeapon;
    public Spell isSpell;
    public Potion isPotion;
    Tools tools = new Tools();

    /**
     * Opening of a box by the player
     *
     * @param boardClass
     * @param user
     * @param boardInt
     * @param caseNumber
     * @param menu
     * @param game
     */
    public void openBox(Board boardClass, User user, int[] boardInt, int caseNumber, Menu menu, Game game, Connection connection, Database database, Level level) {
        Character character = user.getCharacterPlayer();
        int cellPlayer = boardClass.getBoard()[caseNumber];
        String[][][] whatType = whatObject(cellPlayer);
        menu.displayObjectOnBox(whatType[0][0][0]);
        boolean canHave = verificationIfCanUse(whatType, character);
        tools.setTimeout(1);
        if (canHave) {
            boardClass.setNewCellPlayer(boardInt, caseNumber, false, connection, database, user, level);
            displayToPlayer(menu, whatType, boardClass, user, game, connection, database, level);
        }
        tools.setTimeout(1);
        boardClass.setNewCellPlayer(boardInt, caseNumber, false, connection, database, user, level);
        menu.displayCantGetObjectOpenBox(boardClass, user, game, connection, database, level);
    }

    /**
     * Verification via the box’s case and its ID to determine which object
     *
     * @param idObject
     * @return
     */
    public String[][][] whatObject(int idObject) {
        int objet = idObject - 300;
        String[][][] response;
        if (debugForceBox != 0) {
            objet = debugForceBox;
        }
        if (objet == 0) {
            this.isPotion = new StandardPotion();
            response = new String[][][]{{new String[]{"Standard Potion"}}, {new String[]{"ALL"}}, {new String[]{"DefensiveEquipment"}}};
            return response;
        } else if (objet == 1) {
            this.isPotion = new BigPotion();
            response = new String[][][]{{new String[]{"Big Potion"}}, {new String[]{"ALL"}}, {new String[]{"DefensiveEquipment"}}};
            return response;
        } else if (objet == 10) {
            this.isSpell = new Flash();
            response = new String[][][]{{new String[]{"Flash"}}, {new String[]{"Wizard"}}, {new String[]{"OffensiveEquipment"}}};
            return response;
        } else if (objet == 11) {
            this.isSpell = new Fireball();
            response = new String[][][]{{new String[]{"Fireball"}}, {new String[]{"Wizard"}}, {new String[]{"OffensiveEquipment"}}};
            return response;
        } else if (objet == 12) {
            this.isWeapon = new Invisibility();
            response = new String[][][]{{new String[]{"Invisibility"}}, {new String[]{"Wizard"}}, {new String[]{"OffensiveEquipment"}}};
            return response;
        } else if (objet == 20) {
            this.isWeapon = new Mace();
            response = new String[][][]{{new String[]{"Mace"}}, {new String[]{"Warrior"}}, {new String[]{"OffensiveEquipment"}}};
            return response;
        } else if (objet == 21) {
            this.isWeapon = new Sword();
            response = new String[][][]{{new String[]{"Sword"}}, {new String[]{"Warrior"}}, {new String[]{"OffensiveEquipment"}}};
            return response;
        } else if (objet == 22) {
            this.isWeapon = new Bow();
            response = new String[][][]{{new String[]{"Bow"}}, {new String[]{"Warrior"}}, {new String[]{"OffensiveEquipment"}}};
            return response;
        } else if (objet == 30) {
            this.isWeapon = new Thunderclap();
            response = new String[][][]{{new String[]{"Thunderclap"}}, {new String[]{"ALL"}}, {new String[]{"OffensiveEquipment"}}};
            return response;
        } else {
            response = new String[][][]{{new String[]{"Unknown"}}, {new String[]{"ERROR"}}, {new String[]{"ERROR"}}};
            return response;
        }
    }

    /**
     * Display to player
     *
     * @param menu
     * @param allData
     * @param boardClass
     * @param user
     * @param game
     */
    public void displayToPlayer(Menu menu, String[][][] allData, Board boardClass, User user, Game game, Connection connection, Database database, Level level) {
        menu.displayObjectOpenBox(boardClass, user, game, this, allData, connection, database, level);
    }

    /**
     * Verification if the player can take it according to their class
     *
     * @param object
     * @param character
     * @return
     */
    public boolean verificationIfCanUse(String[][][] object, Character character) {
        if (character.getType().equals(object[1][0][0]) || object[1][0][0].equals("ALL")) {
            return true;
        }
        return false;
    }

    /**
     * Verification if the player already has an item in their inventory and returns whether or not they can take it
     *
     * @param user
     * @param object
     * @param menu
     * @param boardClass
     * @param game
     * @return
     */
    public boolean haveAlreadyAObject(User user, String[][][] object, Menu menu, Board boardClass, Game game) {
        Character character = user.getCharacterPlayer();
        if (object[2][0][0].equals("DefensiveEquipment")) {
            DefensiveEquipment playerDefensiveEquipment = character.getDefensiveEquipment();
            if (playerDefensiveEquipment != null) {
                int levelDefense = playerDefensiveEquipment.getLevelDefense();
                int newLevelDefense = this.isPotion.getLevelDefense();
                if (newLevelDefense > levelDefense) {
                    return false;
                } else if (newLevelDefense == levelDefense) {
                    return true;
                }
                return true;
            }
            return false;
        } else if (object[2][0][0].equals("OffensiveEquipment")) {
            OffensiveEquipment playerOffensiveEquipment = character.getOffensiveEquipment();
            if (playerOffensiveEquipment != null) {
                if (this.isSpell != null) {
                    int levelAttack = playerOffensiveEquipment.getLevelAttack();
                    int newLevelAttack = this.isSpell.getLevelAttack();
                    if (newLevelAttack > levelAttack) {
                        return false;
                    } else if (newLevelAttack == levelAttack) {
                        return true;
                    }
                } else if (this.isWeapon != null) {
                    int levelAttack = playerOffensiveEquipment.getLevelAttack();
                    int newLevelAttack = this.isWeapon.getLevelAttack();
                    if (newLevelAttack > levelAttack) {
                        return false;
                    } else if (newLevelAttack == levelAttack) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    /**
     * Verification if possible to give it to the player
     *
     * @param user
     * @param object
     * @param menu
     * @param boardClass
     * @param game
     */
    public void verificationGiveObjectToPlayer(User user, String[][][] object, Menu menu, Board boardClass, Game game, Connection connection, Database database, Level level) {
        Character character = user.getCharacterPlayer();
        boolean playerHaveAlready = haveAlreadyAObject(user, object, menu, boardClass, game);
        if (!playerHaveAlready) {
            addObjectToPlayer(object, character, connection, database, user);
            menu.displayObjectAddToPlayer(boardClass, user, game, object[0][0][0], connection, database, level);
        }
        menu.displayObjectCantAddToPlayer(boardClass, user, game, object[0][0][0], connection, database, level);
    }

    /**
     * Add the object to the player
     *
     * @param object
     * @param character
     */
    public void addObjectToPlayer(String[][][] object, Character character, Connection connection, Database database, User user) {
        if (object[2][0][0].equals("DefensiveEquipment")) {
            character.setDefensiveEquipment(this.isPotion);
            database.addDefensiveEquipment(connection, user, this.isPotion);
        } else if (object[2][0][0].equals("OffensiveEquipment")) {
            if (this.isSpell != null) {
                character.setOffensiveEquipment(this.isSpell);
                database.addOffensiveEquipment(connection, user, this.isSpell);
            } else if (this.isWeapon != null) {
                character.setOffensiveEquipment(this.isWeapon);
                database.addOffensiveEquipment(connection, user, this.isWeapon);
            }

        }
    }
}
