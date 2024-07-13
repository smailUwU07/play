import java.util.Random;

public class Main {
        public static int bossHealth = 700;
        public static int bossDamage = 50;
        public static String bossDefence;
        public static int[] heroesHealth = new int[]{260, 270, 250, 280,};
        public static int[] heroesDamage = new int[]{20, 15, 10,0};
        public static String[] heroesAttackType = new String[]{"Physical", "Magical", "Kinetic","Medic"};
        public static int roundNumber = 0;

    public Main() {
        }

        public static void main(String[] args) {
            printStatistics();

            while(!isGameOver()) {
                playRound();
            }

        }

        public static boolean isGameOver() {
            if (bossHealth <= 0) {
                System.out.println("Heroes won!!!");
                return true;
            } else {
                boolean allHeroesDead = true;


                for (int i = 0; i < heroesHealth.length; ++i) {
                    if (heroesHealth[i] > 0) {
                        allHeroesDead = false;
                        break;
                    }
                }

                if (allHeroesDead) {
                    System.out.println("Boss won!!!");
                    return true;
                } else {
                    return false;
                }
            }
        }

        public static void playRound() {
            ++roundNumber;
            chooseDefence();
            bossHits();
            heroesHit();
            medicHeals();


            printStatistics();
        }

        public static void printStatistics() {
            int var10001 = roundNumber;
            System.out.println("ROUND " + var10001 + " -------------------");
            System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " + (bossDefence == null ? "No Defence" : bossDefence));

            for(int i = 0; i < heroesHealth.length; ++i) {
                String var1 = heroesAttackType[i];
                System.out.println(var1 + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
            }

        }

        public static void chooseDefence() {
            Random random = new Random();
            int randomIndex = random.nextInt(heroesAttackType.length);
            bossDefence = heroesAttackType[randomIndex];
        }

        public static void bossHits() {
            for(int i = 0; i < heroesHealth.length; ++i) {
                if (heroesHealth[i] > 0) {
                    if (heroesHealth[i] - bossDamage < 0) {
                        heroesHealth[i] = 0;
                    } else {
                        heroesHealth[i] -= bossDamage;
                    }
                }
            }

        }

        public static void heroesHit() {
            for(int i = 0; i < heroesDamage.length; ++i) {
                if (heroesHealth[i] > 0 && bossHealth > 0) {
                    int damage = heroesDamage[i];
                    if (bossDefence == heroesAttackType[i]) {
                        Random random = new Random();
                        int coeff = random.nextInt(9) + 2;
                        damage = heroesDamage[i] * coeff;
                        System.out.println("Critical damage: " + damage);
                    }

                    if (bossHealth - damage < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth -= damage;
                    }
                }
            }

        }

public static void medicHeals() {   
    int medicIndex = 3;
    for (int i = 0; i < heroesHealth.length; i++) {
        if (heroesHealth[medicIndex] > 0) {
            int targetIndex =  -1;
            for (int i1 = 0; i < heroesHealth.length; i++) {
                if (i != medicIndex && heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                    targetIndex = i1;
                    break;
                }
            }
            if (targetIndex != -1) {
                int healAmount = 40;
                heroesHealth[targetIndex] += healAmount;
                if (heroesHealth[targetIndex] > 100) {
                    heroesHealth[targetIndex] = 100;
                }
                System.out.println("Medic healed " + heroesAttackType[targetIndex] + " for " +  healAmount + " HP");}
            }
        }
    }
}












