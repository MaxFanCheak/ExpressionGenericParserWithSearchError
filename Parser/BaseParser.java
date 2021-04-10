package expression.parser;

public class BaseParser {

        protected char ch;
        private Source source;

        protected void setSource(Source source) {
            this.source = source;
        }

        protected void nextChar() {
            ch = source.hasNext() ? source.next() : '\0';
        }

        protected boolean test(char expected) {
            if (ch == expected) {
                nextChar();
                return true;
            }
            return false;
        }

        protected void expect(final char c) {
            nextChar();
        }

        protected void expect(final String value) {
            for (char c : value.toCharArray()) {
                expect(c);
            }
        }

        protected boolean between(final char from, final char to) {
            return from <= ch && ch <= to;
        }
}
