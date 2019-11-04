package com.mcmoddev.spookybiomes;

import com.mcmoddev.spookybiomes.api.blocks.BlocksSB;
import com.mcmoddev.spookybiomes.common.world.gen.SpookyWorldGenerator;
import com.mcmoddev.spookybiomes.init.BiomeRegistry;
import com.mcmoddev.spookybiomes.init.OreDictRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(
        name = SpookyBiomes.NAME,
        modid = SpookyBiomes.MODID,
        version = SpookyBiomes.VERSION,
        updateJSON = "https://raw.githubusercontent.com/MinecraftModDevelopmentMods/Spooky-Biomes/master/update.json",
        certificateFingerprint = "@FINGERPRINT@",
        acceptedMinecraftVersions = "[1.12, 1.12.2]",
        dependencies = "required-after:proxyslib@[1.2.0,);"
)
public class SpookyBiomes {

    public static final String NAME = "Spooky Biomes";
    public static final String MODID = "spookybiomes";
    public static final String VERSION = "1.2.0";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    private static Configuration config;

    public static Configuration getConfig() {
        return config;
    }

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlocksSB.GHOSTLY_SAPLING);
        }
    };

    @Mod.EventHandler
    public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
        LOGGER.error("Invalid fingerprint detected! The file " + event.getSource().getName() +
                " may have been tampered with. This version will NOT be supported! Please download the mod from CurseForge for a supported and signed version of the mod.");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        BiomeRegistry.registerBiomeTypes();
        GameRegistry.registerWorldGenerator(new SpookyWorldGenerator(), 10);
        OreDictRegistry.init();
    }
}