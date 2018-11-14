package hu.oe.nik.szfmv.environment;

public class WorldObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected float rotation = 0f;
    protected String imageFileName;
    // protected BufferedImage image;

    /**
     * Creates an object of the virtual world on the given coordinates with the
     * given image.
     *
     * @param x             the initial x coordinate of the object
     * @param y             the initial y coordinate of the object
     * @param imageFileName the filename of the image representing the object in the
     *                      virtual world
     */
    public WorldObject(int x, int y, String imageFileName) {
        this.x = x;
        this.y = y;
        this.imageFileName = imageFileName;

        // read file from resources
        /*
         * try { image = ImageIO.read(new
         * File(ClassLoader.getSystemResource(imageFileName).getFile())); } catch
         * (IOException e) { e.printStackTrace(); }
         */
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public float getRotation() { return rotation; }

    public String getImageFileName() {
        return this.imageFileName;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    // public BufferedImage getImage() {
    // return image;
    // }
}