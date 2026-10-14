package tn.esprit.autoloc;

public class Utils {

    /**
     * Vérifie qu'une chaîne de caractères est non vide.
     * @param value la chaîne à tester
     * @return true si la chaîne n'est ni null, ni vide, ni composée d'espaces
     */
    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}