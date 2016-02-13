package le.mysquishyturt.mysquishymodtools.utils;

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
}
