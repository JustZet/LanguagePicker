package com.devmob.languagepicker.helpers;


import com.devmob.languagepicker.models.Language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanguageHelper {

    public static List<Language> getAllLanguages() {
        return new ArrayList<>(Arrays.asList(
                // Most common languages (1-10)
                new Language("en", "English", "US", "United States", 1),
                new Language("zh", "Chinese", "CN", "China", 2),
                new Language("es", "Spanish", "ES", "Spain", 3),
                new Language("hi", "Hindi", "IN", "India", 4),
                new Language("ar", "Arabic", "SA", "Saudi Arabia", 5),
                new Language("pt", "Portuguese", "PT", "Portugal", 6),
                new Language("bn", "Bengali", "BD", "Bangladesh", 7),
                new Language("ru", "Russian", "RU", "Russia", 8),
                new Language("ja", "Japanese", "JP", "Japan", 9),
                new Language("pa", "Punjabi", "IN", "India", 10),

                // Very common languages (11-25)
                new Language("de", "German", "DE", "Germany", 11),
                new Language("jv", "Javanese", "ID", "Indonesia", 12),
                new Language("ko", "Korean", "KR", "South Korea", 13),
                new Language("fr", "French", "FR", "France", 14),
                new Language("te", "Telugu", "IN", "India", 15),
                new Language("mr", "Marathi", "IN", "India", 16),
                new Language("tr", "Turkish", "TR", "Turkey", 17),
                new Language("ta", "Tamil", "IN", "India", 18),
                new Language("vi", "Vietnamese", "VN", "Vietnam", 19),
                new Language("ur", "Urdu", "PK", "Pakistan", 20),
                new Language("it", "Italian", "IT", "Italy", 21),
                new Language("th", "Thai", "TH", "Thailand", 22),
                new Language("gu", "Gujarati", "IN", "India", 23),
                new Language("fa", "Persian", "IR", "Iran", 24),
                new Language("pl", "Polish", "PL", "Poland", 25),

                // Common languages (26-50)
                new Language("uk", "Ukrainian", "UA", "Ukraine", 26),
                new Language("ml", "Malayalam", "IN", "India", 27),
                new Language("kn", "Kannada", "IN", "India", 28),
                new Language("or", "Oriya", "IN", "India", 29),
                new Language("my", "Burmese", "MM", "Myanmar", 30),
                new Language("yo", "Yoruba", "NG", "Nigeria", 31),
                new Language("uz", "Uzbek", "UZ", "Uzbekistan", 32),
                new Language("su", "Sundanese", "ID", "Indonesia", 33),
                new Language("ro", "Romanian", "RO", "Romania", 34),
                new Language("nl", "Dutch", "NL", "Netherlands", 35),
                new Language("ha", "Hausa", "NG", "Nigeria", 36),
                new Language("hu", "Hungarian", "HU", "Hungary", 37),
                new Language("cs", "Czech", "CZ", "Czech Republic", 38),
                new Language("el", "Greek", "GR", "Greece", 39),
                new Language("sv", "Swedish", "SE", "Sweden", 40),
                new Language("bg", "Bulgarian", "BG", "Bulgaria", 41),
                new Language("az", "Azerbaijani", "AZ", "Azerbaijan", 42),
                new Language("sr", "Serbian", "RS", "Serbia", 43),
                new Language("he", "Hebrew", "IL", "Israel", 44),
                new Language("da", "Danish", "DK", "Denmark", 45),
                new Language("fi", "Finnish", "FI", "Finland", 46),
                new Language("sk", "Slovak", "SK", "Slovakia", 47),
                new Language("no", "Norwegian", "NO", "Norway", 48),
                new Language("hr", "Croatian", "HR", "Croatia", 49),
                new Language("ca", "Catalan", "ES", "Spain", 50),

                // Less common languages (51-100)
                new Language("km", "Khmer", "KH", "Cambodia", 51),
                new Language("si", "Sinhala", "LK", "Sri Lanka", 52),
                new Language("ne", "Nepali", "NP", "Nepal", 53),
                new Language("id", "Indonesian", "ID", "Indonesia", 54),
                new Language("ms", "Malay", "MY", "Malaysia", 55),
                new Language("tl", "Tagalog", "PH", "Philippines", 56),
                new Language("sw", "Swahili", "TZ", "Tanzania", 57),
                new Language("am", "Amharic", "ET", "Ethiopia", 58),
                new Language("af", "Afrikaans", "ZA", "South Africa", 59),
                new Language("lt", "Lithuanian", "LT", "Lithuania", 60),
                new Language("lv", "Latvian", "LV", "Latvia", 61),
                new Language("sl", "Slovene", "SI", "Slovenia", 62),
                new Language("et", "Estonian", "EE", "Estonia", 63),
                new Language("mk", "Macedonian", "MK", "North Macedonia", 64),
                new Language("sq", "Albanian", "AL", "Albania", 65),
                new Language("hy", "Armenian", "AM", "Armenia", 66),
                new Language("ka", "Georgian", "GE", "Georgia", 67),
                new Language("be", "Belarusian", "BY", "Belarus", 68),
                new Language("is", "Icelandic", "IS", "Iceland", 69),
                new Language("ga", "Irish", "IE", "Ireland", 70),
                new Language("mt", "Maltese", "MT", "Malta", 71),
                new Language("cy", "Welsh", "GB", "United Kingdom", 72),
                new Language("eu", "Basque", "ES", "Spain", 73),
                new Language("gl", "Galician", "ES", "Spain", 74),
                new Language("lb", "Luxembourgish", "LU", "Luxembourg", 75),
                new Language("mn", "Mongolian", "MN", "Mongolia", 76),
                new Language("tg", "Tajik", "TJ", "Tajikistan", 77),
                new Language("tk", "Turkmen", "TM", "Turkmenistan", 78),
                new Language("kk", "Kazakh", "KZ", "Kazakhstan", 79),
                new Language("ky", "Kyrgyz", "KG", "Kyrgyzstan", 80),
                new Language("ps", "Pashto", "AF", "Afghanistan", 81),
                new Language("ku", "Kurdish", "IQ", "Iraq", 82),
                new Language("ug", "Uyghur", "CN", "China", 83),
                new Language("bo", "Tibetan", "CN", "China", 84),
                new Language("lo", "Lao", "LA", "Laos", 85),
                new Language("sd", "Sindhi", "PK", "Pakistan", 86),
                new Language("as", "Assamese", "IN", "India", 87),
                new Language("ks", "Kashmiri", "IN", "India", 88),
                new Language("sa", "Sanskrit", "IN", "India", 89),
                new Language("ig", "Igbo", "NG", "Nigeria", 90),
                new Language("so", "Somali", "SO", "Somalia", 91),
                new Language("rw", "Kinyarwanda", "RW", "Rwanda", 92),
                new Language("lg", "Luganda", "UG", "Uganda", 93),
                new Language("om", "Oromo", "ET", "Ethiopia", 94),
                new Language("ti", "Tigrinya", "ER", "Eritrea", 95),
                new Language("sn", "Shona", "ZW", "Zimbabwe", 96),
                new Language("mg", "Malagasy", "MG", "Madagascar", 97),
                new Language("qu", "Quechua", "PE", "Peru", 98),
                new Language("gn", "Guaraní", "PY", "Paraguay", 99),
                new Language("ay", "Aymara", "BO", "Bolivia", 100),

                // Rare/Regional languages (101-185)
                new Language("bs", "Bosnian", "BA", "Bosnia and Herzegovina", 101),
                new Language("dv", "Divehi", "MV", "Maldives", 102),
                new Language("fo", "Faroese", "FO", "Faroe Islands", 103),
                new Language("fj", "Fijian", "FJ", "Fiji", 104),
                new Language("ht", "Haitian Creole", "HT", "Haiti", 105),
                new Language("mi", "Māori", "NZ", "New Zealand", 106),
                new Language("sm", "Samoan", "WS", "Samoa", 107),
                new Language("to", "Tonga", "TO", "Tonga", 108),
                new Language("ty", "Tahitian", "PF", "French Polynesia", 109),
                new Language("mh", "Marshallese", "MH", "Marshall Islands", 110),
                new Language("na", "Nauru", "NR", "Nauru", 111),
                new Language("gd", "Scottish Gaelic", "GB", "United Kingdom", 112),
                new Language("kw", "Cornish", "GB", "United Kingdom", 113),
                new Language("gv", "Manx", "IM", "Isle of Man", 114),
                new Language("br", "Breton", "FR", "France", 115),
                new Language("co", "Corsican", "FR", "France", 116),
                new Language("oc", "Occitan", "FR", "France", 117),
                new Language("wa", "Walloon", "BE", "Belgium", 118),
                new Language("fy", "Western Frisian", "NL", "Netherlands", 119),
                new Language("li", "Limburgish", "NL", "Netherlands", 120),
                new Language("rm", "Romansh", "CH", "Switzerland", 121),
                new Language("sc", "Sardinian", "IT", "Italy", 122),
                new Language("an", "Aragonese", "ES", "Spain", 123),
                new Language("se", "Northern Sami", "NO", "Norway", 124),
                new Language("nb", "Norwegian Bokmål", "NO", "Norway", 125),
                new Language("nn", "Norwegian Nynorsk", "NO", "Norway", 126),
                new Language("yi", "Yiddish", "IL", "Israel", 127),
                new Language("ak", "Akan", "GH", "Ghana", 128),
                new Language("ee", "Ewe", "GH", "Ghana", 129),
                new Language("tw", "Twi", "GH", "Ghana", 130),
                new Language("ff", "Fula", "SN", "Senegal", 131),
                new Language("wo", "Wolof", "SN", "Senegal", 132),
                new Language("bm", "Bambara", "ML", "Mali", 133),
                new Language("ln", "Lingala", "CD", "Democratic Republic of the Congo", 134),
                new Language("lu", "Luba-Katanga", "CD", "Democratic Republic of the Congo", 135),
                new Language("kg", "Kongo", "CG", "Congo", 136),
                new Language("sg", "Sango", "CF", "Central African Republic", 137),
                new Language("rn", "Kirundi", "BI", "Burundi", 138),
                new Language("ny", "Chichewa", "MW", "Malawi", 139),
                new Language("st", "Southern Sotho", "LS", "Lesotho", 140),
                new Language("tn", "Tswana", "BW", "Botswana", 141),
                new Language("ss", "Swati", "SZ", "Eswatini", 142),
                new Language("xh", "Xhosa", "ZA", "South Africa", 143),
                new Language("ts", "Tsonga", "ZA", "South Africa", 144),
                new Language("ve", "Venda", "ZA", "South Africa", 145),
                new Language("nr", "South Ndebele", "ZA", "South Africa", 146),
                new Language("nd", "North Ndebele", "ZW", "Zimbabwe", 147),
                new Language("ki", "Kikuyu", "KE", "Kenya", 148),
                new Language("kr", "Kanuri", "NG", "Nigeria", 149),
                new Language("iu", "Inuktitut", "CA", "Canada", 150),
                new Language("ik", "Inupiaq", "US", "United States", 151),
                new Language("nv", "Navajo", "US", "United States", 152),
                new Language("cr", "Cree", "CA", "Canada", 153),
                new Language("oj", "Ojibwe", "CA", "Canada", 154),
                new Language("ch", "Chamorro", "GU", "Guam", 155),
                new Language("ii", "Nuosu", "CN", "China", 156),
                new Language("za", "Zhuang", "CN", "China", 157),
                new Language("kl", "Kalaallisut", "GL", "Greenland", 158),
                new Language("tt", "Tatar", "RU", "Russia", 159),
                new Language("ba", "Bashkir", "RU", "Russia", 160),
                new Language("ce", "Chechen", "RU", "Russia", 161),
                new Language("cv", "Chuvash", "RU", "Russia", 162),
                new Language("av", "Avaric", "RU", "Russia", 163),
                new Language("kv", "Komi", "RU", "Russia", 164),
                new Language("os", "Ossetian", "GE", "Georgia", 165),
                new Language("ab", "Abkhaz", "GE", "Georgia", 166),
                new Language("bh", "Bihari", "IN", "India", 167),
                new Language("pi", "Pāli", "IN", "India", 168),
                new Language("ho", "Hiri Motu", "PG", "Papua New Guinea", 169),
                new Language("bi", "Bislama", "VU", "Vanuatu", 170),
                new Language("hz", "Herero", "NA", "Namibia", 171),
                new Language("ng", "Ndonga", "NA", "Namibia", 172),
                new Language("kj", "Kwanyama", "NA", "Namibia", 173),
                new Language("aa", "Afar", "ET", "Ethiopia", 174),
                new Language("ae", "Avestan", "IR", "Iran", 175),
                new Language("cu", "Old Church Slavonic", "BG", "Bulgaria", 176),
                new Language("la", "Latin", "VA", "Vatican City", 177)
        ));
    }

    // Get a specific language by language code and country code
    public static Language getLanguage(String languageCode, String countryCode) {
        for (Language language : getAllLanguages()) {
            if (language.getLanguageCode().equals(languageCode) &&
                    language.getCountryCode().equals(countryCode)) {
                return language;
            }
        }
        return null;
    }

    // Get all languages for a specific language code
    public static List<Language> getLanguagesByCode(String languageCode) {
        List<Language> result = new ArrayList<>();
        for (Language language : getAllLanguages()) {
            if (language.getLanguageCode().equals(languageCode)) {
                result.add(language);
            }
        }
        return result;
    }

    // Get all languages for a specific country
    public static List<Language> getLanguagesByCountry(String countryCode) {
        List<Language> result = new ArrayList<>();
        for (Language language : getAllLanguages()) {
            if (language.getCountryCode().equals(countryCode)) {
                result.add(language);
            }
        }
        return result;
    }

    public static String getLanguageIcon(String countryCode) {
        return "https://flagcdn.com/w160/" + countryCode.toLowerCase() + ".png";
    }
    // Example usage

}