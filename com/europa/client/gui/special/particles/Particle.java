/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Vector2f
 *  org.lwjgl.opengl.Display
 */
package com.europa.client.gui.special.particles;

import com.europa.api.utilities.math.MathUtils;
import java.util.Random;
import javax.vecmath.Vector2f;
import org.lwjgl.opengl.Display;

public class Particle {
    public static Random random = new Random();
    public Vector2f velocity;
    public Vector2f pos;
    public float size;
    public float alpha;

    public Particle(Vector2f velocity, float x, float y, float size) {
        this.velocity = velocity;
        this.pos = new Vector2f(x, y);
        this.size = size;
    }

    public static Particle generateParticle() {
        Vector2f velocity = new Vector2f((float)(Math.random() * Double.longBitsToDouble(Double.doubleToLongBits(0.06486448953688094) ^ 0x7FB09AF58D3B8E27L) - Double.longBitsToDouble(Double.doubleToLongBits(14.891974188925316) ^ 0x7FDDC8B0D7449EE7L)), (float)(Math.random() * Double.longBitsToDouble(Double.doubleToLongBits(0.7873232688379924) ^ 0x7FE931C091613F6FL) - Double.longBitsToDouble(Double.doubleToLongBits(13.542297474564089) ^ 0x7FDB15A803BBE98DL)));
        float x = random.nextInt(Display.getWidth());
        float y = random.nextInt(Display.getHeight());
        float size = (float)(Math.random() * Double.longBitsToDouble(Double.doubleToLongBits(11.843775697341316) ^ 0x7FDFB0035E427B29L)) + Float.intBitsToFloat(Float.floatToIntBits(15.711738f) ^ 0x7EFB6347);
        return new Particle(velocity, x, y, size);
    }

    public float getAlpha() {
        return this.alpha;
    }

    public Vector2f getVelocity() {
        return this.velocity;
    }

    /*
     * WARNING - void declaration
     */
    public void setVelocity(Vector2f vector2f) {
        void velocity;
        this.velocity = velocity;
    }

    public float getX() {
        return this.pos.getX();
    }

    /*
     * WARNING - void declaration
     */
    public void setX(float f) {
        void x;
        this.pos.setX((float)x);
    }

    public float getY() {
        return this.pos.getY();
    }

    /*
     * WARNING - void declaration
     */
    public void setY(float f) {
        void y;
        this.pos.setY((float)y);
    }

    public float getSize() {
        return this.size;
    }

    /*
     * WARNING - void declaration
     */
    public void setSize(float f) {
        void size;
        this.size = size;
    }

    /*
     * WARNING - void declaration
     */
    public void tick(int n, float f) {
        block4: {
            void speed;
            void delta;
            this.pos.x += this.velocity.getX() * (float)delta * speed;
            this.pos.y += this.velocity.getY() * (float)delta * speed;
            if (this.alpha < Float.intBitsToFloat(Float.floatToIntBits(0.012547307f) ^ 0x7F329338)) {
                this.alpha += Float.intBitsToFloat(Float.floatToIntBits(266.51282f) ^ 0x7EC98D69) * (float)delta;
            }
            if (this.pos.getX() > (float)Display.getWidth()) {
                this.pos.setX(Float.intBitsToFloat(Float.floatToIntBits(2.1064574E38f) ^ 0x7F1E78E5));
            }
            if (this.pos.getX() < Float.intBitsToFloat(Float.floatToIntBits(2.9105874E38f) ^ 0x7F5AF7E0)) {
                this.pos.setX((float)Display.getWidth());
            }
            if (this.pos.getY() > (float)Display.getHeight()) {
                this.pos.setY(Float.intBitsToFloat(Float.floatToIntBits(3.752927E37f) ^ 0x7DE1DEFF));
            }
            if (!(this.pos.getY() < Float.intBitsToFloat(Float.floatToIntBits(6.025234E36f) ^ 0x7C910D5F))) break block4;
            this.pos.setY((float)Display.getHeight());
        }
    }

    /*
     * WARNING - void declaration
     */
    public float getDistanceTo(Particle particle) {
        void particle1;
        return this.getDistanceTo(particle1.getX(), particle1.getY());
    }

    /*
     * WARNING - void declaration
     */
    public float getDistanceTo(float f, float f2) {
        void y;
        void x;
        return (float)MathUtils.distance(this.getX(), this.getY(), (float)x, (float)y);
    }
}

