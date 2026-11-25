package gameapp;

public abstract class GameCharacter {
    private String name;
    private int level;
    private int health;
    private int maxHealth;

    public GameCharacter(String name, int level, int maxHealth) {
        this.name = name;

        if (level < 1 || level > 100) {
            System.out.println("Warning: Invalid level for " + name + "! Setting level to 1.");
            this.level = 1;
        } else {
            this.level = level;
        }

        if (maxHealth <= 0) {
            System.out.println("Warning: Invalid maxHealth for " + name + "! Setting maxHealth to 100.");
            this.maxHealth = 100;
        } else {
            this.maxHealth = maxHealth;
        }

        // Yeni karakter tam can ile baslar
        this.health = this.maxHealth;
    }

    // Getter Metotlari
    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }

    // Concrete Metotlar
    public boolean isAlive() {
        return health > 0;
    }

    public int takeDamage(int damage) {
        if (!isAlive() || damage <= 0) {
            return 0;
        }
        int previousHealth = this.health;
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0;
        }
        return previousHealth - this.health; // Gercekte alinan hasar
    }

    public int restoreHealth(int amount) {
        if (!isAlive() || amount <= 0) {
            return 0; // Yenilmis (olu) karakter iyilestirilemez
        }
        int previousHealth = this.health;
        this.health += amount;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
        return this.health - previousHealth; // Gercekte yenilenen can
    }

    // Abstract Metotlar
    public abstract String getCharacterInfo();
    public abstract int calculatePower();
}
