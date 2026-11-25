package gameapp;

public class Healer extends GameCharacter implements Supporter {
    private int healAmount;

    public Healer(String name, int level, int maxHealth, int healAmount) {
        super(name, level, maxHealth);
        this.healAmount = Math.max(0, healAmount);
    }

    public int getHealAmount() {
        return healAmount;
    }

    @Override
    public int calculatePower() {
        return (getLevel() * 5) + healAmount;
    }

    @Override
    public int heal(GameCharacter target) {
        if (!this.isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        int totalHeal = healAmount + getLevel();
        int actualHeal = target.restoreHealth(totalHeal);
        System.out.println(getName() + " (Healer) heals " + target.getName() + " for " + actualHeal + " HP!");
        return actualHeal;
    }

    @Override
    public String getCharacterInfo() {
        return "[Healer]  Name: " + getName() + 
               " | Level: " + getLevel() + 
               " | HP: " + getHealth() + "/" + getMaxHealth() + 
               " | HealAmount: " + healAmount + 
               " | Power: " + calculatePower() +
               " | Status: " + (isAlive() ? "Alive" : "Defeated");
    }
}
