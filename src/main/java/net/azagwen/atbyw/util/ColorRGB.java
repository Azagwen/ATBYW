package net.azagwen.atbyw.util;

public class ColorRGB {
    public final float red;
    public final float green;
    public final float blue;
    public final float redNormalized;
    public final float greenNormalized;
    public final float blueNormalized;

    public ColorRGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.redNormalized = (((float)red) / 255);
        this.greenNormalized = (((float)green) / 255);
        this.blueNormalized = (((float)blue) / 255);
    }
}
