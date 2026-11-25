package gameapp;

import java.util.ArrayList;

public class Team {
    private ArrayList<GameCharacter> members;

    public Team() {
        this.members = new ArrayList<>();
    }

    // a. Karakter ekleme (Ayni isimde karakter engeli)
    public boolean addCharacter(GameCharacter character) {
        if (character == null) return false;
        
        if (findByName(character.getName()) != null) {
            System.out.println("Warning: Character with name '" + character.getName() + "' is already in the team!");
            return false;
        }
        members.add(character);
        System.out.println(character.getName() + " joined the team!");
        return true;
    }

    // b. Karakter silme ve isme gore bulma
    public GameCharacter findByName(String name) {
        for (GameCharacter c : members) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public boolean removeCharacter(String name) {
        GameCharacter found = findByName(name);
        if (found != null) {
            members.remove(found);
            System.out.println(name + " has been removed from the team.");
            return true;
        }
        System.out.println("Character '" + name + "' not found in the team.");
        return false;
    }

    // c. Tum hayattaki Attacker'lar hedefe saldirir
    public void attackAll(GameCharacter target) {
        System.out.println("\n--- TEAM ATTACKS " + target.getName().toUpperCase() + " ---");
        for (GameCharacter member : members) {
            if (member.isAlive() && member instanceof Attacker) {
                ((Attacker) member).attack(target);
            }
        }
    }

    // d. Tum hayattaki Supporter'lar can orani en dusuk hayattaki uyeyi iyilestirir
    public void healTeam() {
        System.out.println("\n--- TEAM HEALING PHASE ---");
        for (GameCharacter member : members) {
            if (member.isAlive() && member instanceof Supporter) {
                GameCharacter lowestRatioMember = getLowestHealthRatioMember();
                if (lowestRatioMember != null) {
                    ((Supporter) member).heal(lowestRatioMember);
                }
            }
        }
    }

    // Yardimci metot: Can orani (health / maxHealth) en dusuk hayattaki uyeyi bulur
    private GameCharacter getLowestHealthRatioMember() {
        GameCharacter lowest = null;
        double minRatio = Double.MAX_VALUE;

        for (GameCharacter member : members) {
            if (member.isAlive()) {
                double ratio = (double) member.getHealth() / member.getMaxHealth();
                if (ratio < minRatio) {
                    minRatio = ratio;
                    lowest = member;
                }
            }
        }
        return lowest;
    }

    // e. Hayattaki uyelerin toplam gucu ve takim goruntuleme
    public int getTotalPower() {
        int total = 0;
        for (GameCharacter member : members) {
            if (member.isAlive()) {
                total += member.calculatePower();
            }
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("\n================ TEAM ROSTER ================");
        for (GameCharacter member : members) {
            System.out.println(member.getCharacterInfo());
        }
        System.out.println("Total Alive Team Power: " + getTotalPower());
        System.out.println("=============================================\n");
    }
}
