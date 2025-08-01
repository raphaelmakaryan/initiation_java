package fr.raphaelmakaryan.lombredesdragons.game;

import fr.raphaelmakaryan.lombredesdragons.configurations.Board;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Potion;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Spell;
import fr.raphaelmakaryan.lombredesdragons.configurations.equipments.Weapon;
import fr.raphaelmakaryan.lombredesdragons.configurations.objects.*;

public class Objects {
    private Weapon isWeapon;
    private Spell isSpell;
    private Potion isPotion;

    public void openBox(Board boardClass, User user, int[] boardInt, int caseNumber, Menu menu, Game game) {
        int cellPlayer = boardClass.getBoard()[caseNumber];
        String whatType = whatObject(cellPlayer);
        displayToPlayer(menu, whatType, boardClass, user, game);
        boardClass.setNewCellPlayer(boardInt, caseNumber);
    }

    public String whatObject(int idObject) {
        int objet = idObject - 300;
        if (objet == 0) {
            this.isPotion = new StandardPotion();
            return "StandardPotion";
        } else if (objet == 1) {
            this.isPotion = new BigPotion();
            return "BigPotion";
        } else if (objet == 10) {
            this.isSpell = new Flash();
            return "Flash";
        } else if (objet == 11) {
            this.isSpell = new Fireball();
            return "Fireball";
        } else if (objet == 20) {
            this.isWeapon = new Mace();
            return "Mace";
        } else if (objet == 21) {
            this.isWeapon = new Sword();
            return "Sword";
        } else {
            return "Unknown";
        }
    }

    public void displayToPlayer(Menu menu, String type, Board boardClass, User user, Game game) {
        menu.displayObjectOpenBox(type, boardClass, user, game);
    }
}
