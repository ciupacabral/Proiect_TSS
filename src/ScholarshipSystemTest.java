import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipSystemTest {

    ScholarshipSystem sistem = new ScholarshipSystem();

    // =========================================================
    // 1. EQUIVALENCE PARTITIONING (EP) - Cazuri reprezentative
    // =========================================================

    @Test
    void testEP_Merit() {
        // EP: Caz valid standard pentru Merit
        assertEquals("BURSA_MERIT", sistem.decideScholarship(9.80, 8, 2000));
    }

    @Test
    void testEP_Studiu() {
        // EP: Caz valid standard pentru Studiu
        assertEquals("BURSA_STUDIU", sistem.decideScholarship(9.00, 15, 2000));
    }

    @Test
    void testEP_Social() {
        // EP: Caz valid standard pentru Social
        assertEquals("BURSA_SOCIALA", sistem.decideScholarship(7.50, 25, 1000));
    }

    @Test
    void testEP_Respins() {
        // EP: Caz valid Respins (prea multe absențe)
        assertEquals("RESPINS", sistem.decideScholarship(9.00, 35, 3000));
    }

    @Test
    void testEP_Invalid_Media() {
        // EP: Input invalid (Medie < 1.00)
        assertEquals("EROARE", sistem.decideScholarship(0.50, 0, 0));
    }

    // =========================================================
    // 2. BOUNDARY VALUE ANALYSIS (BVA) - Testarea limitelor
    // =========================================================

    // --- Limite Merit (Praguri: 9.50 media, 10 absențe) ---
    @Test
    void testBVA_Merit_Media() {
        // Limită: 9.50 (Merit) vs 9.49 (Studiu)
        assertEquals("BURSA_MERIT", sistem.decideScholarship(9.50, 0, 0));
        assertEquals("BURSA_STUDIU", sistem.decideScholarship(9.49, 0, 0));
    }

    @Test
    void testBVA_Merit_Absente() {
        // Limită: 10 absențe (Merit) vs 11 absențe (Studiu)
        assertEquals("BURSA_MERIT", sistem.decideScholarship(10.00, 10, 0));
        assertEquals("BURSA_STUDIU", sistem.decideScholarship(10.00, 11, 0));
    }

    // --- Limite Studiu (Praguri: 8.50 media, 20 absențe) ---
    @Test
    void testBVA_Studiu_Media() {
        // Limită: 8.50 (Studiu) vs 8.49 (Respins)
        assertEquals("BURSA_STUDIU", sistem.decideScholarship(8.50, 6, 0));
        assertEquals("RESPINS", sistem.decideScholarship(8.49, 6, 3000));
    }

    @Test
    void testBVA_Studiu_Absente() {
        // Limită: 20 absențe (Studiu) vs 21 absențe (Respins)
        assertEquals("BURSA_STUDIU", sistem.decideScholarship(9.00, 20, 0));
        assertEquals("RESPINS", sistem.decideScholarship(9.00, 21, 3000));
    }

    // --- Limite Social (Praguri: <1500 venit, <30 absențe) ---
    @Test
    void testBVA_Social_Venit() {
        // Limită: 1499 (Social) vs 1500 (Respins, operator <)
        assertEquals("BURSA_SOCIALA", sistem.decideScholarship(6.00, 0, 1499));
        assertEquals("RESPINS", sistem.decideScholarship(6.00, 0, 1500));
    }

    @Test
    void testBVA_Social_Absente() {
        // Limită: 29 absențe (Social) vs 30 absențe (Respins)
        assertEquals("BURSA_SOCIALA", sistem.decideScholarship(6.00, 29, 1000));
        assertEquals("RESPINS", sistem.decideScholarship(6.00, 30, 1000));
    }

    // =========================================================
    // 3. CAUSE-EFFECT GRAPHING - Logică booleană
    // =========================================================

    @Test
    void testCE_Merit_Conditions() {
        // Logică: C1 (Media>=9.5) AND C2 (Abs<=10) -> MERIT
        assertEquals("BURSA_MERIT", sistem.decideScholarship(9.60, 9, 2000));
    }

    @Test
    void testCE_Studiu_Conditions() {
        // Logică: NOT Merit AND (Media>=8.5 AND Abs<=20) -> STUDIU
        assertEquals("BURSA_STUDIU", sistem.decideScholarship(9.00, 15, 2000));
    }

    // =========================================================
    // 4. STRUCTURAL & CODE COVERAGE (MC/DC complet)
    // =========================================================

    @Test
    void testCoverage_IntrariNegative() {
        // Coverage: Ramuri de validare (Absențe < 0, Venit < 0)
        assertEquals("EROARE", sistem.decideScholarship(9.00, -1, 1000));
        assertEquals("EROARE", sistem.decideScholarship(9.00, 0, -500));
    }

    @Test
    void testCoverage_MediaMare() {
        // Coverage: Ramura Media > 10.00
        assertEquals("EROARE", sistem.decideScholarship(10.01, 0, 1000));
    }

    @Test
    void testCoverage_Social_ForceEntry() {
        // Coverage: Toate condițiile Social TRUE, dar nu Merit/Studiu
        assertEquals("BURSA_SOCIALA", sistem.decideScholarship(7.00, 25, 1000));
    }

    @Test
    void testCoverage_Social_Fail_Media() {
        // Coverage (MC/DC): Venit OK, dar pică la Medie (< 5.00)
        assertEquals("RESPINS", sistem.decideScholarship(4.50, 0, 1000));
    }

    @Test
    void testCoverage_Social_Fail_Absente() {
        // Coverage (MC/DC): Venit OK, Medie OK, dar pică la Absențe (>= 30)
        assertEquals("RESPINS", sistem.decideScholarship(6.00, 35, 1000));
    }
}