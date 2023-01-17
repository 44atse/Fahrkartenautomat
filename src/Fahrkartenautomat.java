import java.util.Scanner;

public class Fahrkartenautomat {


    public static void main(String[] args) {
        //echte Endlosschleife.
        while (true) {
            double c;
            c = fahrkartenBestellungErfassen();
            double x = fahrkartenBezahlen(c);
            fahrkartenAusgeben();
            rueckgeldAusgeben(x - c);
        }


    }

    public static int nothingToPay = 0;

    public static String[] fahrkartenBezeichnungen() {
        return new String[]{"Einzelfahrschein Berlin AB", "Einzelfahschein Berlin BC",
                "Einzelfahschein Berlin ABC", "Kurzstrecke", "Tageskarte Berlin AB", "Tageskarte Berlin BC",
                "Tageskarte ABC", "Kleingruppen-Tageskarte Berlin AB", "Kleingruppen-Tageskarte Berlin BC",
                "Kleingruppen-Tageskarte Berlin ABC"};
    }

    public static double fahrkartenBestellungErfassen() {
        Scanner tastatur = new Scanner(System.in);
        int auswahl;
        double[] preisListe = {2.9, 3.3, 3.6, 1.9, 8.6, 9.0, 9.6, 23.5, 24.3, 24.9};
        double zwischensumme = 0;
        System.out.print("Fahrkartenbestellvorgang:\n=========================\n\n");// Wird nur bei neuer Bestellung ausgegeben.
        while (true) {
            System.out.println(
                    "Wählen Sie bitte aus:\r\n");
            for (int i = 0; i < fahrkartenBezeichnungen().length; i++) {
                System.out.printf("%s [%4.2f] (%d)\r\n", fahrkartenBezeichnungen()[i], preisListe[i], (i + 1));//Schleife für Stringausgabe aller im Array befindlichen Bezeichnungen.
            }
            System.out.println("Bezahlen" + " (" + (1 + preisListe.length) + ")\n");
            auswahl = tastatur.nextInt();
            while (auswahl > preisListe.length + 1 || auswahl <= 0) {
                System.out.println("\n\n>>falsche Eingabe<<\n\n");
                auswahl = tastatur.nextInt();
            }

            System.out.println("Ihre Wahl: " + auswahl);
            if (auswahl == preisListe.length + 1) {
                System.out.println("Der Bezahlvorgang startet:");
                break;
            }

            System.out.print("Anzahl der Tickets: ");
            int anzahlTickets = tastatur.nextInt();

            if (anzahlTickets <= 10 && anzahlTickets > 0) {
                System.out.println("  >> " + anzahlTickets + " Ticket/s gewählt \n");
            } else {
                anzahlTickets = 1;
                System.out.println(" Fehler bei der Anzahl!  >> " + anzahlTickets + " Ticket ist  ausgewählt \n");

            }
            zwischensumme = zwischensumme + (anzahlTickets * preisListe[auswahl - 1]);
            System.out.format("Zwischensumme: %4.2f € %n\n", zwischensumme);
        }

        return zwischensumme;

    }

    public static double fahrkartenBezahlen(double zuZahlenderBetrag) {

        double eingezahlterGesamtbetrag = 0.0;
        Scanner tastatur = new Scanner(System.in);
        double eingeworfeneMuenze;

        if (zuZahlenderBetrag == 0) {nothingToPay = 1;}// falls als erstes Bezahlen ausgewählt wurde, soll kein Fahrschein ausgegeben werden.

        while (eingezahlterGesamtbetrag < zuZahlenderBetrag) {
            System.out.format("Noch zu zahlen: %4.2f € %n", (zuZahlenderBetrag - eingezahlterGesamtbetrag));
            System.out.print("Eingabe (mind. 5Ct, höchstens 50 Euro): ");
            eingeworfeneMuenze = tastatur.nextDouble();
            double z = eingeworfeneMuenze;
            if (z==50 | z== 20| z==10| z==5 | z == 2 | z == 1 | z == 0.5 | z == 0.2 | z == 0.1 | z == 0.05) {
                eingezahlterGesamtbetrag = eingeworfeneMuenze + eingezahlterGesamtbetrag;
            }

        }
        return eingezahlterGesamtbetrag;

    }

    public static void fahrkartenAusgeben() {
        //Die Endlosschleife in der Main-Methode überspringt den Schleifendurchgang, falls Bezahlen als 1. ausgewählt wurde.
        if (nothingToPay != 1) {
            System.out.println("\nFahrschein wird ausgegeben");
            for (int i = 0; i < 10; i++) {
                System.out.print("=");
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }


    // -------------------------------

    public static void rueckgeldAusgeben(double rueckgabebetrag) {


        if (rueckgabebetrag > 0.0) {
            System.out.format("\n\nDer Rückgabebetrag in Höhe von %4.2f € %n", rueckgabebetrag);
            System.out.println("wird in folgenden Münzen ausgezahlt:");

            while (rueckgabebetrag >= 2.0) // 2 EURO-Münzen
            {
                System.out.println("2 EURO");
                rueckgabebetrag -= 2.0;
            }
            while (rueckgabebetrag >= 1.0) // 1 EURO-Münzen
            {
                System.out.println("1 EURO");
                rueckgabebetrag -= 1.0;
            }
            while (rueckgabebetrag >= 0.5) // 50 CENT-Münzen
            {
                System.out.println("50 CENT");
                rueckgabebetrag -= 0.5;
            }
            while (rueckgabebetrag >= 0.2) // 20 CENT-Münzen
            {
                System.out.println("20 CENT");
                rueckgabebetrag -= 0.2;
            }
            while (rueckgabebetrag >= 0.1) // 10 CENT-Münzen
            {
                System.out.println("10 CENT");
                rueckgabebetrag -= 0.1;
            }
            while (rueckgabebetrag >= 0.049)// 5 CENT-Münzen
            {
                System.out.println("5 CENT");
                rueckgabebetrag -= 0.05;
            }
        }

        System.out.println("\n\nVergessen Sie nicht, den Fahrschein vor Fahrtantritt entwerten zu lassen!\r\n" +
                "Wir wünschen Ihnen eine gute Fahrt.\n\n");

    }

}