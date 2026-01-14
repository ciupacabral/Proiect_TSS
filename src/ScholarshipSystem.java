public class ScholarshipSystem {

    public String decideScholarship(double media, int absente, int venit) {
        // Validare variabile
        if (media < 1.00 || media > 10.00) return "EROARE";
        if (absente < 0) return "EROARE";
        if (venit < 0) return "EROARE";

        // Logica principala
        if (media >= 9.50 && absente <= 10)
        {
            return "BURSA_MERIT";
        }


        if (media >= 8.50 && absente <= 20)
        {
            return "BURSA_STUDIU";
        }

        if (venit < 1500 && media >= 5.00 && absente < 30)
        {
            return "BURSA_SOCIALA";
        }

        return "RESPINS";
    }
}
