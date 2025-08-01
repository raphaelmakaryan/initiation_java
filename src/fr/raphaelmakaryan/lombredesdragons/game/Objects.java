package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.Character;
import fr.raphaelmakaryan.lombredesdragons.configurations.DefensiveEquipment;
import fr.raphaelmakaryan.lombredesdragons.configurations.OffensiveEquipment;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Potion;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Spell;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Weapon;
import fr.raphaelmakaryan.lombredesdragons.configurations.objects.*;

public class Objects {
    public Weapon isWeapon;
    public Spell isSpell;
    public Potion isPotion;

    public void openBox(Board boardClass, User user, int[] boardInt, int caseNumber, Menu menu, Game game) {
        Character character = user.getCharacterPlayer();
        int cellPlayer = boardClass.getBoard()[caseNumber];
        String[][][] whatType = whatObject(cellPlayer);
        menu.displayObjectOnBox(whatType[0][0][0]);
        boolean canHave = verificationIfCanUse(whatType, character);
        if (canHave) {
            boardClass.setNewCellPlayer(boardInt, caseNumber);
            displayToPlayer(menu, whatType, boardClass, user, game);
        }
        boardClass.setNewCellPlayer(boardInt, caseNumber);
        menu.displayCantGetObjectOpenBox(boardClass, user, game);
    }

    public String[][][] whatObject(int idObject) {
        int objet = idObject - 300;
        String[][][] response;
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
        } else if (objet == 20) {
            this.isWeapon = new Mace();
            response = new String[][][]{{new String[]{"Mace"}}, {new String[]{"Warrior"}}, {new String[]{"OffensiveEquipment"}}};
            return response;
        } else if (objet == 21) {
            this.isWeapon = new Sword();
            response = new String[][][]{{new String[]{"Sword"}}, {new String[]{"Warrior"}}, {new String[]{"OffensiveEquipment"}}};
            return response;
        } else {
            response = new String[][][]{{new String[]{"Unknown"}}, {new String[]{"ERROR"}}, {new String[]{"ERROR"}}};
            return response;
        }
    }

    public void displayToPlayer(Menu menu, String[][][] allData, Board boardClass, User user, Game game) {
        menu.displayObjectOpenBox(boardClass, user, game, this, allData);
    }

    public boolean verificationIfCanUse(String[][][] object, Character character) {
        if (character.getType().equals(object[1][0][0]) || object[1][0][0].equals("ALL")) {
            return true;
        }
        return false;
    }


    public boolean haveAlreadyAObject(User user, String[][][] object, Menu menu, Board boardClass, Game game) {
        Character character = user.getCharacterPlayer();
        if (object[2][0][0].equals("DefensiveEquipment")) {
            DefensiveEquipment playerDefensiveEquipment = character.getDefensiveEquipment();
            if (playerDefensiveEquipment != null) {
                int levelDefense = playerDefensiveEquipment.getLevelDefense();
                int newLevelDefense = this.isPotion.getLevelDefense();
                if (newLevelDefense > levelDefense) {
                    return false;
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
                    }
                } else if (this.isWeapon != null) {
                    int levelAttack = playerOffensiveEquipment.getLevelAttack();
                    int newLevelAttack = this.isWeapon.getLevelAttack();
                    if (newLevelAttack > levelAttack) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public void verificationGiveObjectToPlayer(User user, String[][][] object, Menu menu, Board boardClass, Game game) {
        Character character = user.getCharacterPlayer();
        boolean playerHaveAlready = haveAlreadyAObject(user, object, menu, boardClass, game);
        if (!playerHaveAlready) {
            addObjectToPlayer(object, character, menu);
            menu.displayObjectAddToPlayer(boardClass, user, game, object[0][0][0]);
        }
        menu.displayObjectCantAddToPlayer(boardClass, user, game, object[0][0][0]);
    }

    public void addObjectToPlayer(String[][][] object, Character character, Menu menu) {
        if (object[2][0][0].equals("DefensiveEquipment")) {
            character.setDefensiveEquipment(this.isPotion);
        } else if (object[2][0][0].equals("OffensiveEquipment")) {
            if (this.isSpell != null) {
                character.setOffensiveEquipment(this.isSpell);
            } else if (this.isWeapon != null) {
                character.setOffensiveEquipment(this.isWeapon);
            }
        }
    }

}
