public class MutantKilled {
    public String decideScholarship(double media, int absente, int venit) {
        // Validari standard...
        if (media < 1.00 || media > 10.00) return "EROARE";
        if (absente < 0) return "EROARE";
        if (venit < 0) return "EROARE";

        // Original: absente <= 10
        // Modificat: absente <= 5
        // Testul cu 8 absente va pica aici (se asteapta la Merit, dar nu primeste)
        if (media >= 9.50 && absente <= 5) {
            return "BURSA_MERIT";
        }

        if (media >= 8.50 && absente <= 20) return "BURSA_STUDIU";
        if (venit < 1500 && media >= 5.00 && absente < 30) return "BURSA_SOCIALA";

        return "RESPINS";
    }
}