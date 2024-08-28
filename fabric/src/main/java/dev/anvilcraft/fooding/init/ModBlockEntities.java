package dev.anvilcraft.fooding.init;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import dev.anvilcraft.fooding.block.entity.FoodAnalyzerBlockEntity;
import dev.anvilcraft.fooding.block.entity.InducedMutameterBlockEntity;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.REGIS;

public class ModBlockEntities {
    public static final BlockEntityEntry<FoodAnalyzerBlockEntity> FOOD_ANALYZER = REGIS
            .blockEntity("food_analyzer",FoodAnalyzerBlockEntity::createBlockEntity)
            .onRegister(FoodAnalyzerBlockEntity::onBlockEntityRegister)
            .validBlock(ModBlocks.FOOD_ANALYZER)
            .register();
    public static final BlockEntityEntry<InducedMutameterBlockEntity> INDUCED_MUTAMETER = REGIS
            .blockEntity("induced_mutameter",InducedMutameterBlockEntity::createBlockEntity)
            .onRegister(InducedMutameterBlockEntity::onBlockEntityRegister)
            .validBlock(ModBlocks.INDUCED_MUTAMETER)
            .register();
    public static void register() {

    }
}
