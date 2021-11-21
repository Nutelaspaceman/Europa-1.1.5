/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 */
package com.europa.api.manager.element;

import com.europa.api.manager.element.Element;
import com.europa.api.manager.event.impl.player.EventMotionUpdate;
import com.europa.api.manager.event.impl.render.EventRender2D;
import com.europa.api.manager.module.Module;
import com.europa.client.elements.ElementArmor;
import com.europa.client.elements.ElementCoordinates;
import com.europa.client.elements.ElementFriends;
import com.europa.client.elements.ElementPlayerViewer;
import com.europa.client.elements.ElementStickyNotes;
import com.europa.client.elements.ElementWatermark;
import com.europa.client.elements.ElementWelcomer;
import java.util.ArrayList;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class ElementManager {
    public static Minecraft mc = Minecraft.getMinecraft();
    public ArrayList<Element> elements;

    public ElementManager() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.elements = new ArrayList();
        this.addElement(new ElementWatermark());
        this.addElement(new ElementWelcomer());
        this.addElement(new ElementCoordinates());
        this.addElement(new ElementStickyNotes());
        this.addElement(new ElementFriends());
        this.addElement(new ElementArmor());
        this.addElement(new ElementPlayerViewer());
        this.elements.sort(Comparator.comparing(Module::getName));
    }

    /*
     * Exception decompiling
     */
    public void addElement(Element var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [21[UNCONDITIONALDOLOOP]], but top level block is 17[TRYBLOCK]
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:429)
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:478)
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:728)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:806)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:258)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:192)
         * org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         * org.benf.cfr.reader.entities.Method.analyse(Method.java:521)
         * org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
         * org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:922)
         * org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:253)
         * org.benf.cfr.reader.Driver.doJar(Driver.java:135)
         * org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:65)
         * org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException(Decompilation failed);
    }

    public ArrayList<Element> getElements() {
        return this.elements;
    }

    /*
     * WARNING - void declaration
     */
    public Element getElement(String string) {
        for (Element module : this.elements) {
            void name;
            if (!module.getName().equalsIgnoreCase((String)name)) continue;
            return module;
        }
        return null;
    }

    /*
     * WARNING - void declaration
     */
    public boolean isElementEnabled(String string) {
        void name;
        Element module = this.elements.stream().filter(arg_0 -> ElementManager.lambda$isElementEnabled$0((String)name, arg_0)).findFirst().orElse(null);
        if (module != null) {
            return module.isToggled();
        }
        return false;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent clientTickEvent) {
        block1: {
            if (ElementManager.mc.player == null) break block1;
            if (ElementManager.mc.world != null) {
                this.elements.stream().filter(Module::isToggled).forEach(Element::onUpdate);
            }
        }
    }

    @SubscribeEvent
    public void onUpdate(EventMotionUpdate eventMotionUpdate) {
        block0: {
            if (ElementManager.mc.player == null || ElementManager.mc.world == null) break block0;
            this.elements.stream().filter(Module::isToggled).forEach(Element::onMotionUpdate);
        }
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent.Post post) {
        block0: {
            void event;
            if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT) break block0;
            this.elements.stream().filter(Module::isToggled).forEach(arg_0 -> ElementManager.lambda$onRender2D$1((RenderGameOverlayEvent.Post)event, arg_0));
            GlStateManager.color((float)Float.intBitsToFloat(Float.floatToIntBits(6.726699f) ^ 0x7F57411E), (float)Float.intBitsToFloat(Float.floatToIntBits(8.872096f) ^ 0x7E8DF41B), (float)Float.intBitsToFloat(Float.floatToIntBits(14.271403f) ^ 0x7EE457AB), (float)Float.intBitsToFloat(Float.floatToIntBits(19.808035f) ^ 0x7E1E76DB));
        }
    }

    @SubscribeEvent
    public void onLogin(FMLNetworkEvent.ClientConnectedToServerEvent clientConnectedToServerEvent) {
        this.elements.stream().filter(Module::isToggled).forEach(Element::onLogin);
    }

    @SubscribeEvent
    public void onLogout(FMLNetworkEvent.ClientDisconnectionFromServerEvent clientDisconnectionFromServerEvent) {
        this.elements.stream().filter(Module::isToggled).forEach(Element::onLogout);
    }

    /*
     * WARNING - void declaration
     */
    public static void lambda$onRender2D$1(RenderGameOverlayEvent.Post post, Element element) {
        RenderGameOverlayEvent.Post event;
        void m;
        m.onRender2D(new EventRender2D(event.getPartialTicks()));
    }

    /*
     * WARNING - void declaration
     */
    public static boolean lambda$isElementEnabled$0(String string, Element element) {
        String name;
        void m;
        return m.getName().equals(name);
    }
}

