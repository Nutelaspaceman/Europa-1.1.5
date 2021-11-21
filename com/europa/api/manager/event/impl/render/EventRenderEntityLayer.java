/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 */
package com.europa.api.manager.event.impl.render;

import com.europa.api.manager.event.Event;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;

public class EventRenderEntityLayer
extends Event {
    public EntityLivingBase entity;
    public LayerRenderer<?> layer;

    public EventRenderEntityLayer(EntityLivingBase entity, LayerRenderer<?> layer) {
        this.entity = entity;
        this.layer = layer;
    }

    public EntityLivingBase getEntity() {
        return this.entity;
    }

    /*
     * WARNING - void declaration
     */
    public void setEntity(EntityLivingBase entityLivingBase) {
        void entityLivingBase2;
        this.entity = entityLivingBase2;
    }

    public LayerRenderer<?> getLayer() {
        return this.layer;
    }

    /*
     * WARNING - void declaration
     */
    public void setLayer(LayerRenderer<?> layerRenderer) {
        void layerRenderer2;
        this.layer = layerRenderer2;
    }

    public EntityLivingBase component1() {
        return this.entity;
    }

    public LayerRenderer<?> component2() {
        return this.layer;
    }

    /*
     * WARNING - void declaration
     */
    public EventRenderEntityLayer copy(EntityLivingBase entityLivingBase, LayerRenderer<?> layerRenderer) {
        void layer;
        void entity;
        return new EventRenderEntityLayer((EntityLivingBase)entity, (LayerRenderer<?>)layer);
    }

    /*
     * WARNING - void declaration
     */
    public static EventRenderEntityLayer copy$default(EventRenderEntityLayer eventRenderEntityLayer, EntityLivingBase entityLivingBase, LayerRenderer layerRenderer, int n, Object object) {
        LayerRenderer<?> layerRenderer2;
        EntityLivingBase entityLivingBase2;
        EventRenderEntityLayer renderEntityLayerEvent;
        block1: {
            void n2;
            if ((n2 & 1) != 0) {
                entityLivingBase2 = renderEntityLayerEvent.entity;
            }
            if ((n2 & 2) == 0) break block1;
            layerRenderer2 = renderEntityLayerEvent.layer;
        }
        return renderEntityLayerEvent.copy(entityLivingBase2, layerRenderer2);
    }

    public String toString() {
        return "RenderEntityLayerEvent(entity=" + (Object)this.entity + ", layer=" + this.layer + ')';
    }

    public int hashCode() {
        int result2 = this.entity.hashCode();
        result2 = result2 * 31 + this.layer.hashCode();
        return result2;
    }

    /*
     * WARNING - void declaration
     */
    public boolean equals(Object object) {
        void other;
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventRenderEntityLayer)) {
            return false;
        }
        EventRenderEntityLayer renderEntityLayerEvent = (EventRenderEntityLayer)other;
        if (this.entity != renderEntityLayerEvent.entity) {
            return false;
        }
        return this.layer == renderEntityLayerEvent.layer;
    }
}

