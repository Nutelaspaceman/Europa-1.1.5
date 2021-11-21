/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package com.europa.api.utilities.world;

import com.europa.api.utilities.IMinecraft;
import com.europa.api.utilities.world.BlockUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class HoleUtils
implements IMinecraft {
    public static boolean isBedrockHole(BlockPos blockPos) {
        BlockPos pos;
        boolean retVal = false;
        if (HoleUtils.mc.world.getBlockState(pos).getBlock().equals((Object)Blocks.AIR) && HoleUtils.mc.world.getBlockState(pos.up()).getBlock().equals((Object)Blocks.AIR) && HoleUtils.mc.world.getBlockState(pos.up().up()).getBlock().equals((Object)Blocks.AIR) && HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.BEDROCK) && HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.BEDROCK) && HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.BEDROCK) && HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.BEDROCK) && HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.BEDROCK)) {
            retVal = true;
        }
        return retVal;
    }

    public static boolean isObiHole(BlockPos blockPos) {
        BlockPos pos;
        boolean retVal = false;
        int obiCount = 0;
        if (HoleUtils.mc.world.getBlockState(pos).getBlock().equals((Object)Blocks.AIR) && HoleUtils.mc.world.getBlockState(pos.up()).getBlock().equals((Object)Blocks.AIR) && HoleUtils.mc.world.getBlockState(pos.up().up()).getBlock().equals((Object)Blocks.AIR) && (HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.OBSIDIAN))) {
            if (HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                ++obiCount;
            }
            if (HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                if (HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                    ++obiCount;
                }
                if (HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                    if (HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                        ++obiCount;
                    }
                    if (HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                        if (HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                            ++obiCount;
                        }
                        if (HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                            if (HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                                ++obiCount;
                            }
                            if (obiCount >= 1) {
                                retVal = true;
                            }
                        }
                    }
                }
            }
        }
        return retVal;
    }

    public static boolean isDoubleHole(BlockPos blockPos) {
        for (EnumFacing f : EnumFacing.HORIZONTALS) {
            int offZ;
            BlockPos pos;
            int offX = f.getXOffset();
            if (HoleUtils.mc.world.getBlockState(pos.add(offX, 0, offZ = f.getZOffset())).getBlock() != Blocks.OBSIDIAN) {
                if (HoleUtils.mc.world.getBlockState(pos.add(offX, 0, offZ)).getBlock() != Blocks.BEDROCK) continue;
            }
            if (HoleUtils.mc.world.getBlockState(pos.add(offX * -2, 0, offZ * -2)).getBlock() != Blocks.OBSIDIAN && HoleUtils.mc.world.getBlockState(pos.add(offX * -2, 0, offZ * -2)).getBlock() != Blocks.BEDROCK || HoleUtils.mc.world.getBlockState(pos.add(offX * -1, 0, offZ * -1)).getBlock() != Blocks.AIR) continue;
            if (!HoleUtils.isSafeBlock(pos.add(0, -1, 0))) continue;
            if (!HoleUtils.isSafeBlock(pos.add(offX * -1, -1, offZ * -1))) continue;
            if (offZ == 0) {
                if (HoleUtils.isSafeBlock(pos.add(0, 0, 1))) {
                    if (HoleUtils.isSafeBlock(pos.add(0, 0, -1)) && HoleUtils.isSafeBlock(pos.add(offX * -1, 0, 1))) {
                        if (HoleUtils.isSafeBlock(pos.add(offX * -1, 0, -1))) {
                            return true;
                        }
                    }
                }
            }
            if (offX != 0 || !HoleUtils.isSafeBlock(pos.add(1, 0, 0)) || !HoleUtils.isSafeBlock(pos.add(-1, 0, 0)) || !HoleUtils.isSafeBlock(pos.add(1, 0, offZ * -1))) continue;
            if (!HoleUtils.isSafeBlock(pos.add(-1, 0, offZ * -1))) continue;
            return true;
        }
        return false;
    }

    public static boolean isHole(BlockPos blockPos) {
        boolean retVal;
        block4: {
            BlockPos pos;
            block5: {
                retVal = false;
                if (!HoleUtils.mc.world.getBlockState(pos).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(pos.up()).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(pos.up().up()).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
                if (HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.BEDROCK)) break block5;
                if (!HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
            }
            if ((HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.OBSIDIAN)) && (HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.OBSIDIAN)) && (HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.OBSIDIAN))) {
                retVal = true;
            }
        }
        return retVal;
    }

    public static boolean isPlayerInHole() {
        boolean retVal;
        block4: {
            BlockPos pos;
            block6: {
                block5: {
                    retVal = false;
                    pos = new BlockPos(HoleUtils.mc.player.posX, HoleUtils.mc.player.posY, HoleUtils.mc.player.posZ);
                    if (!HoleUtils.mc.world.getBlockState(pos).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(pos.up()).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
                    if (HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.BEDROCK)) break block5;
                    if (!HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
                }
                if (!HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
                if (HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.BEDROCK)) break block6;
                if (!HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
            }
            if (HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                retVal = true;
            }
        }
        return retVal;
    }

    public static boolean isEntityInHole(Entity entity) {
        boolean retVal;
        block0: {
            Entity entity2;
            retVal = false;
            BlockPos pos = new BlockPos(entity2.posX, entity2.posY, entity2.posZ);
            if (!HoleUtils.mc.world.getBlockState(pos).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(pos.up()).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.OBSIDIAN) || !HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.OBSIDIAN) || !HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.OBSIDIAN) || !HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.OBSIDIAN) || !HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block0;
            retVal = true;
        }
        return retVal;
    }

    public static boolean isPlayerTrapped() {
        boolean retVal = false;
        BlockPos pos = new BlockPos(HoleUtils.mc.player.posX, HoleUtils.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(12.367639570239492) ^ 0x7FD8BC3B40F5C9C9L), HoleUtils.mc.player.posZ);
        if (!HoleUtils.mc.world.getBlockState(pos.up()).getBlock().equals((Object)Blocks.AIR)) {
            retVal = true;
        }
        return retVal;
    }

    public static boolean isEntityTrapped(Entity entity) {
        Entity entity2;
        boolean retVal = false;
        BlockPos pos = new BlockPos(entity2.posX, entity2.posY + Double.longBitsToDouble(Double.doubleToLongBits(8.424052216242812) ^ 0x7FD0D91D5F411E4FL), entity2.posZ);
        if (!HoleUtils.mc.world.getBlockState(pos.up()).getBlock().equals((Object)Blocks.AIR)) {
            retVal = true;
        }
        return retVal;
    }

    public static boolean isInHole(EntityPlayer entityPlayer) {
        boolean retVal;
        block4: {
            BlockPos pos;
            block6: {
                block5: {
                    EntityPlayer player;
                    retVal = false;
                    pos = new BlockPos(player.posX, player.posY, player.posZ);
                    if (!HoleUtils.mc.world.getBlockState(pos).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(pos.up()).getBlock().equals((Object)Blocks.AIR)) break block4;
                    if (HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.BEDROCK)) break block5;
                    if (!HoleUtils.mc.world.getBlockState(pos.down()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
                }
                if (!HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.east()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
                if (!HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.BEDROCK) && !HoleUtils.mc.world.getBlockState(pos.west()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
                if (HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.BEDROCK)) break block6;
                if (!HoleUtils.mc.world.getBlockState(pos.south()).getBlock().equals((Object)Blocks.OBSIDIAN)) break block4;
            }
            if (HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.BEDROCK) || HoleUtils.mc.world.getBlockState(pos.north()).getBlock().equals((Object)Blocks.OBSIDIAN)) {
                retVal = true;
            }
        }
        return retVal;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isSafeBlock(BlockPos blockPos) {
        BlockPos pos;
        if (HoleUtils.mc.world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN) return true;
        if (HoleUtils.mc.world.getBlockState(pos).getBlock() != Blocks.BEDROCK) return false;
        return true;
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d centerPos(double d, double d2, double d3) {
        void posZ;
        void posY;
        double posX;
        return new Vec3d(Math.floor(posX) + Double.longBitsToDouble(Double.doubleToLongBits(2.483631649651979) ^ 0x7FE3DE7A453486B7L), Math.floor((double)posY), Math.floor((double)posZ) + Double.longBitsToDouble(Double.doubleToLongBits(3.847580551695734) ^ 0x7FEEC7D84FF2120EL));
    }

    public static boolean isDoubleBedrockHoleX(BlockPos blockPos) {
        BlockPos blockPos2;
        if (!HoleUtils.mc.world.getBlockState(blockPos2).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(blockPos2.add(1, 0, 0)).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(blockPos2.add(0, 1, 0)).getBlock().equals((Object)Blocks.AIR) && !HoleUtils.mc.world.getBlockState(blockPos2.add(1, 1, 0)).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(blockPos2.add(0, 2, 0)).getBlock().equals((Object)Blocks.AIR) && !HoleUtils.mc.world.getBlockState(blockPos2.add(1, 2, 0)).getBlock().equals((Object)Blocks.AIR)) {
            return false;
        }
        for (BlockPos blockPos22 : new BlockPos[]{blockPos2.add(2, 0, 0), blockPos2.add(1, 0, 1), blockPos2.add(1, 0, -1), blockPos2.add(-1, 0, 0), blockPos2.add(0, 0, 1), blockPos2.add(0, 0, -1), blockPos2.add(0, -1, 0), blockPos2.add(1, -1, 0)}) {
            IBlockState iBlockState = HoleUtils.mc.world.getBlockState(blockPos22);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.BEDROCK) continue;
            return false;
        }
        return true;
    }

    public static boolean isDoubleBedrockHoleZ(BlockPos blockPos) {
        BlockPos blockPos2;
        block5: {
            block3: {
                block4: {
                    if (!HoleUtils.mc.world.getBlockState(blockPos2).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(blockPos2.add(0, 0, 1)).getBlock().equals((Object)Blocks.AIR)) break block3;
                    if (HoleUtils.mc.world.getBlockState(blockPos2.add(0, 1, 0)).getBlock().equals((Object)Blocks.AIR)) break block4;
                    if (!HoleUtils.mc.world.getBlockState(blockPos2.add(0, 1, 1)).getBlock().equals((Object)Blocks.AIR)) break block3;
                }
                if (HoleUtils.mc.world.getBlockState(blockPos2.add(0, 2, 0)).getBlock().equals((Object)Blocks.AIR) || HoleUtils.mc.world.getBlockState(blockPos2.add(0, 2, 1)).getBlock().equals((Object)Blocks.AIR)) break block5;
            }
            return false;
        }
        for (BlockPos blockPos22 : new BlockPos[]{blockPos2.add(0, 0, 2), blockPos2.add(1, 0, 1), blockPos2.add(-1, 0, 1), blockPos2.add(0, 0, -1), blockPos2.add(1, 0, 0), blockPos2.add(-1, 0, 0), blockPos2.add(0, -1, 0), blockPos2.add(0, -1, 1)}) {
            IBlockState iBlockState = HoleUtils.mc.world.getBlockState(blockPos22);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.BEDROCK) continue;
            return false;
        }
        return true;
    }

    public static boolean isDoubleObsidianHoleX(BlockPos blockPos) {
        BlockPos blockPos2;
        block5: {
            block3: {
                block4: {
                    if (!HoleUtils.mc.world.getBlockState(blockPos2).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(blockPos2.add(1, 0, 0)).getBlock().equals((Object)Blocks.AIR)) break block3;
                    if (HoleUtils.mc.world.getBlockState(blockPos2.add(0, 1, 0)).getBlock().equals((Object)Blocks.AIR)) break block4;
                    if (!HoleUtils.mc.world.getBlockState(blockPos2.add(1, 1, 0)).getBlock().equals((Object)Blocks.AIR)) break block3;
                }
                if (HoleUtils.mc.world.getBlockState(blockPos2.add(0, 2, 0)).getBlock().equals((Object)Blocks.AIR) || HoleUtils.mc.world.getBlockState(blockPos2.add(1, 2, 0)).getBlock().equals((Object)Blocks.AIR)) break block5;
            }
            return false;
        }
        for (BlockPos blockPos22 : new BlockPos[]{blockPos2.add(2, 0, 0), blockPos2.add(1, 0, 1), blockPos2.add(1, 0, -1), blockPos2.add(-1, 0, 0), blockPos2.add(0, 0, 1), blockPos2.add(0, 0, -1), blockPos2.add(0, -1, 0), blockPos2.add(1, -1, 0)}) {
            if (BlockUtils.getBlockResistance(blockPos22) == BlockUtils.BlockResistance.Resistant || BlockUtils.getBlockResistance(blockPos22) == BlockUtils.BlockResistance.Unbreakable) continue;
            return false;
        }
        return true;
    }

    public static boolean isDoubleObsidianHoleZ(BlockPos blockPos) {
        BlockPos blockPos2;
        block4: {
            block3: {
                if (!HoleUtils.mc.world.getBlockState(blockPos2).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(blockPos2.add(0, 0, 1)).getBlock().equals((Object)Blocks.AIR) || !HoleUtils.mc.world.getBlockState(blockPos2.add(0, 1, 0)).getBlock().equals((Object)Blocks.AIR) && !HoleUtils.mc.world.getBlockState(blockPos2.add(0, 1, 1)).getBlock().equals((Object)Blocks.AIR)) break block3;
                if (HoleUtils.mc.world.getBlockState(blockPos2.add(0, 2, 0)).getBlock().equals((Object)Blocks.AIR)) break block4;
                if (HoleUtils.mc.world.getBlockState(blockPos2.add(0, 2, 1)).getBlock().equals((Object)Blocks.AIR)) break block4;
            }
            return false;
        }
        for (BlockPos blockPos22 : new BlockPos[]{blockPos2.add(0, 0, 2), blockPos2.add(1, 0, 1), blockPos2.add(-1, 0, 1), blockPos2.add(0, 0, -1), blockPos2.add(1, 0, 0), blockPos2.add(-1, 0, 0), blockPos2.add(0, -1, 0), blockPos2.add(0, -1, 1)}) {
            if (BlockUtils.getBlockResistance(blockPos22) == BlockUtils.BlockResistance.Resistant) continue;
            if (BlockUtils.getBlockResistance(blockPos22) == BlockUtils.BlockResistance.Unbreakable) continue;
            return false;
        }
        return true;
    }
}

