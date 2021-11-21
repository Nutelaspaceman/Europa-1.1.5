/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemSword
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.network.play.server.SPacketSpawnObject
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.GameType
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.europa.client.modules.combat;

import com.europa.Europa;
import com.europa.api.manager.event.impl.network.EventPacket;
import com.europa.api.manager.event.impl.render.EventRender3D;
import com.europa.api.manager.event.impl.world.EventCrystalAttack;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueBoolean;
import com.europa.api.manager.value.impl.ValueColor;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.api.manager.value.impl.ValueNumber;
import com.europa.api.utilities.crystal.CrystalUtils;
import com.europa.api.utilities.entity.DamageUtils;
import com.europa.api.utilities.entity.InventoryUtils;
import com.europa.api.utilities.math.MathUtils;
import com.europa.api.utilities.render.RenderUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleAutoCrystal
extends Module {
    public static ModuleAutoCrystal INSTANCE;
    public EntityEnderCrystal targetCrystal = null;
    public BlockPos targetPosition = null;
    public List<Integer> blacklist = new ArrayList<Integer>();
    public List<FadePosition> fadePositions = new ArrayList<FadePosition>();
    public static BlockPos renderPosition;
    public float damageNumber = Float.intBitsToFloat(Float.floatToIntBits(5.4387783E37f) ^ 0x7E23AAD3);
    public static boolean isSequential;
    public EntityPlayer target = null;
    public int breakTicks;
    public int placeTicks;
    public static ValueBoolean breakMode;
    public static ValueNumber breakDelay;
    public static ValueNumber breakRange;
    public static ValueNumber breakWallsRange;
    public static ValueBoolean antiWeakness;
    public static ValueBoolean inhibit;
    public static ValueBoolean sequential;
    public static ValueBoolean sync;
    public static ValueBoolean place;
    public static ValueNumber placeDelay;
    public static ValueNumber placeRange;
    public static ValueNumber placeWallsRange;
    public static ValueBoolean placeUnderBlock;
    public static ValueEnum switchMode;
    public static ValueEnum multiPlace;
    public static ValueBoolean holePlace;
    public static ValueBoolean rotation;
    public static ValueEnum timing;
    public static ValueNumber targetRange;
    public static ValueEnum swing;
    public static ValueBoolean packetSwing;
    public static ValueNumber minimumDamage;
    public static ValueNumber maxSelfDamage;
    public static ValueBoolean facePlace;
    public static ValueNumber facePlaceHealth;
    public static ValueBoolean armorBreaker;
    public static ValueNumber armorPercent;
    public static ValueEnum render;
    public static ValueEnum fill;
    public static ValueEnum outline;
    public static ValueBoolean renderDamage;
    public static ValueNumber shrinkSpeed;
    public static ValueNumber fadeDuration;
    public static ValueNumber lineWidth;
    public static ValueColor fillColor;
    public static ValueColor outlineColor;

    public ModuleAutoCrystal() {
        super("AutoCrystal", "Auto Crystal", "Automatically places and breaks crystals in order to kill the enemies.", ModuleCategory.COMBAT);
        INSTANCE = this;
    }

    @Override
    public void onMotionUpdate() {
        this.doAutoCrystal();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void doAutoCrystal() {
        if (ModuleAutoCrystal.mc.player == null) return;
        if (ModuleAutoCrystal.mc.world == null) {
            return;
        }
        maxCrystalDamage = Double.longBitsToDouble(Double.doubleToLongBits(1.4087661019685725E308) ^ 9216919749093210788L);
        maxPositionDamage = Double.longBitsToDouble(Double.doubleToLongBits(2.5986735741899057E307) ^ 9206062218073306167L);
        if (!ModuleAutoCrystal.place.getValue() || this.placeTicks++ <= ModuleAutoCrystal.placeDelay.getValue().intValue()) ** GOTO lbl40
        this.placeTicks = 0;
        positions = NonNullList.create();
        for (BlockPos position : CrystalUtils.getSphere(ModuleAutoCrystal.placeRange.getValue().floatValue(), true, false)) {
            if (ModuleAutoCrystal.mc.world.getBlockState(position).getBlock() == Blocks.AIR || !CrystalUtils.canPlaceCrystal(position, ModuleAutoCrystal.placeUnderBlock.getValue(), ModuleAutoCrystal.multiPlace.getValue().equals((Object)MultiPlaceModes.Static) != false || ModuleAutoCrystal.multiPlace.getValue().equals((Object)MultiPlaceModes.Dynamic) != false && CrystalUtils.isEntityMoving((EntityLivingBase)ModuleAutoCrystal.mc.player) != false && CrystalUtils.isEntityMoving((EntityLivingBase)this.target) != false, ModuleAutoCrystal.holePlace.getValue())) continue;
            positions.add((Object)position);
        }
        var6_5 = ModuleAutoCrystal.mc.world.playerEntities.iterator();
        block1: while (true) lbl-1000:
        // 3 sources

        {
            block17: {
                block16: {
                    if (!var6_5.hasNext()) break block16;
                    player = (EntityPlayer)var6_5.next();
                    if (ModuleAutoCrystal.mc.player.getDistanceSq((Entity)player) > (double)MathUtils.square(ModuleAutoCrystal.targetRange.getValue().floatValue()) || player == ModuleAutoCrystal.mc.player || Europa.FRIEND_MANAGER.isFriend(player.getName()) || player.isDead || player.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(2.0385629E38f) ^ 2132368715)) ** GOTO lbl-1000
                    break block17;
                }
                if (this.targetPosition != null) {
                    slot = InventoryUtils.findItem(Items.END_CRYSTAL, 0, 9);
                    lastSlot = ModuleAutoCrystal.mc.player.inventory.currentItem;
                    if (!ModuleAutoCrystal.switchMode.getValue().equals((Object)SwitchModes.None) && slot != -1) {
                        InventoryUtils.switchSlot(slot, ModuleAutoCrystal.switchMode.getValue().equals((Object)SwitchModes.Silent));
                    }
                    if (ModuleAutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || ModuleAutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL || ModuleAutoCrystal.switchMode.getValue().equals((Object)SwitchModes.Silent)) {
                        ModuleAutoCrystal.renderPosition = this.targetPosition;
                        result = ModuleAutoCrystal.mc.world.rayTraceBlocks(new Vec3d(ModuleAutoCrystal.mc.player.posX, ModuleAutoCrystal.mc.player.posY + (double)ModuleAutoCrystal.mc.player.getEyeHeight(), ModuleAutoCrystal.mc.player.posZ), new Vec3d((double)this.targetPosition.getX() + Double.longBitsToDouble(Double.doubleToLongBits(2.0585482766064893) ^ 9214496676598388901L), (double)this.targetPosition.getY() - Double.longBitsToDouble(Double.doubleToLongBits(18.64274749914699) ^ 9210605105263438863L), (double)this.targetPosition.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(3.2686479786919134) ^ 9217221578882085433L)));
                        v0 = facing = result == null || result.sideHit == null ? EnumFacing.UP : result.sideHit;
                        ModuleAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.targetPosition, facing, ModuleAutoCrystal.switchMode.getValue().equals((Object)SwitchModes.Silent) != false ? EnumHand.MAIN_HAND : (ModuleAutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND), Float.intBitsToFloat(Float.floatToIntBits(29.637854f) ^ 2129467987), Float.intBitsToFloat(Float.floatToIntBits(3.3075676f) ^ 2136190768), Float.intBitsToFloat(Float.floatToIntBits(25347.998f) ^ 2043021311)));
                        ModuleAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(ModuleAutoCrystal.switchMode.getValue().equals((Object)SwitchModes.Silent) != false ? EnumHand.MAIN_HAND : (ModuleAutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND)));
                    } else {
                        ModuleAutoCrystal.renderPosition = null;
                    }
                    if (ModuleAutoCrystal.switchMode.getValue().equals((Object)SwitchModes.Silent) && lastSlot != -1) {
                        InventoryUtils.switchSlot(lastSlot, ModuleAutoCrystal.switchMode.getValue().equals((Object)SwitchModes.Silent));
                    }
                } else {
                    ModuleAutoCrystal.renderPosition = null;
                }
                this.targetPosition = null;
lbl40:
                // 2 sources

                if (ModuleAutoCrystal.breakMode.getValue() == false) return;
                if (this.breakTicks++ <= ModuleAutoCrystal.breakDelay.getValue().intValue()) return;
                this.breakTicks = 0;
                positions = ModuleAutoCrystal.mc.world.playerEntities.iterator();
                break;
            }
            var8_12 = positions.iterator();
            while (true) {
                if (!var8_12.hasNext()) continue block1;
                position = (BlockPos)var8_12.next();
                targetDamage = this.filterPosition(position, player);
                if (targetDamage == Float.intBitsToFloat(Float.floatToIntBits(-4.9015f) ^ 2132597015) || !((double)targetDamage > maxPositionDamage)) continue;
                maxPositionDamage = targetDamage;
                this.targetPosition = position;
                this.damageNumber = targetDamage;
                this.target = player;
            }
            break;
        }
        block3: while (true) lbl-1000:
        // 3 sources

        {
            block19: {
                block18: {
                    if (!positions.hasNext()) break block18;
                    player = (EntityPlayer)positions.next();
                    if (ModuleAutoCrystal.mc.player.getDistanceSq((Entity)player) > (double)MathUtils.square(ModuleAutoCrystal.targetRange.getValue().floatValue()) || player == ModuleAutoCrystal.mc.player || Europa.FRIEND_MANAGER.isFriend(player.getName()) || player.isDead || player.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(1.0582841E38f) ^ 2124364703)) ** GOTO lbl-1000
                    break block19;
                }
                if (this.targetCrystal == null) return;
                swordSlot = InventoryUtils.findItem(Items.DIAMOND_SWORD, 0, 9);
                appleSlot = InventoryUtils.findItem(Items.GOLDEN_APPLE, 0, 9);
                if (ModuleAutoCrystal.antiWeakness.getValue() && ModuleAutoCrystal.mc.player.isPotionActive(MobEffects.WEAKNESS) && swordSlot != -1) {
                    if (!(ModuleAutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword)) {
                        InventoryUtils.switchSlot(swordSlot, false);
                    }
                } else if (ModuleAutoCrystal.antiWeakness.getValue() && !ModuleAutoCrystal.mc.player.isPotionActive(MobEffects.WEAKNESS) && appleSlot != -1 && ModuleAutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) {
                    InventoryUtils.switchSlot(appleSlot, false);
                }
                ModuleAutoCrystal.mc.playerController.attackEntity((EntityPlayer)ModuleAutoCrystal.mc.player, (Entity)this.targetCrystal);
                this.swingItem();
                this.targetCrystal = null;
                return;
            }
            var7_11 = new ArrayList<E>(ModuleAutoCrystal.mc.world.loadedEntityList).iterator();
            while (true) {
                if (!var7_11.hasNext()) continue block3;
                entity = (Entity)var7_11.next();
                if (!(entity instanceof EntityEnderCrystal) || this.blacklist.contains((crystal = (EntityEnderCrystal)entity).getEntityId()) && ModuleAutoCrystal.inhibit.getValue() || (targetDamage = (double)this.filterCrystal(crystal, player)) == Double.longBitsToDouble(Double.doubleToLongBits(-10.430420888648806) ^ 9211229443298723349L) || !(targetDamage > maxCrystalDamage)) continue;
                maxCrystalDamage = targetDamage;
                this.targetCrystal = crystal;
                this.target = player;
            }
            break;
        }
    }

    @Override
    public void onRender3D(EventRender3D eventRender3D) {
        block9: {
            if (render.getValue().equals((Object)RenderModes.None)) break block9;
            if (render.getValue().equals((Object)RenderModes.Normal) || render.getValue().equals((Object)RenderModes.Fade) || render.getValue() == RenderModes.Size) {
                if (renderPosition != null) {
                    if (render.getValue() == RenderModes.Fade || render.getValue() == RenderModes.Size) {
                        this.fadePositions.removeIf(ModuleAutoCrystal::lambda$onRender3D$0);
                        this.fadePositions.add(new FadePosition(renderPosition));
                    }
                    if (fill.getValue().equals((Object)Renders.Normal)) {
                        RenderUtils.drawBox(renderPosition, fillColor.getValue());
                    }
                    if (outline.getValue().equals((Object)Renders.Normal)) {
                        RenderUtils.drawBlockOutline(new AxisAlignedBB((double)renderPosition.getX() - ModuleAutoCrystal.mc.getRenderManager().viewerPosX, (double)renderPosition.getY() - ModuleAutoCrystal.mc.getRenderManager().viewerPosY, (double)renderPosition.getZ() - ModuleAutoCrystal.mc.getRenderManager().viewerPosZ, (double)(renderPosition.getX() + 1) - ModuleAutoCrystal.mc.getRenderManager().viewerPosX, (double)(renderPosition.getY() + 1) - ModuleAutoCrystal.mc.getRenderManager().viewerPosY, (double)(renderPosition.getZ() + 1) - ModuleAutoCrystal.mc.getRenderManager().viewerPosZ), outlineColor.getValue(), lineWidth.getValue().floatValue());
                    }
                }
                if (render.getValue().equals((Object)RenderModes.Fade)) {
                    this.fadePositions.forEach(ModuleAutoCrystal::lambda$onRender3D$1);
                    this.fadePositions.removeIf(ModuleAutoCrystal::lambda$onRender3D$2);
                } else if (render.getValue().equals((Object)RenderModes.Size)) {
                    this.fadePositions.stream().distinct().forEach(ModuleAutoCrystal::lambda$onRender3D$3);
                    this.fadePositions.removeIf(ModuleAutoCrystal::lambda$onRender3D$4);
                }
            }
            if (renderDamage.getValue() && renderPosition != null) {
                CrystalUtils.drawText(renderPosition, (Math.floor(this.damageNumber) == (double)this.damageNumber ? Integer.valueOf((int)this.damageNumber) : String.format("%.1f", Float.valueOf(this.damageNumber))) + "");
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onCrystalAttack(EventCrystalAttack eventCrystalAttack) {
        void event;
        this.blacklist.add(event.getEntityID());
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onPacketReceive(EventPacket.Receive receive) {
        SPacketSoundEffect packet;
        void event;
        if (event.getPacket() instanceof SPacketSoundEffect && sync.getValue()) {
            packet = (SPacketSoundEffect)event.getPacket();
            if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                for (Entity entity : new ArrayList(ModuleAutoCrystal.mc.world.loadedEntityList)) {
                    if (!(entity instanceof EntityEnderCrystal) || !(entity.getDistanceSq(packet.getX(), packet.getY(), packet.getZ()) <= Double.longBitsToDouble(Double.doubleToLongBits(0.03533007623236061) ^ 0x7FE016C8A3F762CFL))) continue;
                    entity.setDead();
                }
            }
        }
        if (event.getPacket() instanceof SPacketSpawnObject && sequential.getValue()) {
            packet = (SPacketSpawnObject)event.getPacket();
            if (this.target == null) {
                return;
            }
            if (packet.getType() == 51 && breakMode.getValue()) {
                EntityEnderCrystal crystal = new EntityEnderCrystal((World)ModuleAutoCrystal.mc.world, packet.getX(), packet.getY(), packet.getZ());
                if (this.target != null && this.filterCrystal(crystal, this.target) != Float.intBitsToFloat(Float.floatToIntBits(-26.224195f) ^ 0x7E51CB27)) {
                    if (this.blacklist.contains(packet.getEntityID())) {
                        return;
                    }
                    isSequential = true;
                    CPacketUseEntity crystalPacket = new CPacketUseEntity();
                    crystalPacket.entityId = packet.getEntityID();
                    crystalPacket.action = CPacketUseEntity.Action.ATTACK;
                    ModuleAutoCrystal.mc.player.connection.sendPacket((Packet)crystalPacket);
                    if (ModuleAutoCrystal.mc.playerController.getCurrentGameType() != GameType.SPECTATOR) {
                        ModuleAutoCrystal.mc.player.resetCooldown();
                    }
                    this.swingItem();
                    this.blacklist.add(crystalPacket.entityId);
                    isSequential = false;
                }
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public float filterCrystal(EntityEnderCrystal entityEnderCrystal, EntityPlayer entityPlayer) {
        void target;
        void crystal;
        if (ModuleAutoCrystal.mc.player.canEntityBeSeen((Entity)crystal) ? ModuleAutoCrystal.mc.player.getDistanceSq((Entity)crystal) > (double)MathUtils.square(breakRange.getValue().floatValue()) : ModuleAutoCrystal.mc.player.getDistanceSq((Entity)crystal) > (double)MathUtils.square(breakWallsRange.getValue().floatValue())) {
            return Float.intBitsToFloat(Float.floatToIntBits(-5.0406475f) ^ 0x7F214CFC);
        }
        if (crystal.isDead) {
            return Float.intBitsToFloat(Float.floatToIntBits(-208.54588f) ^ 0x7CD08BBF);
        }
        float targetDamage = DamageUtils.calculateDamage(crystal.posX, crystal.posY, crystal.posZ, (EntityLivingBase)target);
        float selfDamage = DamageUtils.calculateDamage(crystal.posX, crystal.posY, crystal.posZ, (EntityLivingBase)ModuleAutoCrystal.mc.player);
        return this.returnDamage((EntityPlayer)target, targetDamage, selfDamage);
    }

    /*
     * WARNING - void declaration
     */
    public float filterPosition(BlockPos blockPos, EntityPlayer entityPlayer) {
        void target;
        void position;
        if (CrystalUtils.canSeePos((BlockPos)position) ? ModuleAutoCrystal.mc.player.getDistanceSq((BlockPos)position) > (double)MathUtils.square(placeRange.getValue().floatValue()) : ModuleAutoCrystal.mc.player.getDistanceSq((BlockPos)position) > (double)MathUtils.square(placeWallsRange.getValue().floatValue())) {
            return Float.intBitsToFloat(Float.floatToIntBits(-7.1987925f) ^ 0x7F665C82);
        }
        float targetDamage = DamageUtils.calculateDamage((double)position.getX() + Double.longBitsToDouble(Double.doubleToLongBits(2.926604140248566) ^ 0x7FE769AF6E75A574L), (double)position.getY() + Double.longBitsToDouble(Double.doubleToLongBits(10.872572781808893) ^ 0x7FD5BEC1DC127F75L), (double)position.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(20.94846926721453) ^ 0x7FD4F2CEE1C3F28FL), (EntityLivingBase)target);
        float selfDamage = DamageUtils.calculateDamage((double)position.getX() + Double.longBitsToDouble(Double.doubleToLongBits(3.800241793394989) ^ 0x7FEE66E52B5C30E1L), (double)position.getY() + Double.longBitsToDouble(Double.doubleToLongBits(8.346756871161473) ^ 0x7FD0B18A1DDA9A87L), (double)position.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(16.53449891037378) ^ 0x7FD088D4EBABCD93L), (EntityLivingBase)ModuleAutoCrystal.mc.player);
        return this.returnDamage((EntityPlayer)target, targetDamage, selfDamage);
    }

    /*
     * WARNING - void declaration
     */
    public float returnDamage(EntityPlayer entityPlayer, float f, float f2) {
        void selfDamage;
        void target;
        void targetDamage;
        if (targetDamage < this.getMinimumDamage((EntityLivingBase)target)) {
            if (targetDamage < target.getHealth() + target.getAbsorptionAmount()) {
                return Float.intBitsToFloat(Float.floatToIntBits(-5.012834f) ^ 0x7F206923);
            }
        }
        if (selfDamage > maxSelfDamage.getValue().floatValue()) {
            return Float.intBitsToFloat(Float.floatToIntBits(-17.910734f) ^ 0x7E0F492F);
        }
        if (ModuleAutoCrystal.mc.player.getHealth() + ModuleAutoCrystal.mc.player.getAbsorptionAmount() <= selfDamage) {
            return Float.intBitsToFloat(Float.floatToIntBits(-6.6046715f) ^ 0x7F535978);
        }
        return (float)targetDamage;
    }

    public void swingItem() {
        block0: {
            block3: {
                block1: {
                    block2: {
                        if (swing.getValue().equals((Object)Hands.None)) break block0;
                        if (!packetSwing.getValue()) break block1;
                        if (!swing.getValue().equals((Object)Hands.Mainhand)) break block2;
                        ModuleAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                        break block0;
                    }
                    if (!swing.getValue().equals((Object)Hands.Offhand)) break block0;
                    ModuleAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.OFF_HAND));
                    break block0;
                }
                if (!swing.getValue().equals((Object)Hands.Mainhand)) break block3;
                ModuleAutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
                break block0;
            }
            if (!swing.getValue().equals((Object)Hands.Offhand)) break block0;
            ModuleAutoCrystal.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }

    /*
     * WARNING - void declaration
     */
    public float getMinimumDamage(EntityLivingBase entityLivingBase) {
        void entity;
        if (facePlace.getValue() && entity.getHealth() + entity.getAbsorptionAmount() < facePlaceHealth.getValue().floatValue() || armorBreaker.getValue() && DamageUtils.shouldBreakArmor((EntityLivingBase)entity, armorPercent.getValue().intValue())) {
            return Float.intBitsToFloat(Float.floatToIntBits(15.796245f) ^ 0x7EFCBD6B);
        }
        return minimumDamage.getValue().floatValue();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        isSequential = false;
        this.target = null;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        isSequential = false;
        this.target = null;
    }

    @Override
    public void onLogout() {
        this.blacklist.clear();
    }

    public static boolean lambda$onRender3D$4(FadePosition fadePosition) {
        FadePosition p;
        return System.currentTimeMillis() - FadePosition.access$000(p) >= (long)shrinkSpeed.getValue().intValue() * ((long)-1142890069 ^ 0xFFFFFFFFBBE0E1A1L) || ModuleAutoCrystal.mc.world.getBlockState(FadePosition.access$100(p)).getBlock() == Blocks.AIR;
    }

    public static void lambda$onRender3D$3(FadePosition fadePosition) {
        block1: {
            FadePosition p;
            if (FadePosition.access$100(p).equals((Object)renderPosition)) break block1;
            AxisAlignedBB bb = RenderUtils.fixBB(new AxisAlignedBB(FadePosition.access$100(p)));
            long time = System.currentTimeMillis();
            long duration = time - FadePosition.access$000(p);
            float startAlpha = (float)fillColor.getValue().getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.19909953f) ^ 0x7D34E0BF);
            if (duration < (long)shrinkSpeed.getValue().intValue() * ((long)224288018 ^ 0xD5E5D18L)) {
                float opacity = startAlpha - (float)duration / (float)(shrinkSpeed.getValue().intValue() * 10);
                opacity = MathHelper.clamp((float)opacity, (float)Float.intBitsToFloat(Float.floatToIntBits(-4.7571034f) ^ 0x7F183A31), (float)Float.intBitsToFloat(Float.floatToIntBits(2.7682826E38f) ^ 0x7F50432F));
                bb = bb.shrink((double)(-opacity));
                RenderUtils.drawFilledBox(bb, fillColor.getValue().getRGB());
                RenderUtils.drawBlockOutline(bb, outlineColor.getValue(), Float.intBitsToFloat(Float.floatToIntBits(4.049864f) ^ 0x7F01987C));
            }
        }
    }

    public static boolean lambda$onRender3D$2(FadePosition fadePosition) {
        FadePosition fadePosition2;
        return System.currentTimeMillis() - fadePosition2.getStartTime() >= (long)fadeDuration.getValue().intValue() * ((long)1778390701 ^ 0x6A0016C9L);
    }

    public static void lambda$onRender3D$1(FadePosition fadePosition) {
        block3: {
            FadePosition pos;
            if (pos.getPosition().equals((Object)renderPosition)) break block3;
            long time = System.currentTimeMillis();
            long duration = time - pos.getStartTime();
            if (duration < (long)fadeDuration.getValue().intValue() * ((long)-1970346968 ^ 0xFFFFFFFF8A8EE44CL)) {
                float opacity = (float)fillColor.getValue().getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.3990955f) ^ 0x7DB3563F) - (float)duration / (float)((long)fadeDuration.getValue().intValue() * ((long)1706775068 ^ 0x65BB5278L));
                int alpha = MathHelper.clamp((int)((int)(opacity * Float.intBitsToFloat(Float.floatToIntBits(0.7680854f) ^ 0x7C3BA13F))), (int)0, (int)255);
                if (fill.getValue().equals((Object)Renders.Normal)) {
                    RenderUtils.drawBox(pos.getPosition(), new Color(fillColor.getValue().getRed(), fillColor.getValue().getGreen(), fillColor.getValue().getBlue(), alpha));
                }
                if (outline.getValue().equals((Object)Renders.Normal)) {
                    RenderUtils.prepare(7);
                    RenderUtils.drawBoundingBoxBlockPos(pos.getPosition(), lineWidth.getValue().floatValue(), outlineColor.getValue().getRed(), outlineColor.getValue().getGreen(), outlineColor.getValue().getBlue(), alpha);
                    RenderUtils.release();
                }
            }
        }
    }

    public static boolean lambda$onRender3D$0(FadePosition fadePosition) {
        FadePosition pos;
        return FadePosition.access$100(pos).equals((Object)renderPosition);
    }

    static {
        renderPosition = null;
        isSequential = false;
        breakMode = new ValueBoolean("Break", "Break", "Breaks the crystals.", true);
        breakDelay = new ValueNumber("BreakDelay", "BreakDelay", "The delay for breaking.", 0, 0, 20);
        breakRange = new ValueNumber("BreakRange", "BreakRange", "The range for breaking.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.0256348f) ^ 0x7F234800)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.1883357E38f) ^ 0x7EB2CD07)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.6635215f) ^ 0x7F14EE46)));
        breakWallsRange = new ValueNumber("BreakWallsRange", "BreakWallsRange", "The range for breaking through walls.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.56104803f) ^ 0x7F6FA0D8)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.5817177E38f) ^ 0x7EEDFD8D)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.17075278f) ^ 0x7EEED9D1)));
        antiWeakness = new ValueBoolean("AntiWeakness", "AntiWeakness", "Switches to a sword when you have weakness.", false);
        inhibit = new ValueBoolean("Inhibit", "Inhibit", "Prevents an a crystal which is going to explode from being attacked again.", true);
        sequential = new ValueBoolean("Sequential", "Sequential", "Breaks crystals when they spawn. Good for strictless servers.", true);
        sync = new ValueBoolean("Sync", "Sync", "Syncs crystals based on sounds.", true);
        place = new ValueBoolean("Place", "Place", "Places the crystals.", true);
        placeDelay = new ValueNumber("PlaceDelay", "PlaceDelay", "The delay for placing.", 0, 0, 20);
        placeRange = new ValueNumber("PlaceRange", "PlaceRange", "The range for placing.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.22218512f) ^ 0x7EC3847F)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(8.3361226E37f) ^ 0x7E7ADB27)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.0495443f) ^ 0x7F465778)));
        placeWallsRange = new ValueNumber("PlaceWallsRange", "PlaceWallsRange", "The range for breaking through walls.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.28594783f) ^ 0x7EF267C1)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.5344281E38f) ^ 0x7EE6E005)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.7122473f) ^ 0x7F1B2AEB)));
        placeUnderBlock = new ValueBoolean("PlaceUnderBlock", "PlaceUnderBlock", "Places under blocks.", false);
        switchMode = new ValueEnum("Switch", "Switch", "Automatically switches to a crystal.", SwitchModes.None);
        multiPlace = new ValueEnum("MultiPlace", "MultiPlace", "Places in more positions than one.", MultiPlaceModes.None);
        holePlace = new ValueBoolean("HolePlace", "HolePlace", "Places in the hole when the player jumps.", true);
        rotation = new ValueBoolean("Rotation", "Rotation", "Rotates to the crystal and position when placing.", false);
        timing = new ValueEnum("Timing", "Timing", "The timing for the breaking.", Timings.Break);
        targetRange = new ValueNumber("TargetRange", "TargetRange", "The range for targeting.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.5514623f) ^ 0x7EB69651)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(2.1071864E38f) ^ 0x7F1E86EF)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.59863883f) ^ 0x7EE94065)));
        swing = new ValueEnum("Swing", "Swing", "The hand to swing with.", Hands.Mainhand);
        packetSwing = new ValueBoolean("PacketSwing", "PacketSwing", "Swings serverside but not clientside.", false);
        minimumDamage = new ValueNumber("MinimumDamage", "MinimumDamage", "The minimum damage that is required for the target.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.5494468f) ^ 0x7F065446)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(2.975486E37f) ^ 0x7DB3149F)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.03962459f) ^ 0x7F324D65)));
        maxSelfDamage = new ValueNumber("MaxSelfDamage", "MaxSelfDamage", "The minimum damage that is required for the target.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.21364413f) ^ 0x7E9AC587)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(2.5525206E38f) ^ 0x7F4007C2)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.008906226f) ^ 0x7E01EB6B)));
        facePlace = new ValueBoolean("FacePlace", "FacePlace", "Faceplaces the target when the opportunity is right.", true);
        facePlaceHealth = new ValueNumber("FacePlaceHealth", "FacePlaceHealth", "The health at which faceplacing would start.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.2479648f) ^ 0x7F3DEA7C)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(2.5063878E38f) ^ 0x7F3C8F46)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.01991236f) ^ 0x7EB31F3F)));
        armorBreaker = new ValueBoolean("ArmorBreaker", "ArmorBreaker", "Starts faceplacing the enemy when their armor is low.", true);
        armorPercent = new ValueNumber("ArmorPercent", "ArmorPercent", "The percent required for the armor breaking to start.", 20, 0, 100);
        render = new ValueEnum("Render", "Render", "Renders the current target position.", RenderModes.Normal);
        fill = new ValueEnum("Fill", "Fill", "The mode for filling the position.", Renders.Normal);
        outline = new ValueEnum("Outline", "Outline", "The mode for outlining the position.", Renders.Normal);
        renderDamage = new ValueBoolean("RenderDamage", "RenderDamage", "Renders the amount of damage that the position does.", false);
        shrinkSpeed = new ValueNumber("ShrinkSpeed", "ShrinkSpeed", "", 150, 10, 500);
        fadeDuration = new ValueNumber("FadeDuration", "FadeDuration", "The duration of the fade.", 15, 1, 50);
        lineWidth = new ValueNumber("Width", "Width", "The width for the outline.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(4.8967586f) ^ 0x7F1CB23F)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(2.1313062E38f) ^ 0x7F205777)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.2066184f) ^ 0x7F3A7279)));
        fillColor = new ValueColor("FillColor", "FillColor", "The color for the filling.", new Color(0, 0, 255, 100));
        outlineColor = new ValueColor("OutlineColor", "OutlineColor", "The color for the outline.", new Color(0, 0, 255, 255));
    }

    public static enum Renders {
        None,
        Normal;

    }

    public static enum RenderModes {
        None,
        Normal,
        Fade,
        Size;

    }

    public static enum MultiPlaceModes {
        None,
        Dynamic,
        Static;

    }

    public static enum Hands {
        None,
        Mainhand,
        Offhand;

    }

    public static enum SwitchModes {
        None,
        Normal,
        Silent;

    }

    public static enum Timings {
        Break,
        Place;

    }

    public static class FadePosition {
        public BlockPos position;
        public long startTime;

        public FadePosition(BlockPos position) {
            this.position = position;
            this.startTime = System.currentTimeMillis();
        }

        public BlockPos getPosition() {
            return this.position;
        }

        public long getStartTime() {
            return this.startTime;
        }

        public static long access$000(FadePosition fadePosition) {
            FadePosition x0;
            return x0.startTime;
        }

        public static BlockPos access$100(FadePosition fadePosition) {
            FadePosition x0;
            return x0.position;
        }
    }
}

