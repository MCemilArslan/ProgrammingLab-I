package gameapp;

public class Mage extends GameCharacter implements Attacker, Supporter {
    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = Math.max(0, mana);
    }

    public int getMana() {
        return mana;
    }

    @Override
    public int calculatePower() {
        return (getLevel() * 8) + mana;
    }

    @Override
    public int attack(GameCharacter target) {
        if (!this.isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        if (mana < 20) {
            System.out.println(getName() + " (Mage) does not have enough mana to attack! (Required: 20, Current: " + mana + ")");
            return 0;
        }
        // Guc mana harcanmadan once hesaplanir
        int damage = calculatePower() / 3;
        mana -= 20;
        
        int actualDamage = target.takeDamage(damage);
        System.out.println(getName() + " (Mage) casts fireball on " + target.getName() + " for " + actualDamage + " damage! (Remaining Mana: " + mana + ")");
        return actualDamage;
    }

    @Override
    public int heal(GameCharacter target) {
        if (!this.isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        if (mana < 15) {
            System.out.println(getName() + " (Mage) does not have enough mana to heal! (Required: 15, Current: " + mana + ")");
            return 0;
        }
        mana -= 15;
        int healAmount = getLevel() * 2;
        int actualHeal = target.restoreHealth(healAmount);
        System.out.println(getName() + " (Mage) heals " + target.getName() + " for " + actualHeal + " HP! (Remaining Mana: " + mana + ")");
        return actualHeal;
    }

    @Override
    public String getCharacterInfo() {
        return "[Mage]    Name: " + getName() + 
               " | Level: " + getLevel() + 
               " | HP: " + getHealth() + "/" + getMaxHealth() + 
               " | Mana: " + mana + 
               " | Power: " + calculatePower() +
               " | Status: " + (isAlive() ? "Alive" : "Defeated");
    }
}