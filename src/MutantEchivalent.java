public class MutantEchivalent {
    public String decideScholarship(double media, int absente, int venit) {
        // Validare Input
        if (media < 1.00 || media > 10.00) return "EROARE";

        // Original: if (absente < 0)
        // Modificat: if (absente <= -1)
        // (Pentru un intreg, e exact acelasi lucru, deci e echivalent)
        if (absente <= -1) {
            return "EROARE";
        }

        if (venit < 0) return "EROARE";

        // Restul logicii ramane neschimbata...
        if (media >= 9.50 && absente <= 10) return "BURSA_MERIT";
        if (media >= 8.50 && absente <= 20) return "BURSA_STUDIU";
        if (venit < 1500 && media >= 5.00 && absente < 30) return "BURSA_SOCIALA";

        return "RESPINS";
    }
}