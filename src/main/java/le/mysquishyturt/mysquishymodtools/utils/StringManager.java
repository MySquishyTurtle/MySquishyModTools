package le.mysquishyturt.mysquishymodtools.utils;

import java.util.List;

public class StringManager {

    public enum EnumTextColor {

        BLACK(0),
        DARK_BLUE(1),
        DARK_GREEN(2),
        DARK_AQUA(3),
        DARK_RED(4),
        PURPLE(5),
        ORANGE(6),
        GREY(7),
        DARK_GREY(8),
        INDIGO(9),
        BRIGHT_GREEN("a"),
        AQUA("b"),
        RED("c"),
        PINK("d"),
        YELLOW("e"),
        WHITE("f");
        private String index;

        private EnumTextColor(int index) {
            this.index = String.valueOf(index);
        }

        private EnumTextColor(String index) {
            this.index = index;
        }

        public String ColorString(String s) {
            return getColorString() + s + EnumTextColor.WHITE.getColorString();
        }

        public String getColorString() {
            return "\u00a7" + this.index;
        }
    }

    public static String join(List<String> list) {
        String result = "";
        for(String s : list) {
            result += s + ", ";
        }
        return result.substring(0, result.length() - 2);
    }

    public static String colorBoolean(String string) {
        if (string.equals("true")) {
            return EnumTextColor.BRIGHT_GREEN.ColorString("True");
        }
        if (string.equals("false")) {
            return EnumTextColor.RED.ColorString("False");
        }
        return string;
    }
}
