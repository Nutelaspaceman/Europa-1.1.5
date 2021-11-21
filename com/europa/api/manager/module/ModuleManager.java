/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 *  org.lwjgl.input.Keyboard
 */
package com.europa.api.manager.module;

import com.europa.api.manager.event.impl.player.EventMotionUpdate;
import com.europa.api.manager.event.impl.render.EventRender2D;
import com.europa.api.manager.event.impl.render.EventRender3D;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.client.modules.client.ModuleColor;
import com.europa.client.modules.client.ModuleDiscordPresence;
import com.europa.client.modules.client.ModuleFont;
import com.europa.client.modules.client.ModuleGUI;
import com.europa.client.modules.client.ModuleHUDEditor;
import com.europa.client.modules.client.ModuleHud;
import com.europa.client.modules.client.ModuleMiddleClick;
import com.europa.client.modules.client.ModuleNotifications;
import com.europa.client.modules.client.ModuleParticles;
import com.europa.client.modules.client.ModuleStreamerMode;
import com.europa.client.modules.combat.ModuleAutoCrystal;
import com.europa.client.modules.combat.ModuleAutoTrap;
import com.europa.client.modules.combat.ModuleCriticals;
import com.europa.client.modules.combat.ModuleCrystalBasePlace;
import com.europa.client.modules.combat.ModuleHoleFill;
import com.europa.client.modules.combat.ModuleKillAura;
import com.europa.client.modules.combat.ModuleSelfFill;
import com.europa.client.modules.combat.ModuleSurround;
import com.europa.client.modules.combat.ModuleSurroundBreaker;
import com.europa.client.modules.misc.ModuleAnnouncer;
import com.europa.client.modules.misc.ModuleAutoFish;
import com.europa.client.modules.misc.ModuleAutoGhastFarmer;
import com.europa.client.modules.misc.ModuleAutoReconnect;
import com.europa.client.modules.misc.ModuleAutoRespawn;
import com.europa.client.modules.misc.ModuleChatModifier;
import com.europa.client.modules.misc.ModuleEntityFinder;
import com.europa.client.modules.misc.ModuleKillsay;
import com.europa.client.modules.misc.ModuleSpawns;
import com.europa.client.modules.misc.ModuleTest;
import com.europa.client.modules.misc.ModuleTimer;
import com.europa.client.modules.misc.ModuleWelcomer;
import com.europa.client.modules.movement.ModuleAnchor;
import com.europa.client.modules.movement.ModuleAntiLevitation;
import com.europa.client.modules.movement.ModuleAntiWeb;
import com.europa.client.modules.movement.ModuleFastFall;
import com.europa.client.modules.movement.ModuleFastSwim;
import com.europa.client.modules.movement.ModuleNoSlow;
import com.europa.client.modules.movement.ModuleSpeed;
import com.europa.client.modules.movement.ModuleSprint;
import com.europa.client.modules.movement.ModuleStep;
import com.europa.client.modules.movement.ModuleVelocity;
import com.europa.client.modules.player.ModuleAntiHunger;
import com.europa.client.modules.player.ModuleAntiVoid;
import com.europa.client.modules.player.ModuleAutoTool;
import com.europa.client.modules.player.ModuleChorusPostpone;
import com.europa.client.modules.player.ModuleFastPlace;
import com.europa.client.modules.player.ModuleMultiTask;
import com.europa.client.modules.player.ModuleNoEntityTrace;
import com.europa.client.modules.player.ModulePacketMine;
import com.europa.client.modules.player.ModuleReach;
import com.europa.client.modules.player.ModuleSpeedMine;
import com.europa.client.modules.remove.ModuleAntiWebSpam;
import com.europa.client.modules.remove.ModuleAutoArmor;
import com.europa.client.modules.remove.ModuleAutoTotem;
import com.europa.client.modules.remove.ModuleBlockOutline;
import com.europa.client.modules.remove.ModuleFakePlayer;
import com.europa.client.modules.remove.ModuleHotbarFill;
import com.europa.client.modules.remove.ModuleIceSpeed;
import com.europa.client.modules.remove.ModulePopCounter;
import com.europa.client.modules.remove.ModuleViewModel;
import com.europa.client.modules.render.ModuleAnimations;
import com.europa.client.modules.render.ModuleBreadCrumbs;
import com.europa.client.modules.render.ModuleCityESP;
import com.europa.client.modules.render.ModuleCrosshair;
import com.europa.client.modules.render.ModuleCrystalChams;
import com.europa.client.modules.render.ModuleCustomFOV;
import com.europa.client.modules.render.ModuleESP;
import com.europa.client.modules.render.ModuleFullBright;
import com.europa.client.modules.render.ModuleGlintModify;
import com.europa.client.modules.render.ModuleHitmarkers;
import com.europa.client.modules.render.ModuleHoleESP;
import com.europa.client.modules.render.ModuleKillEffects;
import com.europa.client.modules.render.ModuleLogoutSpots;
import com.europa.client.modules.render.ModuleModelChanger;
import com.europa.client.modules.render.ModuleNametags;
import com.europa.client.modules.render.ModuleNoRender;
import com.europa.client.modules.render.ModulePlayerChams;
import com.europa.client.modules.render.ModulePopChams;
import com.europa.client.modules.render.ModuleShaderChams;
import com.europa.client.modules.render.ModuleShulkerViewer;
import com.europa.client.modules.render.ModuleSkeleton;
import com.europa.client.modules.render.ModuleSkyColor;
import com.europa.client.modules.render.ModuleTracer;
import com.europa.client.modules.render.ModuleTrails;
import com.europa.client.modules.render.ModuleViewClip;
import com.europa.client.modules.render.ModuleWallhack;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.lwjgl.input.Keyboard;

public class ModuleManager {
    public static Minecraft mc = Minecraft.getMinecraft();
    public ArrayList<Module> modules;

    public ModuleManager() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.modules = new ArrayList();
        this.addModule(new ModuleKillEffects());
        this.addModule(new ModuleSurroundBreaker());
        this.addModule(new ModuleKillAura());
        this.addModule(new ModuleAutoArmor());
        this.addModule(new ModuleAutoCrystal());
        this.addModule(new ModuleAutoTotem());
        this.addModule(new ModuleAutoTrap());
        this.addModule(new ModuleCrystalBasePlace());
        this.addModule(new ModuleCriticals());
        this.addModule(new ModuleHoleFill());
        this.addModule(new ModulePopCounter());
        this.addModule(new ModuleSelfFill());
        this.addModule(new ModuleSurround());
        this.addModule(new ModuleChorusPostpone());
        this.addModule(new ModuleAntiVoid());
        this.addModule(new ModuleAutoTool());
        this.addModule(new ModuleAutoFish());
        this.addModule(new ModuleAntiHunger());
        this.addModule(new ModuleFastPlace());
        this.addModule(new ModuleHotbarFill());
        this.addModule(new ModuleFakePlayer());
        this.addModule(new ModulePacketMine());
        this.addModule(new ModuleReach());
        this.addModule(new ModuleSpeedMine());
        this.addModule(new ModuleMultiTask());
        this.addModule(new ModuleNoEntityTrace());
        this.addModule(new ModuleTest());
        this.addModule(new ModuleKillsay());
        this.addModule(new ModuleAnnouncer());
        this.addModule(new ModuleSpawns());
        this.addModule(new ModuleDiscordPresence());
        this.addModule(new ModuleAutoRespawn());
        this.addModule(new ModuleAutoGhastFarmer());
        this.addModule(new ModuleAutoReconnect());
        this.addModule(new ModuleChatModifier());
        this.addModule(new ModuleEntityFinder());
        this.addModule(new ModuleTimer());
        this.addModule(new ModuleWelcomer());
        this.addModule(new ModuleAnchor());
        this.addModule(new ModuleSprint());
        this.addModule(new ModuleNoSlow());
        this.addModule(new ModuleAntiLevitation());
        this.addModule(new ModuleAntiWeb());
        this.addModule(new ModuleAntiWebSpam());
        this.addModule(new ModuleFastSwim());
        this.addModule(new ModuleIceSpeed());
        this.addModule(new ModuleFastFall());
        this.addModule(new ModuleSpeed());
        this.addModule(new ModuleStep());
        this.addModule(new ModuleVelocity());
        this.addModule(new ModuleAnimations());
        this.addModule(new ModuleHoleESP());
        this.addModule(new ModuleShaderChams());
        this.addModule(new ModuleBreadCrumbs());
        this.addModule(new ModuleGlintModify());
        this.addModule(new ModulePopChams());
        this.addModule(new ModuleModelChanger());
        this.addModule(new ModuleCrosshair());
        this.addModule(new ModuleCityESP());
        this.addModule(new ModuleHitmarkers());
        this.addModule(new ModuleTrails());
        this.addModule(new ModuleWallhack());
        this.addModule(new ModuleCustomFOV());
        this.addModule(new ModuleViewClip());
        this.addModule(new ModuleCrystalChams());
        this.addModule(new ModulePlayerChams());
        this.addModule(new ModuleESP());
        this.addModule(new ModuleFullBright());
        this.addModule(new ModuleNoRender());
        this.addModule(new ModuleSkyColor());
        this.addModule(new ModuleSkeleton());
        this.addModule(new ModuleNametags());
        this.addModule(new ModuleBlockOutline());
        this.addModule(new ModuleLogoutSpots());
        this.addModule(new ModuleShulkerViewer());
        this.addModule(new ModuleTracer());
        this.addModule(new ModuleViewModel());
        this.addModule(new ModuleMiddleClick());
        this.addModule(new ModuleNotifications());
        this.addModule(new ModuleHud());
        this.addModule(new ModuleGUI());
        this.addModule(new ModuleStreamerMode());
        this.addModule(new ModuleColor());
        this.addModule(new ModuleFont());
        this.addModule(new ModuleParticles());
        this.addModule(new ModuleHUDEditor());
        this.modules.sort(Comparator.comparing(Module::getName));
    }

    /*
     * Exception decompiling
     */
    public void addModule(Module var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [29[FORLOOP]], but top level block is 17[TRYBLOCK]
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

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    /*
     * WARNING - void declaration
     */
    public ArrayList<Module> getModules(ModuleCategory moduleCategory) {
        void category;
        return (ArrayList)this.modules.stream().filter(arg_0 -> ModuleManager.lambda$getModules$0((ModuleCategory)category, arg_0)).collect(Collectors.toList());
    }

    /*
     * WARNING - void declaration
     */
    public Module getModule(String string) {
        for (Module module : this.modules) {
            void name;
            if (!module.getName().equalsIgnoreCase((String)name)) continue;
            return module;
        }
        return null;
    }

    /*
     * WARNING - void declaration
     */
    public boolean isModuleEnabled(String string) {
        void name;
        Module module = this.modules.stream().filter(arg_0 -> ModuleManager.lambda$isModuleEnabled$1((String)name, arg_0)).findFirst().orElse(null);
        if (module != null) {
            return module.isToggled();
        }
        return false;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent clientTickEvent) {
        block0: {
            if (ModuleManager.mc.player == null || ModuleManager.mc.world == null) break block0;
            this.modules.stream().filter(Module::isToggled).forEach(Module::onUpdate);
        }
    }

    @SubscribeEvent
    public void onUpdate(EventMotionUpdate eventMotionUpdate) {
        block0: {
            if (ModuleManager.mc.player == null || ModuleManager.mc.world == null) break block0;
            this.modules.stream().filter(Module::isToggled).forEach(Module::onMotionUpdate);
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
            this.modules.stream().filter(Module::isToggled).forEach(arg_0 -> ModuleManager.lambda$onRender2D$2((RenderGameOverlayEvent.Post)event, arg_0));
            GlStateManager.color((float)Float.intBitsToFloat(Float.floatToIntBits(4.4062624f) ^ 0x7F0D001A), (float)Float.intBitsToFloat(Float.floatToIntBits(6.6551237f) ^ 0x7F54F6C6), (float)Float.intBitsToFloat(Float.floatToIntBits(12.824576f) ^ 0x7ECD3177), (float)Float.intBitsToFloat(Float.floatToIntBits(31.08959f) ^ 0x7E78B77B));
        }
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onRender3D(RenderWorldLastEvent renderWorldLastEvent) {
        void event;
        ModuleManager.mc.profiler.startSection("europa");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.shadeModel((int)7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth((float)Float.intBitsToFloat(Float.floatToIntBits(7.7310553f) ^ 0x7F7764CE));
        EventRender3D renderEvent = new EventRender3D(event.getPartialTicks());
        this.modules.stream().filter(Module::isToggled).forEach(arg_0 -> ModuleManager.lambda$onRender3D$3(renderEvent, arg_0));
        GlStateManager.glLineWidth((float)Float.intBitsToFloat(Float.floatToIntBits(10.098111f) ^ 0x7EA191DD));
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        ModuleManager.mc.profiler.endSection();
    }

    @SubscribeEvent
    public void onLogin(FMLNetworkEvent.ClientConnectedToServerEvent clientConnectedToServerEvent) {
        this.modules.stream().filter(Module::isToggled).forEach(Module::onLogin);
    }

    @SubscribeEvent
    public void onLogout(FMLNetworkEvent.ClientDisconnectionFromServerEvent clientDisconnectionFromServerEvent) {
        this.modules.stream().filter(Module::isToggled).forEach(Module::onLogout);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() == 0) {
                return;
            }
            for (Module module : this.modules) {
                if (module.getBind() != Keyboard.getEventKey()) continue;
                module.toggle();
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void lambda$onRender3D$3(EventRender3D eventRender3D, Module module) {
        EventRender3D renderEvent;
        void mm;
        mm.onRender3D(renderEvent);
    }

    /*
     * WARNING - void declaration
     */
    public static void lambda$onRender2D$2(RenderGameOverlayEvent.Post post, Module module) {
        RenderGameOverlayEvent.Post event;
        void m;
        m.onRender2D(new EventRender2D(event.getPartialTicks()));
    }

    /*
     * WARNING - void declaration
     */
    public static boolean lambda$isModuleEnabled$1(String string, Module module) {
        String name;
        void m;
        return m.getName().equals(name);
    }

    /*
     * WARNING - void declaration
     */
    public static boolean lambda$getModules$0(ModuleCategory moduleCategory, Module module) {
        ModuleCategory category;
        void m;
        return m.getCategory().equals((Object)category);
    }
}

