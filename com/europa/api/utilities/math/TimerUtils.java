/*
 * Decompiled with CFR 0.150.
 */
package com.europa.api.utilities.math;

public class TimerUtils {
    public long current = System.currentTimeMillis();

    public void reset() {
        this.current = System.currentTimeMillis();
    }

    /*
     * WARNING - void declaration
     */
    public boolean hasReached(long l) {
        void var1;
        return System.currentTimeMillis() - this.current >= var1;
    }

    /*
     * WARNING - void declaration
     */
    public boolean sleep(long l) {
        void var1;
        if (this.time() >= var1) {
            this.reset();
            return true;
        }
        return false;
    }

    public long getTimePassed() {
        return System.currentTimeMillis() - this.current;
    }

    public long time() {
        return System.currentTimeMillis() - this.current;
    }

    /*
     * WARNING - void declaration
     */
    public boolean hasReached(long l, boolean bl) {
        void var1;
        void var3;
        if (var3 != false) {
            this.reset();
        }
        return System.currentTimeMillis() - this.current >= var1;
    }

    /*
     * WARNING - void declaration
     */
    public boolean passed(double d) {
        void ms;
        return (double)(System.currentTimeMillis() - this.current) >= ms;
    }
}

