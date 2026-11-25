package gameapp;

public class Warrior extends GameCharacter implements Attacker {
    private int armor;

    public Warrior(String name, int level, int maxHealth, int armor) {
        super(name, level, maxHealth);
        this.armor = Math.max(0, armor);
    }

    public int getArmor() {
        return armor;
    }

    @Override
    public int calculatePower() {
        return (getLevel() * 10) + (armor * 2);
    }

    @Override
    public int takeDamage(int damage) {
        if (!isAlive()) {
            return 0;
        }
        // Zirhin yarisi hasardan dusulur, ancak en az 1 hasar alinir
        int reducedDamage = damage - (armor / 2);
        if (reducedDamage < 1) {
            reducedDamage = 1;
        }
        return super.takeDamage(reducedDamage);
    }

    @Override
    public int attack(GameCharacter target) {
        if (!this.isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        int damage = calculatePower() / 4;
        int actualDamage = target.takeDamage(damage);
        System.out.println(getName() + " (Warrior) attacks " + target.getName() + " for " + actualDamage + " damage!");
        return actualDamage;
    }

    @Override
    public String getCharacterInfo() {
        return "[Warrior] Name: " + getName() + 
               " | Level: " + getLevel() + 
               " | HP: " + getHealth() + "/" + getMaxHealth() + 
               " | Armor: " + armor + 
               " | Power: " + calculatePower() +
               " | Status: " + (isAlive() ? "Alive" : "Defeated");
    }
}
