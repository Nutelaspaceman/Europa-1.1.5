/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemShield
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package com.europa.api.utilities.entity;

import com.europa.api.utilities.IMinecraft;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class DamageUtils
implements IMinecraft {
    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static float calculateDamage(double var0, double var2_1, double var4_2, EntityLivingBase var6_3) {
        block48: {
            block47: {
                v0 = entity;
                v1 = posX;
                v2 = posY;
                v3 = posZ;
                v4 = v0.getDistance(v1, (double)v2, (double)v3);
                distance = v4 / Double.longBitsToDouble(Double.doubleToLongBits(0.7061696223198352) ^ 9209466449027488523L);
                v5 = Double.longBitsToDouble(Double.doubleToLongBits(27.029805348929738) ^ 9208462252549006891L) - distance;
                v6 = DamageUtils.mc.world;
                v7 = v8;
                v9 = v8;
                v10 = posX;
                v11 = posY;
                v12 = posZ;
                v7(v10, (double)v11, (double)v12);
                v13 = entity;
                v14 = v13.getEntityBoundingBox();
                v15 = v6.getBlockDensity(v9, v14);
                value = v5 * (double)v15;
                v16 = (int)((value * value + value) / Double.longBitsToDouble(Double.doubleToLongBits(0.8903081150566055) ^ 9217880420563092034L) * Double.longBitsToDouble(Double.doubleToLongBits(1.7109126140714253) ^ 9216440603477017248L) * Double.longBitsToDouble(Double.doubleToLongBits(1.106780737933289) ^ 9212593935477916275L) + Double.longBitsToDouble(Double.doubleToLongBits(7.542607152744057) ^ 9218353458663289363L));
                v17 = DamageUtils.mc.world;
                v18 = v17.getDifficulty();
                v19 = v18.getId();
                if (v19 != 0) break block47;
                v20 = Float.intBitsToFloat(Float.floatToIntBits(2.8729047E38f) ^ 2136482338);
                ** GOTO lbl56
            }
            v21 = DamageUtils.mc.world;
            v22 = v21.getDifficulty();
            v23 = v22.getId();
            if (v23 != 2) break block48;
            v20 = Float.intBitsToFloat(Float.floatToIntBits(11.309852f) ^ 2125788455);
            ** GOTO lbl56
        }
        v24 = DamageUtils.mc.world;
        v25 = v24.getDifficulty();
        v26 = v25.getId();
        v20 = v26 == 1 ? Float.intBitsToFloat(Float.floatToIntBits(2.8048782f) ^ 2134082336) : Float.intBitsToFloat(Float.floatToIntBits(6.0613766f) ^ 2130835148);
lbl56:
        // 3 sources

        v27 = damage = v16 * v20;
        v28 = entity;
        v29 = v28.getTotalArmorValue();
        v30 = v29;
        v31 = entity;
        v32 = SharedMonsterAttributes.ARMOR_TOUGHNESS;
        v33 = v31.getEntityAttribute(v32);
        v34 = v33.getAttributeValue();
        v35 = (float)v34;
        v36 = CombatRules.getDamageAfterAbsorb((float)v27, (float)v30, (float)v35);
        v37 = damage = v36;
        v38 = Float.intBitsToFloat(Float.floatToIntBits(48.782467f) ^ 2109940031);
        v39 = entity;
        v40 = v39.getArmorInventoryList();
        v41 = v42;
        v43 = v42;
        v44 = DamageUtils.mc.world;
        v45 = null;
        v46 = posX;
        v47 = posY;
        v48 = posZ;
        v49 = Float.intBitsToFloat(Float.floatToIntBits(1.0126694f) ^ 2135007015);
        v50 = false;
        v51 = true;
        v41((World)v44, v45, v46, (double)v47, (double)v48, v49, v50, v51);
        v52 = DamageSource.causeExplosionDamage((Explosion)v43);
        v53 = EnchantmentHelper.getEnchantmentModifierDamage((Iterable)v40, (DamageSource)v52);
        v54 = v53;
        v55 = Float.intBitsToFloat(Float.floatToIntBits(3.0601562E38f) ^ 2137405559);
        v56 = Float.intBitsToFloat(Float.floatToIntBits(0.48258448f) ^ 2136413520);
        v57 = MathHelper.clamp((float)v54, (float)v55, (float)v56);
        damage = v37 * (v38 - v57 / Float.intBitsToFloat(Float.floatToIntBits(0.27084562f) ^ 2135075911));
        v58 = entity;
        v59 = 11;
        v60 = Potion.getPotionById((int)v59);
        v61 = Objects.requireNonNull(v60);
        v62 = v61;
        v63 = v58.isPotionActive(v62);
        if (v63 == false) return damage;
        try {
            damage -= damage / Float.intBitsToFloat(Float.floatToIntBits(0.18720047f) ^ 2126492027);
            return damage;
        }
        catch (NullPointerException | ConcurrentModificationException exception) {
            return Float.intBitsToFloat(Float.floatToIntBits(9.840829E37f) ^ 2123633023);
        }
    }

    /*
     * WARNING - void declaration
     */
    public static boolean shouldBreakArmor(EntityLivingBase entityLivingBase, float f) {
        EntityLivingBase entity;
        for (ItemStack stack : entity.getArmorInventoryList()) {
            void targetPercent;
            if (stack == null || stack.getItem() == Items.AIR) {
                return true;
            }
            float armorPercent = (float)(stack.getMaxDamage() - stack.getItemDamage()) / (float)stack.getMaxDamage() * Float.intBitsToFloat(Float.floatToIntBits(0.11806387f) ^ 0x7F39CB78);
            if (!(targetPercent >= armorPercent) || stack.stackSize >= 2) continue;
            return true;
        }
        return false;
    }

    public static boolean hasDurability(ItemStack itemStack) {
        ItemStack stack;
        Item item = stack.getItem();
        return item instanceof ItemArmor || item instanceof ItemSword || item instanceof ItemTool || item instanceof ItemShield;
    }

    public static int getRoundedDamage(ItemStack itemStack) {
        ItemStack stack;
        return (int)((float)(stack.getMaxDamage() - stack.getItemDamage()) / (float)stack.getMaxDamage() * Float.intBitsToFloat(Float.floatToIntBits(0.26034543f) ^ 0x7C4D4BFF));
    }
}

