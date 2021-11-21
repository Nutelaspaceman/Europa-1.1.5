/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.europa.client.modules.render;

import com.europa.api.manager.event.impl.render.EventRender3D;
import com.europa.api.manager.event.impl.render.EventRenderEntityLayer;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueEnum;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleShaderChams
extends Module {
    public ValueEnum mode = new ValueEnum("Mode", "Mode", "", modes.Smoke);
    public static boolean renderNameTags;

    public ModuleShaderChams() {
        super("ShaderChams", "Shader Chams", "Draws a shader on players to make them look amazing.", ModuleCategory.RENDER);
    }

    /*
     * Exception decompiling
     */
    @Override
    public void onRender3D(EventRender3D var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 32[UNCONDITIONALDOLOOP]
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

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onRenderEntityLayer(EventRenderEntityLayer eventRenderEntityLayer) {
        block0: {
            void event;
            if (renderNameTags) break block0;
            event.setCancelled(true);
        }
    }

    public static enum modes {
        Smoke,
        Aqua,
        Flow,
        Red,
        Outline;

    }
}

