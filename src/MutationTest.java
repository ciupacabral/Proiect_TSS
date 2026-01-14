import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MutationTest {

    // 1. Testam Mutantul ECHIVALENT
    // Comportament asteptat: Testul TRECE (VERDE), deoarece logica e identica.
    @Test
    void testMutantEchivalent() {
        MutantEchivalent mutant = new MutantEchivalent();
        // Testam validarea absentelor (unde am facut modificarea)
        assertEquals("EROARE", mutant.decideScholarship(9.50, -1, 2000));
    }

    // 2. Testam Mutantul OMORAT (Killed)
    // Comportament asteptat: Testul PICA (ROSU).

    @Test
    void testMutantKilled() {
        MutantKilled mutant = new MutantKilled();
        // Input: Media 9.80, Absente 8.
        // Originalul dadea MERIT. Mutantul (care cere <=5 absente) nu va da MERIT.
        assertEquals("BURSA_MERIT", mutant.decideScholarship(9.80, 8, 2000));
    }

    // 3. Testam Mutantul SUPRAVIETUITOR (Survived)
    // Comportament asteptat: Testul TRECE (VERDE), desi codul e gresit.
    // Asta inseamna ESEC in mutation testing (nu am detectat bug-ul).
    @Test
    void testMutantSurvived() {
        MutantSurvived mutant = new MutantSurvived();
        // Input: Media 9.80.
        // Mutantul are pragul coborat la 9.00. 
        // 9.80 indeplineste ambele conditii, deci testul nu vede diferenta.
        assertEquals("BURSA_MERIT", mutant.decideScholarship(9.80, 8, 2000));
    }
}