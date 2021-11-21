/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSnow
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.europa.api.utilities.world;

import com.europa.api.utilities.IMinecraft;
import com.europa.api.utilities.math.MathUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockUtils
implements IMinecraft {
    public static List<Block> blackList = Arrays.asList(new Block[]{Blocks.ENDER_CHEST, Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER});
    public static List<Block> shulkerList = Arrays.asList(new Block[]{Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX});
    public static BlockPos[] SURROUND = new BlockPos[]{new BlockPos(0, -1, 0), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0), new BlockPos(0, 0, -1)};

    public static boolean canBeClicked(BlockPos blockPos) {
        BlockPos var0;
        return BlockUtils.mc.world.getBlockState(var0).getBlock().canCollideCheck(BlockUtils.mc.world.getBlockState(var0), false);
    }

    public static float[] getLegitRotations(Vec3d vec3d) {
        Vec3d var0;
        Vec3d var1 = new Vec3d(BlockUtils.mc.player.posX, BlockUtils.mc.player.posY + (double)BlockUtils.mc.player.getEyeHeight(), BlockUtils.mc.player.posZ);
        double var2 = var0.x - var1.x;
        double var4 = var0.y - var1.y;
        double var6 = var0.z - var1.z;
        double var8 = Math.sqrt(var2 * var2 + var6 * var6);
        float var10 = (float)Math.toDegrees(Math.atan2(var6, var2)) - Float.intBitsToFloat(Float.floatToIntBits(0.0919861f) ^ 0x7F086335);
        float var11 = (float)(-Math.toDegrees(Math.atan2(var4, var8)));
        return new float[]{BlockUtils.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(var10 - BlockUtils.mc.player.rotationYaw)), BlockUtils.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(var11 - BlockUtils.mc.player.rotationPitch))};
    }

    public static void faceVectorPacketInstant(Vec3d vec3d) {
        Vec3d var0;
        float[] var1 = BlockUtils.getLegitRotations(var0);
        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(var1[0], var1[1], BlockUtils.mc.player.onGround));
    }

    /*
     * WARNING - void declaration
     */
    public static boolean placeBlockOnHole(BlockPos blockPos, boolean bl) {
        void swing;
        BlockPos pos;
        EnumFacing side = BlockUtils.getFirstFacing(pos);
        if (side == null) {
            return false;
        }
        BlockPos neighbour = pos.offset(side);
        EnumFacing opposite = side.getOpposite();
        Vec3d hitVec = new Vec3d((Vec3i)neighbour).add(Double.longBitsToDouble(Double.doubleToLongBits(14.310729552844377) ^ 0x7FCC9F17F1A6BC0FL), Double.longBitsToDouble(Double.doubleToLongBits(2.5630748913417127) ^ 0x7FE4812D689C175EL), Double.longBitsToDouble(Double.doubleToLongBits(15.030794407170665) ^ 0x7FCE0FC448D7653BL)).add(new Vec3d(opposite.getDirectionVec()).scale(Double.longBitsToDouble(Double.doubleToLongBits(3.1349750344517173) ^ 0x7FE9146DCA75F909L)));
        if (!BlockUtils.mc.player.isSneaking()) {
            BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtils.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtils.mc.player.setSneaking(true);
        }
        BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, neighbour, opposite, hitVec, EnumHand.MAIN_HAND);
        if (swing != false) {
            BlockUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtils.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        return true;
    }

    public static EnumFacing getFirstFacing(BlockPos blockPos) {
        BlockPos pos;
        Iterator<EnumFacing> iterator = BlockUtils.getPossibleSides(pos).iterator();
        if (iterator.hasNext()) {
            EnumFacing facing = iterator.next();
            return facing;
        }
        return null;
    }

    public static List<EnumFacing> getPossibleSides(BlockPos blockPos) {
        ArrayList<EnumFacing> facings = new ArrayList<EnumFacing>();
        for (EnumFacing side : EnumFacing.values()) {
            IBlockState blockState;
            BlockPos pos;
            BlockPos neighbour = pos.offset(side);
            if (BlockUtils.mc.world.getBlockState(neighbour) == null) {
                return facings;
            }
            if (BlockUtils.mc.world.getBlockState(neighbour).getBlock() == null) {
                return facings;
            }
            if (!BlockUtils.mc.world.getBlockState(neighbour).getBlock().canCollideCheck(BlockUtils.mc.world.getBlockState(neighbour), false) || (blockState = BlockUtils.mc.world.getBlockState(neighbour)).getMaterial().isReplaceable()) continue;
            facings.add(side);
        }
        return facings;
    }

    /*
     * WARNING - void declaration
     */
    public static List<BlockPos> getNearbyBlocks(EntityPlayer entityPlayer, double d, boolean bl) {
        EntityPlayer player;
        void motion;
        void blockRange;
        ArrayList<BlockPos> nearbyBlocks = new ArrayList<BlockPos>();
        int range = (int)MathUtils.roundToPlaces((double)blockRange, 0);
        if (motion != false) {
            player.getPosition().add(new Vec3i(player.motionX, player.motionY, player.motionZ));
        }
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range - range / 2; ++y) {
                for (int z = -range; z <= range; ++z) {
                    nearbyBlocks.add(player.getPosition().add(x, y, z));
                }
            }
        }
        return nearbyBlocks;
    }

    /*
     * WARNING - void declaration
     */
    public static void placeBlock(BlockPos blockPos, boolean bl, boolean bl2) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            void swing;
            void packet;
            BlockPos blockPos2;
            if (BlockUtils.mc.world.getBlockState(blockPos2.offset(enumFacing)).getBlock().equals((Object)Blocks.AIR) || BlockUtils.isIntercepted(blockPos2)) continue;
            if (packet != false) {
                BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos2.offset(enumFacing), enumFacing.getOpposite(), EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(2.7271183f) ^ 0x7F2E891B), Float.intBitsToFloat(Float.floatToIntBits(3.827434f) ^ 0x7F74F4AE), Float.intBitsToFloat(Float.floatToIntBits(30.020975f) ^ 0x7EF02AF5)));
            } else {
                BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, blockPos2.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos2), EnumHand.MAIN_HAND);
            }
            if (swing != false) {
                BlockUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            return;
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void placeBlock(BlockPos blockPos, EnumHand enumHand, boolean bl) {
        void packet;
        void hand;
        BlockPos position;
        if (!BlockUtils.mc.world.getBlockState(position).getBlock().isReplaceable((IBlockAccess)BlockUtils.mc.world, position)) {
            return;
        }
        if (BlockUtils.getPlaceableSide(position) == null) {
            return;
        }
        BlockUtils.clickBlock(position, BlockUtils.getPlaceableSide(position), (EnumHand)hand, (boolean)packet);
        BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketAnimation((EnumHand)hand));
    }

    /*
     * WARNING - void declaration
     */
    public static void clickBlock(BlockPos blockPos, EnumFacing enumFacing, EnumHand enumHand, boolean bl) {
        void hand;
        void side;
        BlockPos position;
        void packet;
        if (packet != false) {
            BlockUtils.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(position.offset((EnumFacing)side), side.getOpposite(), (EnumHand)hand, Float.intBitsToFloat(Float.floatToIntBits(17.735476f) ^ 0x7E8DE241), Float.intBitsToFloat(Float.floatToIntBits(26.882437f) ^ 0x7ED70F3B), Float.intBitsToFloat(Float.floatToIntBits(3.0780227f) ^ 0x7F44FE53)));
        } else {
            BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, position.offset((EnumFacing)side), side.getOpposite(), new Vec3d((Vec3i)position), (EnumHand)hand);
        }
    }

    public static boolean isIntercepted(BlockPos blockPos) {
        for (Entity entity : BlockUtils.mc.world.loadedEntityList) {
            BlockPos blockPos2;
            if (entity instanceof EntityItem || entity instanceof EntityEnderCrystal || !new AxisAlignedBB(blockPos2).intersects(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    /*
     * WARNING - void declaration
     */
    public static boolean isPositionPlaceable(BlockPos blockPos, boolean bl, boolean bl2) {
        void sideCheck;
        void entityCheck;
        BlockPos position;
        if (!BlockUtils.mc.world.getBlockState(position).getBlock().isReplaceable((IBlockAccess)BlockUtils.mc.world, position)) {
            return false;
        }
        if (entityCheck != false) {
            for (Entity entity : BlockUtils.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(position))) {
                if (entity instanceof EntityItem) continue;
                if (entity instanceof EntityXPOrb) continue;
                return false;
            }
        }
        if (sideCheck != false) {
            return BlockUtils.getPlaceableSide(position) != null;
        }
        return true;
    }

    /*
     * WARNING - void declaration
     */
    public static boolean isPositionPlaceable(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3) {
        void sideCheck;
        void entityCheck;
        BlockPos position;
        if (!BlockUtils.mc.world.getBlockState(position).getBlock().isReplaceable((IBlockAccess)BlockUtils.mc.world, position)) {
            return false;
        }
        if (entityCheck != false) {
            for (Entity entity : BlockUtils.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(position))) {
                void ignoreCrystals;
                if (entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityEnderCrystal && ignoreCrystals != false) continue;
                return false;
            }
        }
        if (sideCheck != false) {
            return BlockUtils.getPlaceableSide(position) != null;
        }
        return true;
    }

    /*
     * WARNING - void declaration
     */
    public static boolean isPositionPlaceable(BlockPos blockPos, boolean bl, double d) {
        void entityCheck;
        BlockPos pos;
        Block block = BlockUtils.mc.world.getBlockState(pos).getBlock();
        if (!(block instanceof BlockAir)) {
            if (!(block instanceof BlockLiquid)) {
                if (!(block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush)) {
                    if (!(block instanceof BlockSnow)) {
                        return false;
                    }
                }
            }
        }
        if (entityCheck != false) {
            for (Entity entity : BlockUtils.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(pos))) {
                void distance;
                if ((double)BlockUtils.mc.player.getDistance(entity) > distance || entity instanceof EntityItem || entity instanceof EntityXPOrb) continue;
                return false;
            }
        }
        return true;
    }

    public static EnumFacing getPlaceableSide(BlockPos blockPos) {
        for (EnumFacing side : EnumFacing.values()) {
            IBlockState blockState;
            BlockPos pos;
            BlockPos neighbour = pos.offset(side);
            if (!BlockUtils.mc.world.getBlockState(neighbour).getBlock().canCollideCheck(BlockUtils.mc.world.getBlockState(neighbour), false) || (blockState = BlockUtils.mc.world.getBlockState(neighbour)).getMaterial().isReplaceable()) continue;
            return side;
        }
        return null;
    }

    public static BlockResistance getBlockResistance(BlockPos blockPos) {
        BlockPos block;
        block8: {
            block7: {
                if (BlockUtils.mc.world.isAirBlock(block)) {
                    return BlockResistance.Blank;
                }
                if (BlockUtils.mc.world.getBlockState(block).getBlock().getBlockHardness(BlockUtils.mc.world.getBlockState(block), (World)BlockUtils.mc.world, block) != Float.intBitsToFloat(Float.floatToIntBits(-6.673269f) ^ 0x7F558B6B)) {
                    if (!(BlockUtils.mc.world.getBlockState(block).getBlock().equals((Object)Blocks.OBSIDIAN) || BlockUtils.mc.world.getBlockState(block).getBlock().equals((Object)Blocks.ANVIL) || BlockUtils.mc.world.getBlockState(block).getBlock().equals((Object)Blocks.ENCHANTING_TABLE) || BlockUtils.mc.world.getBlockState(block).getBlock().equals((Object)Blocks.ENDER_CHEST))) {
                        return BlockResistance.Breakable;
                    }
                }
                if (BlockUtils.mc.world.getBlockState(block).getBlock().equals((Object)Blocks.OBSIDIAN) || BlockUtils.mc.world.getBlockState(block).getBlock().equals((Object)Blocks.ANVIL) || BlockUtils.mc.world.getBlockState(block).getBlock().equals((Object)Blocks.ENCHANTING_TABLE)) break block7;
                if (!BlockUtils.mc.world.getBlockState(block).getBlock().equals((Object)Blocks.ENDER_CHEST)) break block8;
            }
            return BlockResistance.Resistant;
        }
        if (BlockUtils.mc.world.getBlockState(block).getBlock().equals((Object)Blocks.BEDROCK)) {
            return BlockResistance.Unbreakable;
        }
        return null;
    }

    public static enum BlockResistance {
        Blank,
        Breakable,
        Resistant,
        Unbreakable;

    }
}

