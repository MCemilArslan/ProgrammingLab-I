package gameapp;

public class Gameapp {
    public static void main(String[] args) {
        System.out.println("=== 1. TESTING INVALID INPUTS & CHARACTER CREATION ===");
        // Gecersiz seviye (-5) ve maxHealth (0) testi
        Warrior invalidWarrior = new Warrior("TestBot", -5, 0, 10);
        System.out.println(invalidWarrior.getCharacterInfo());

        // Takim uyelerinin olusturulmasi
        Warrior aragorn = new Warrior("Aragorn", 15, 200, 30);
        Mage gandalf = new Mage("Gandalf", 20, 120, 50);
        Healer elrond = new Healer("Elrond", 18, 140, 40);

        // Guclu dusman karakter
        Warrior golem = new Warrior("Golem", 25, 450, 50);

        System.out.println("\n=== 2. TEAM SETUP & DUPLICATE CHECK ===");
        Team fellowship = new Team();
        fellowship.addCharacter(aragorn);
        fellowship.addCharacter(gandalf);
        fellowship.addCharacter(elrond);

        // Ayni isimle ekleme testi
        Warrior duplicateAragorn = new Warrior("Aragorn", 10, 150, 20);
        fellowship.addCharacter(duplicateAragorn);

        fellowship.displayTeam();
        System.out.println("ENEMY: " + golem.getCharacterInfo());

        // ================= ROUND 1 =================
        System.out.println("\n==================== ROUND 1 ====================");
        fellowship.attackAll(golem);
        
        System.out.println("\n--- ENEMY TURN ---");
        golem.attack(aragorn);
        golem.attack(gandalf);

        fellowship.healTeam();
        fellowship.displayTeam();

        // ================= ROUND 2 =================
        System.out.println("\n==================== ROUND 2 ====================");
        fellowship.attackAll(golem);

        System.out.println("\n--- ENEMY TURN ---");
        golem.attack(elrond);
        golem.attack(aragorn);

        fellowship.healTeam();
        fellowship.displayTeam();
        System.out.println("ENEMY STATUS: " + golem.getCharacterInfo());

        // ================= SEARCH & REMOVE TEST =================
        System.out.println("\n=== 3. SEARCH & REMOVE CHARACTER TEST ===");
        GameCharacter searched = fellowship.findByName("Gandalf");
        if (searched != null) {
            System.out.println("Found Character -> " + searched.getCharacterInfo());
        }

        fellowship.removeCharacter("Elrond");
        fellowship.displayTeam();
    }
}
