package busstops;
import java.util.ArrayList;
import java.util.Scanner;

public class BusStops {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. KURULUM VE GIRDILER
        System.out.print("Durak sayisini giriniz: ");
        int durakSayisi = scanner.nextInt();
        scanner.nextLine(); // Buffer temizleme

        // 3 Tane ArrayList Tanimlama
        ArrayList<String> durakIsimleri = new ArrayList<>();
        ArrayList<Integer> binenYolcular = new ArrayList<>();
        ArrayList<Integer> inenYolcular = new ArrayList<>();

        // Durak isimlerini alma
        for (int i = 0; i < durakSayisi; i++) {
            System.out.print((i + 1) + ". Durak ismini giriniz: ");
            String durakAdi = scanner.nextLine();
            durakIsimleri.add(durakAdi);
        }

        System.out.print("\nOtobus koltuk kapasitesini giriniz: ");
        int koltukKapasitesi = scanner.nextInt();

        // Takip Degiskenleri
        int mevcutYolcu = 0;
        int kapasiteAsimSayaci = 0;
        int toplamYolcuHacmi = 0; // Ortalama doluluk hesabi icin durak sonrasi yolcu toplamlari

        System.out.println("\n========================================");
        System.out.println("          OTOBUS SEFERI BASLADI         ");
        System.out.println("========================================\n");

        // 2. DURAK ISLEMLERI (DONGU)
        for (int i = 0; i < durakSayisi; i++) {
            String mevcutDurak = durakIsimleri.get(i);
            boolean ilkDurakMi = (i == 0);
            boolean sonDurakMi = (i == durakSayisi - 1);

            System.out.println("----------------------------------------");
            System.out.println("DURAK " + (i + 1) + ": " + mevcutDurak);
            System.out.println("Durak Oncesi Otobusteki Yolcu Sayisi: " + mevcutYolcu);

            int binen = 0;
            int inen = 0;

            if (ilkDurakMi) {
                // ILK DURAK: Inen yolcu olamaz (0 kabul edilir)
                System.out.print("Kac yolcu bindi? ");
                binen = scanner.nextInt();
                inen = 0;
                System.out.println("Ilk durak oldugundan inen yolcu sayisi: 0");

                binenYolcular.add(binen);
                inenYolcular.add(inen);

                mevcutYolcu += binen;

            } else if (sonDurakMi) {
                // SON DURAK: Binen yolcu 0'dir, inen yolcuyu kullanici girer
                binen = 0;
                System.out.println(">> SON DURAK! Yeni yolcu binmiyor (Binen: 0).");

                while (true) {
                    System.out.print("Kac yolcu indi? ");
                    inen = scanner.nextInt();

                    if (mevcutYolcu - inen > 0) {
                        System.out.println("HATA: Son durakta otobusteki tum yolcular inmelidir!");
                        System.out.println("Otobusteki mevcut yolcu sayisi: " + mevcutYolcu + ". Lutfen tam bu sayida inen giriniz.");
                    }
                    
                    else if(mevcutYolcu - inen < 0){
                        System.out.println("HATA: Mevcut yolcudan fazla yolcu inemez!");
                        System.out.println("Otobusteki mevcut yolcu sayisi: " + mevcutYolcu + ". Lutfen tam bu sayida inen giriniz.");
                    }
                    
                    else {
                        break; // Tum yolcular indi, donguden cik
                    }
                }

                binenYolcular.add(binen);
                inenYolcular.add(inen);

                mevcutYolcu -= inen;

            } else {
                // ARA DURAKLAR
                System.out.print("Kac yolcu bindi? ");
                binen = scanner.nextInt();

                // Inen yolcu kontrolu
                while (true) {
                    System.out.print("Kac yolcu indi? ");
                    inen = scanner.nextInt();

                    if (inen > (mevcutYolcu + binen)) {
                        System.out.println("HATA: Inen yolcu mevcut yolcudan fazla olamaz! Lutfen tekrar giriniz.");
                    } else {
                        break;
                    }
                }

                binenYolcular.add(binen);
                inenYolcular.add(inen);

                mevcutYolcu = mevcutYolcu + binen - inen;
            }

            System.out.println("Guncel Yolcu Sayisi: " + mevcutYolcu);

            // Kapasite Asim Uyari Kontrolu
            if (mevcutYolcu > koltukKapasitesi) {
                System.out.println("WARNING: Over capacity! (Kapasite Asildi)");
                kapasiteAsimSayaci++;
            }

            toplamYolcuHacmi += mevcutYolcu;
            System.out.println("----------------------------------------\n");
        }

        // 3. ISTATISTIKLER
        System.out.println("========================================");
        System.out.println("          SEFER ISTATISTIKLERI          ");
        System.out.println("========================================");

        // En kalabalik durak (En cok yolcu binen durak)
        int enCokBinenIndex = 0;
        for (int i = 1; i < durakSayisi; i++) {
            if (binenYolcular.get(i) > binenYolcular.get(enCokBinenIndex)) {
                enCokBinenIndex = i;
            }
        }

        // Ortalama Doluluk Hesabi
        double ortalamaDoluluk = (double) toplamYolcuHacmi / durakSayisi;

        System.out.println("1. En Kalabalik Durak (En Cok Binen) : " 
                           + durakIsimleri.get(enCokBinenIndex) 
                           + " (" + binenYolcular.get(enCokBinenIndex) + " yolcu)");
        
        System.out.printf("2. Ortalama Doluluk (Occupancy)      : %.2f yolcu/durak\n", ortalamaDoluluk);
        
        System.out.println("3. Kapasite Asilan Durak Sayisi      : " + kapasiteAsimSayaci);

        System.out.println("========================================");

        scanner.close();
    }
}        