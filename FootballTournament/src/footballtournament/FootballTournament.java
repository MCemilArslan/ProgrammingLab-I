package footballtournament;
import java.util.Scanner;

public class FootballTournament {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Takim isimleri ve indeksleri: 0 -> A, 1 -> B, 2 -> C, 3 -> D
        String[] takimlar = {"A", "B", "C", "D"};

        // Istatistik dizileri (Her takim icin bir eleman)
        int[] oynanan = new int[4];
        int[] galibiyet = new int[4];
        int[] beraberlik = new int[4];
        int[] maglubiyet = new int[4];
        int[] atilanGol = new int[4];
        int[] yenilenGol = new int[4];
        int[] averaj = new int[4];
        int[] puan = new int[4];

        // Fikstur tanimlamalari (Ev Sahibi vs Deplasman)
        int[][] maclar = {
            {0, 1}, // Mac 1: A - B
            {0, 2}, // Mac 2: A - C
            {0, 3}, // Mac 3: A - D
            {1, 2}, // Mac 4: B - C
            {1, 3}, // Mac 5: B - D
            {2, 3}  // Mac 6: C - D
        };

        // 1. FIKSTURI GOSTERME
        System.out.println("========================================");
        System.out.println("           TURNUVA FIKSTURU             ");
        System.out.println("========================================");
        for (int i = 0; i < maclar.length; i++) {
            String ev = takimlar[maclar[i][0]];
            String dep = takimlar[maclar[i][1]];
            System.out.println("Mac " + (i + 1) + ": " + ev + " - " + dep);
        }
        System.out.println("========================================\n");

        // 2. SKOR GIRISI, ISTATISTIK GUNCELLEME VE HER MAC SONU TABLO
        for (int i = 0; i < maclar.length; i++) {
            int evIndex = maclar[i][0];
            int depIndex = maclar[i][1];
            String evAdi = takimlar[evIndex];
            String depAdi = takimlar[depIndex];

            System.out.println("--- Mac " + (i + 1) + ": " + evAdi + " - " + depAdi + " ---");
            System.out.print(evAdi + " takimi kac gol atti? ");
            int evGol = scanner.nextInt();

            System.out.print(depAdi + " takimi kac gol atti? ");
            int depGol = scanner.nextInt();

            // Girilen skoru ekrana yazdir
            System.out.println("\nMac Sonucu: " + evAdi + " " + evGol + " - " + depGol + " " + depAdi);

            // Oynanan mac sayilarini artir
            oynanan[evIndex]++;
            oynanan[depIndex]++;

            // Golleri guncelle
            atilanGol[evIndex] += evGol;
            yenilenGol[evIndex] += depGol;

            atilanGol[depIndex] += depGol;
            yenilenGol[depIndex] += evGol;

            // Averajlari guncelle
            averaj[evIndex] = atilanGol[evIndex] - yenilenGol[evIndex];
            averaj[depIndex] = atilanGol[depIndex] - yenilenGol[depIndex];

            // Galibiyet, Maglubiyet ve Puan guncellemesi
            if (evGol > depGol) { // Ev sahibi kazandi
                galibiyet[evIndex]++;
                puan[evIndex] += 3;

                maglubiyet[depIndex]++;
            } else if (depGol > evGol) { // Deplasman kazandi
                galibiyet[depIndex]++;
                puan[depIndex] += 3;

                maglubiyet[evIndex]++;
            } else { // Beraberlik
                beraberlik[evIndex]++;
                puan[evIndex] += 1;

                beraberlik[depIndex]++;
                puan[depIndex] += 1;
            }

            // HER MAC SONUNDA GUNCELLENEN PUAN DURUMU TABLOSU
            System.out.println("\n==========================================================================");
            System.out.println("                GUNCEL PUAN DURUMU ("+(i + 1) + ". Mac Sonrasi)");
            System.out.println("==========================================================================");
            System.out.printf("%-10s %-5s %-5s %-5s %-5s %-5s %-5s %-8s %-5s\n", 
                              "Takim", "OM", "G", "B", "M", "AG", "YG", "Averaj", "Puan");
            System.out.println("--------------------------------------------------------------------------");

            for (int k = 0; k < 4; k++) {
                System.out.printf("%-10s %-5d %-5d %-5d %-5d %-5d %-5d %-8d %-5d\n",
                        takimlar[k], oynanan[k], galibiyet[k], beraberlik[k], maglubiyet[k],
                        atilanGol[k], yenilenGol[k], averaj[k], puan[k]);
            }
            System.out.println("==========================================================================\n");
        }

        // 3. SAMPIYONU BELIRLEME (Once Puana, Esitlik Varsa Avereja Bakilir)
        int sampiyonIndex = 0;

        for (int i = 1; i < 4; i++) {
            if (puan[i] > puan[sampiyonIndex]) {
                sampiyonIndex = i;
            } else if (puan[i] == puan[sampiyonIndex]) {
                // Puanlar esitse averaja bak
                if (averaj[i] > averaj[sampiyonIndex]) {
                    sampiyonIndex = i;
                }
            }
        }

        // Sampiyonu Yazdir
        System.out.println("****************************************");
        System.out.println("  TURNUVA SAMPIYONI: TAKIM " + takimlar[sampiyonIndex]);
        System.out.println("  Puan: " + puan[sampiyonIndex] + " | Averaj: " + averaj[sampiyonIndex]);
        System.out.println("****************************************");

        scanner.close();
    }
}