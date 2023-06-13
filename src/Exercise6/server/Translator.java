package Exercise6.server;

public class Translator {

	// translate text
    private String[] english = {"Good morning", "Good night", "How are you?", "Thank you", "Goodbye", "What’s up?"};
    private String[] arabic = {"ر صب", "ك طا", "؟ ك", "ك ش", "ة م", "؟ م"};
    private String[] korean = {"좋은 아침", "안녕히 주무세요", "어떻게 지내세요?", "감사합니다", "안녕", "뭐야?"};
    private String[] malay = {"Selamat pagi", "Selamat malam", "Apa khabar? ", "Terima kasih", "Selamat tinggal", "Ada apa? "};

    /**
     * translate enlgish to arabic
     * 
     * @param englishText
     * @return
     */
    public String translateToArabic(String englishText) {
        for (int i = 0; i < english.length; i++) {
            if (english[i].equalsIgnoreCase(englishText)) {
                return arabic[i];
            }
        }
        return "Translation not available";
    }

    /**
     * translate enlgish to korean
     * 
     * @param englishText
     * @return
     */
    public String translateToKorean(String englishText) {
        for (int i = 0; i < english.length; i++) {
            if (english[i].equalsIgnoreCase(englishText)) {
                return korean[i];
            }
        }
        return "Translation not available";
    }

    /**
     * translate enlgish to malay
     * 
     * @param englishText
     * @return
     */
    public String translateToMalay(String englishText) {
        for (int i = 0; i < english.length; i++) {
            if (english[i].equalsIgnoreCase(englishText)) {
                return malay[i];
            }
        }
        return "Translation not available";
    }
}
