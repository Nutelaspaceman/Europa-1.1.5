/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.util.BlockRenderLayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package com.europa.client.mixins.impl.blocks;

import com.europa.Europa;
import com.europa.api.manager.event.impl.render.EventBlockGetRenderLayer;
import com.europa.client.modules.render.ModuleWallhack;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Block.class})
public class MixinBlock {
    @Inject(method={"shouldSideBeRendered"}, at={@At(value="HEAD")}, cancellable=true)
    public void shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side, CallbackInfoReturnable<Boolean> callback) {
        if (Europa.getModuleManager().isModuleEnabled("Wallhack")) {
            ModuleWallhack.processShouldSideBeRendered((Block)this, blockState, blockAccess, pos, side, callback);
        }
    }

    @Inject(method={"getRenderLayer"}, at={@At(value="HEAD")}, cancellable=true)
    public void getRenderLayer(CallbackInfoReturnable<BlockRenderLayer> callback) {
        EventBlockGetRenderLayer event = new EventBlockGetRenderLayer((Block)this);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCancelled()) {
            callback.cancel();
            callback.setReturnValue(event.getBlockRenderLayer());
        }
    }

    @Inject(method={"getLightValue"}, at={@At(value="HEAD")}, cancellable=true)
    public void getLightValue(CallbackInfoReturnable<Integer> callback) {
        if (Europa.getModuleManager().isModuleEnabled("Wallhack")) {
            ModuleWallhack.processGetLightValue((Block)this, callback);
        }
    }
}

