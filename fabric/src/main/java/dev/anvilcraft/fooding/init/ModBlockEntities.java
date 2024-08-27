package dev.anvilcraft.fooding.init;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import dev.anvilcraft.fooding.block.entity.FoodAnalyzerBlockEntity;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.REGIS;

public class ModBlockEntities {
    public static final BlockEntityEntry<FoodAnalyzerBlockEntity> FOOD_ANALYZER = REGIS
            .blockEntity("food_analyzer",FoodAnalyzerBlockEntity::createBlockEntity)
            .onRegister(FoodAnalyzerBlockEntity::onBlockEntityRegister)
            .validBlock(ModBlocks.FOOD_ANALYZER)
            .register();
    public static void register() {

    }
}
