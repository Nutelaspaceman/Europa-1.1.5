/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package com.europa.client.gui.special.particles;

import com.europa.api.utilities.render.RenderUtils;
import com.europa.client.gui.special.particles.Particle;
import com.europa.client.modules.client.ModuleParticles;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;

public class ParticleSystem {
    public static float SPEED = Float.intBitsToFloat(Float.floatToIntBits(186.37817f) ^ 0x7EF6AC1D);
    public List<Particle> particleList = new ArrayList<Particle>();
    public int dist;

    public ParticleSystem(int initAmount, int dist) {
        this.addParticles(initAmount);
        this.dist = dist;
    }

    public void addParticles(int n) {
        int amount;
        for (int i = 0; i < amount; ++i) {
            this.particleList.add(Particle.generateParticle());
        }
    }

    public void changeParticles(int n) {
        int amount;
        this.particleList.clear();
        for (int i = 0; i < amount; ++i) {
            this.particleList.add(Particle.generateParticle());
        }
    }

    /*
     * WARNING - void declaration
     */
    public void tick(int n) {
        for (Particle particle : this.particleList) {
            void delta;
            particle.tick((int)delta, SPEED);
        }
    }

    public void render() {
        for (Particle particle : this.particleList) {
            Color color = new Color(ModuleParticles.daColor.getValue().getRed(), ModuleParticles.daColor.getValue().getGreen(), ModuleParticles.daColor.getValue().getBlue(), 255);
            for (Particle particle1 : this.particleList) {
                float distance = particle.getDistanceTo(particle1);
                if (!(particle.getDistanceTo(particle1) < (float)this.dist)) continue;
                float alpha = Math.min(Float.intBitsToFloat(Float.floatToIntBits(171.23094f) ^ 0x7CAB3B1F), Math.min(Float.intBitsToFloat(Float.floatToIntBits(14.271226f) ^ 0x7EE456F1), Float.intBitsToFloat(Float.floatToIntBits(14.363122f) ^ 0x7EE5CF59) - distance / (float)this.dist));
                RenderUtils.prepareGL();
                GL11.glEnable((int)2848);
                GL11.glDisable((int)3553);
                GL11.glLineWidth((float)ModuleParticles.lineWidth.getValue().floatValue());
                GL11.glBegin((int)1);
                GL11.glColor4f((float)((float)ModuleParticles.daColor.getValue().getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.009421679f) ^ 0x7F655D63)), (float)((float)ModuleParticles.daColor.getValue().getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.014374525f) ^ 0x7F148321)), (float)((float)ModuleParticles.daColor.getValue().getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.09082467f) ^ 0x7EC50249)), (float)alpha);
                GL11.glVertex2d((double)particle.getX(), (double)particle.getY());
                GL11.glVertex2d((double)particle1.getX(), (double)particle1.getY());
                GL11.glEnd();
                GL11.glEnable((int)3553);
                RenderUtils.releaseGL();
            }
            RenderUtils.drawCircle(particle.getX(), particle.getY(), particle.getSize(), color.getRGB());
        }
    }

    /*
     * WARNING - void declaration
     */
    public void drawLine(double d, double d2, double d3, double d4, float f) {
        void y1;
        void x1;
        void y;
        void x;
        void width;
        GL11.glDisable((int)3553);
        GL11.glLineWidth((float)width);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x1, (double)y1);
        GL11.glEnd();
        GL11.glEnable((int)3553);
    }
}

