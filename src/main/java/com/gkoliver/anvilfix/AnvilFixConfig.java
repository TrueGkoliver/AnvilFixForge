package com.gkoliver.anvilfix;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class AnvilFixConfig {
    public static class Configuration {
        private int levelLimit = -1;
        private final ForgeConfigSpec.ConfigValue<Integer> REPAIR_LIMIT;
        private int globalEnchantmentLevelLimit = -1;
        private final ForgeConfigSpec.ConfigValue<Integer> GLOBAL_ENCHANTMENT_LIMIT;
        private boolean removeIncrementalRepairCost = false;
        private final ForgeConfigSpec.BooleanValue REMOVE_INCREMENTAL_COST;
        private boolean stopAnvilBreaking = true;
        private final ForgeConfigSpec.BooleanValue STOP_ANVIL_BREAKING;
        public Configuration(ForgeConfigSpec.Builder builderIn) {
            builderIn.comment("Anvil Fix [Forge Edition]")
                    .push("general");
            REPAIR_LIMIT = builderIn.comment("The maximum level limit for repairs. If it is -1, then there is no limit.")
                    .define("repair_level_limit", -1);
            GLOBAL_ENCHANTMENT_LIMIT = builderIn.comment("The maximum level limit for enchantments. If -1, then there is no limit.")
                    .define("enchantment_level_limit", -1);
            REMOVE_INCREMENTAL_COST = builderIn.comment("Do remove the incremental repair cost for enchantments?")
                    .define("remove_incremental_cost", false);
            STOP_ANVIL_BREAKING = builderIn.comment("Do stop anvils from breaking?")
                    .define("stop_anvil_breaking", true);
        }
    }
    public static final ForgeConfigSpec CONFIGSPEC;
    public static final Configuration CONFIG;
    static {
        final Pair<Configuration, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Configuration::new);
        CONFIGSPEC = pair.getRight();
        CONFIG = pair.getLeft();
    }
    public static int getLevelLimit() {
        return CONFIG.REPAIR_LIMIT.get() != -1 ? CONFIG.REPAIR_LIMIT.get() + 1 : Integer.MAX_VALUE;
    }

    public static int getEnchantmentLimit(Enchantment enchantment) {
        return CONFIG.GLOBAL_ENCHANTMENT_LIMIT.get() >= 0 ? CONFIG.GLOBAL_ENCHANTMENT_LIMIT.get() : enchantment.getMaxLevel();
    }

    public static boolean removeIncrementalCost(ItemStack stack) {
        return CONFIG.REMOVE_INCREMENTAL_COST.get() && !stack.is(AnvilFix.FORCE_REPAIR_COST);
    }

    public static boolean shouldStopAnvilBreaking() {
        return CONFIG.STOP_ANVIL_BREAKING.get();
    }
}
