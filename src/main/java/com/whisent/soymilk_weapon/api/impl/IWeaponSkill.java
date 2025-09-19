package com.whisent.soymilk_weapon.api.impl;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface IWeaponSkill {
    /**
     * 获取能量条数值
     * @return 当前能量条值
     */
    float getEnergy();

    /**
     * 设置能量条数值
     * @param energy 能量值
     */
    void setEnergy(float energy);

    /**
     * 获取必杀槽数值
     * @return 当前必杀槽值
     */
    float getUltimateCharge();

    /**
     * 设置必杀槽数值
     * @param charge 必杀槽值
     */
    void setUltimateCharge(float charge);

    /**
     * 获取必杀状态
     * @return 是否处于必杀状态
     */
    boolean isUltimateActive();

    /**
     * 设置必杀状态
     * @param active 是否激活必杀状态
     */
    void setUltimateActive(boolean active);

    /**
     * 消耗能量
     * @param amount 消耗量
     * @return 是否消耗成功
     */
    boolean consumeEnergy(float amount);

    /**
     * 消耗必杀槽
     * @param amount 消耗量
     * @return 是否消耗成功
     */
    boolean consumeUltimateCharge(float amount);

    /**
     * 增加能量
     * @param amount 增加量
     */
    void addEnergy(float amount);

    /**
     * 增加必杀槽
     * @param amount 增加量
     */
    void addUltimateCharge(float amount);
    
    /**
     * 保存数据到NBT
     * @param nbt NBT标签
     */
    void saveNBTData(net.minecraft.nbt.CompoundTag nbt);
    
    /**
     * 从NBT加载数据
     * @param nbt NBT标签
     */
    void loadNBTData(net.minecraft.nbt.CompoundTag nbt);
}