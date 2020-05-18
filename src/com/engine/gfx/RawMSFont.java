package com.engine.gfx;

import com.engine.Renderer;

/**
 * This is a very very very BASIC font class.
 * <p>
 * Here is not used any kind of font file or
 * even bytes. Instead, the rendering follows
 * the various patters in constants bellow.
 * <p>
 * This not seems to bad in performance terms,
 * however, still a bit ugly font.
 * <p>
 * As all string-designs are based in a simple
 * "2D character matrix" then this will act
 * like a pixeled font. Also, as all the designs
 * has the same dimensions, this will generate a
 * Monospaced font.
 * <p>
 * Also, this have a very very >>VERY<< basic float
 * scaling system.
 * <p>
 * Future plans:
 * - pass String designs to another strategy;
 * - Strategy could use a big bytes data array;
 * - each byte offset of that byte can be a value saying
 * that we have or no to plot a rect;
 * - to get those bytes, a tool can be created to
 * help generate the values.
 * <p>
 * TODO: letter lower cases
 * TODO: more symbols
 *
 * @author Lucas Sousa
 */
public class RawMSFont {

    public static final int CHAR_WIDTH = 13;
    public static final int CHAR_HEIGHT = 6;

    public static final String ZERO =
            /******/
            "   xxxxxxx   " +
                    "  xx     xx  " +
                    "  xx     xx  " +
                    "  xx     xx  " +
                    "  xx     xx  " +
                    "   xxxxxxx   ";

    public static final String ONE =
            /******/
            "      xx     " +
                    "    xxxx     " +
                    "  xx  xx     " +
                    "      xx     " +
                    "      xx     " +
                    "  xxxxxxxxx  ";

    public static final String TWO =
            /******/
            "  xxxxxxxxx  " +
                    "         xx  " +
                    "         xx  " +
                    "  xxxxxxx    " +
                    "  xx         " +
                    "  xxxxxxxxx  ";

    public static final String THREE =
            /******/
            "  xxxxxxxxx  " +
                    "         xx  " +
                    "     xxxxxx  " +
                    "         xx  " +
                    "         xx  " +
                    "  xxxxxxxxx  ";

    public static final String FOUR =
            /******/
            "  xx     xx  " +
                    "  xx     xx  " +
                    "  xx     xx  " +
                    "  xxxxxxxxx  " +
                    "         xx  " +
                    "         xx  ";

    public static final String FIVE =
            /******/
            "  xxxxxxxxx  " +
                    "  xx         " +
                    "  xxxxxxxxx  " +
                    "         xx  " +
                    "         xx  " +
                    "  xxxxxxxxx  ";

    public static final String SIX =
            /******/
            "    xxxxxx   " +
                    "  xx         " +
                    "  xx         " +
                    "  xxxxxxxxx  " +
                    "  xx      xx " +
                    "   xxxxxxx   ";

    public static final String SEVEN =
            /******/
            "  xxxxxxxxx  " +
                    "         xx  " +
                    "         xx  " +
                    "         xx  " +
                    "         xx  " +
                    "         xx  ";

    public static final String EIGHT =
            /******/
            "   xxxxxxx   " +
                    "  xx     xx  " +
                    "  xxxxxxxxx  " +
                    "  xx     xx  " +
                    "  xx     xx  " +
                    "   xxxxxxx   ";

    public static final String NINE =
            /******/
            "  xxxxxxxxx  " +
                    "  xx     xx  " +
                    "  xxxxxxxxx  " +
                    "         xx  " +
                    "         xx  " +
                    "         xx  ";

    public static final String A =
            /******/
            "     xxx     " +
                    "   xx   xx   " +
                    " xx       xx " +
                    " xxxxxxxxxxx " +
                    " xx       xx " +
                    " xx       xx ";

    public static final String B =
            /******/
            " xxxxxxxx    " +
                    " xx     xx   " +
                    " xxxxxxxxx   " +
                    " xx       xx " +
                    " xx       xx " +
                    "  xxxxxxxx   ";

    public static final String C =
            /******/
            "  xxxxxxxx   " +
                    " xx      xx  " +
                    " x           " +
                    " x           " +
                    " xx      xx  " +
                    "  xxxxxxxx   ";

    public static final String D =
            /******/
            " xxxxxxxx    " +
                    " xx      xx  " +
                    " xx       xx " +
                    " xx       xx " +
                    " xx      xx  " +
                    " xxxxxxxx    ";

    public static final String E =
            /******/
            " xxxxxxxxxxx " +
                    " xx          " +
                    " xxxxxxx     " +
                    " xx          " +
                    " xx          " +
                    " xxxxxxxxxxx ";

    public static final String F =
            /******/
            " xxxxxxxxxxx " +
                    " xx          " +
                    " xxxxxxx     " +
                    " xx          " +
                    " xx          " +
                    " xx          ";

    //TODO: ficou feio
    public static final String G =
            /******/
            "  xxxxxxxx   " +
                    " xx      xxx " +
                    " xx          " +
                    " xx    xxxx  " +
                    " xx       xx " +
                    "   xxxxxxx   ";

    public static final String H =
            /******/
            " xx       xx " +
                    " xx       xx " +
                    " xxxxxxxxxxx " +
                    " xx       xx " +
                    " xx       xx " +
                    " xx       xx ";

    public static final String I =
            /******/
            " xxxxxxxxxxx " +
                    "     xx      " +
                    "     xx      " +
                    "     xx      " +
                    "     xx      " +
                    " xxxxxxxxxxx ";

    public static final String J =
            /******/
            " xxxxxxxxxxx " +
                    "       xx    " +
                    "       xx    " +
                    " xx    xx    " +
                    " xx    xx    " +
                    "  xxxxxxx    ";

    public static final String K =
            /******/
            " xx     xx   " +
                    " xx   xx     " +
                    " xxxxxx      " +
                    " xx   xx     " +
                    " xx     xx   " +
                    " xx       xx ";

    public static final String L =
            /******/
            " xx          " +
                    " xx          " +
                    " xx          " +
                    " xx          " +
                    " xx          " +
                    " xxxxxxxxxxx ";

    public static final String M =
            /******/
            " xx       xx " +
                    " xxx     xxx " +
                    " xx x   x xx " +
                    " xx  xxx  xx " +
                    " xx   x   xx " +
                    " xx       xx ";

    public static final String N =
            /******/
            " xxxx     xx " +
                    " xx xx    xx " +
                    " xx  xx   xx " +
                    " xx   xx  xx " +
                    " xx    xx xx " +
                    " xx     xxxx ";

    public static final String O =
            /******/
            "    xxxxx    " +
                    "  xx     xx  " +
                    " xx       xx " +
                    " xx       xx " +
                    "  xx     xx  " +
                    "    xxxxx    ";

    public static final String P =
            /******/
            " xxxxxxxxx   " +
                    " xx       xx " +
                    " xx       xx " +
                    " xxxxxxxxx   " +
                    " xx          " +
                    " xx          ";

    public static final String Q =
            /******/
            "    xxxxx    " +
                    "  xx     xx  " +
                    " xx       xx " +
                    " xx    x  xx " +
                    "  xx     xx  " +
                    "    xxxxx  x ";

    public static final String R =
            /******/
            " xxxxxxxxx   " +
                    " xx       xx " +
                    " xx       xx " +
                    " xxxxxxxxx   " +
                    " xx      xx  " +
                    " xx       xx ";

    public static final String S =
            /******/
            "  xxxxxxx    " +
                    " x           " +
                    "  xxxxxx     " +
                    "        xx   " +
                    "        xxx  " +
                    " xxxxxxxxx   ";

    public static final String T =
            /******/
            " xxxxxxxxxxx " +
                    "     xxx     " +
                    "     xxx     " +
                    "     xxx     " +
                    "     xxx     " +
                    "     xxx     ";

    public static final String U =
            /******/
            " xx       xx " +
                    " xx       xx " +
                    " xx       xx " +
                    " xx       xx " +
                    " xx       xx " +
                    "   xxxxxxx   ";

    public static final String V =
            /******/
            " xx       xx " +
                    "  xx     xx  " +
                    "   xx   xx   " +
                    "    xx xx    " +
                    "     xxx     " +
                    "      x      ";

    public static final String W =
            /******/
            " xx       xx " +
                    " xx       xx " +
                    " xx       xx " +
                    " xx   x   xx " +
                    " xx  xxx  xx " +
                    "   xx   xx   ";

    public static final String X =
            /******/
            "  x       x  " +
                    "   xx   xx   " +
                    "     xxx     " +
                    "    xx xx    " +
                    "  xx     xx  " +
                    " x         x ";

    public static final String Y =
            /******/
            " xx       xx " +
                    "  xx     xx  " +
                    "   xx   xx   " +
                    "     x x     " +
                    "     xxx     " +
                    "     xxx     ";

    public static final String Z =
            /******/
            "   xxxxxxxxx " +
                    "        xxx  " +
                    "      xx     " +
                    "    xx       " +
                    "  xx         " +
                    " xxxxxxxxxxx ";

    public static final String SPACE =
            /******/
            "             " +
                    "             " +
                    "             " +
                    "             " +
                    "             " +
                    "             ";

    public static final String EXCLAMATION_MARK =
            /******/
            "      xx     " +
                    "      xx     " +
                    "      xx     " +
                    "      xx     " +
                    "             " +
                    "      xx     ";

    public static final String HIFEN =
            /******/
                    "             " +
                    "             " +
                    "   xxxxxxxx  " +
                    "   xxxxxxxx  " +
                    "             " +
                    "             ";

    public static final String DOT =
            /******/
            "             " +
                    "             " +
                    "             " +
                    "             " +
                    "     xx      " +
                    "     xx      ";


    public static final String COMMA =
            /******/
            "             " +
                    "             " +
                    "             " +
                    "             " +
                    "  xx         " +
                    " xx          ";

    public static final String CLOSE_PARENTHESIS =
            /******/"       xx    " +
                    "         xx  " +
                    "          xx " +
                    "          xx " +
                    "         xx  " +
                    "       xx    ";

    public static final String OPEN_PARENTHESIS =
            /******/"    xx       " +
                    "  xx         " +
                    " xx          " +
                    " xx          " +
                    "  xx         " +
                    "    xx       ";

    public static final String COLON =
            /******/"             " +
                    "  XX         " +
                    "  xx         " +
                    "             " +
                    "  xx         " +
                    "  xx         ";

    public static final String SEMICOLON =
                    /******/
                    "             " +
                    "      xx     " +
                    "      xx     " +
                    "             " +
                    "      xx     " +
                    "    xx       ";

    public static final String NULL =
            /******/
            "xxx      xxxx" +
                    "xx xxxxxx xxx" +
                    "xxxxxxxxx xxx" +
                    "xxxxxx    xxx" +
                    "xxxxxxxxxxxxx" +
                    "xxxxxx  xxxxx";

    /**
     * Create max possible character,
     * but only designs above will work.
     * If user call one that is not
     * defined, than NULL is choose.
     * <p>
     * Note: must be know that models
     * are used only to mark if a renderer
     * should or no plot something in
     * that coordinate. Because this,
     * any kind of structure that can holds
     * on value of two can be used. Here
     * String was best because allows a
     * better typing design.
     */
    private static final String[] MODELS = new String[0xff];

    static {
        MODELS[' '] = SPACE;
        MODELS['!'] = EXCLAMATION_MARK;
        MODELS['.'] = DOT;
        MODELS[','] = COMMA;
        MODELS[';'] = SEMICOLON;
        MODELS['('] = OPEN_PARENTHESIS;
        MODELS[')'] = CLOSE_PARENTHESIS;
        MODELS['-'] = HIFEN;

        MODELS['0'] = ZERO;
        MODELS['1'] = ONE;
        MODELS['2'] = TWO;
        MODELS['3'] = THREE;
        MODELS['4'] = FOUR;
        MODELS['5'] = FIVE;
        MODELS['6'] = SIX;
        MODELS['7'] = SEVEN;
        MODELS['8'] = EIGHT;
        MODELS['9'] = NINE;

        MODELS['A'] = A;
        MODELS['B'] = B;
        MODELS['C'] = C;
        MODELS['D'] = D;
        MODELS['E'] = E;
        MODELS['F'] = F;
        MODELS['G'] = G;
        MODELS['H'] = H;
        MODELS['I'] = I;
        MODELS['J'] = J;
        MODELS['K'] = K;
        MODELS['L'] = L;
        MODELS['M'] = M;
        MODELS['N'] = N;
        MODELS['O'] = O;
        MODELS['P'] = P;
        MODELS['Q'] = Q;
        MODELS['R'] = R;
        MODELS['S'] = S;
        MODELS['T'] = T;
        MODELS['U'] = U;
        MODELS['V'] = V;
        MODELS['W'] = W;
        MODELS['X'] = X;
        MODELS['Y'] = Y;
        MODELS['Z'] = Z;
    }

    /**
     * This render only ONE char onto screen.
     * <p>
     * This takes a my custom renderer to "plot" rectangles
     * at current coordinates offset and, for this reason,
     * any kind of renderer that offer a "fillRect(x, y, w, h, color)"
     * method can be used.
     *
     * @param c        the targeted character that should be rendered
     * @param offX     the "what x coordinate" this character should be start rendered
     * @param offY     the "what y coordinate" this character should be start rendered
     * @param scale    the scale to increase/decrease the final result
     * @param color    the color to be used in rendering
     * @param renderer a renderer to plot "squares" in each not empty point.
     */
    public static void renderChar(char c, int offX, int offY, float scale, int color, Renderer renderer) {
        final char[] data = (MODELS[c] == null ? NULL : MODELS[c]).toCharArray();
        //avoids huge casting inside loop
        final int roundScale = Math.round(scale);

        //independent of scale, always will perform
        //(CHAR_WIDTH * CHAR_HEIGHT) iterations
        for (int y = 0; y < CHAR_HEIGHT; y++) {
            for (int x = 0; x < CHAR_WIDTH; x++) {
                if (data[x + y * CHAR_WIDTH] != ' ') {
                    renderer.fillRect(
                            (x * roundScale) + offX,
                            (y * roundScale) + offY,
                            roundScale,
                            roundScale,
                            color);
                }
            }
        }
    }
}
