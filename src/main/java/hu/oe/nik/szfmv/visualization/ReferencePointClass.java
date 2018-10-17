package hu.oe.nik.szfmv.visualization;

/**
 * Class for reference points.
 */
public class ReferencePointClass {
    /**
     * Reference point name string.
     */
    private String name;
    /**
     * Reference point x integer.
     */
    private int x;
    /**
     * Reference point y integer.
     */
    private int y;
    /**
     * Get name prop.
     * @return name
     * @see String
     * */
    public String getName() {
        return name;
    }
    /**
     * Set name prop.
     * @param nameParam name
     * @see String
     * */
    public void setName(final String nameParam) {
        name = nameParam;
    }
    /**
     * Get x prop.
     * @return x
     * @see String
     * */
    public int getX() {
        return x;
    }
    /**
     * Set x prop.
     * @param xParam x
     * @see String
     * */
    public void setX(final int xParam) {
        this.x = xParam;
    }
    /**
     * Get y prop.
     * @return name
     * @see String
     * */
    public int getY() {
        return y;
    }
    /**
     * Set y prop.
     * @param yParam y
     * @see String
     * */
    public void setY(final int yParam) {
        this.y = yParam;
    }
}
