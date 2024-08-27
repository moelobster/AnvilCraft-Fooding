package dev.anvilcraft.fooding.init;

import com.tterrag.registrate.util.entry.BlockEntry;
import dev.anvilcraft.fooding.block.FoodAnalyzerBlock;
import dev.dubhe.anvilcraft.init.ModItems;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import static dev.anvilcraft.fooding.fabric.AnvilCraftFoodingFabric.REGIS;

public class ModBlocks {
    public static final BlockEntry<? extends Block> FOOD_ANALYZER = REGIS
            .block("food_analyzer", FoodAnalyzerBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .blockstate((ctx, provider) -> {
            })
            .simpleItem()
            .tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .recipe((ctx, provider) -> ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ctx.get())
                    .pattern("ABA")
                    .pattern("ECE")
                    .pattern("ADA")
                    .define('A', Items.IRON_INGOT)
                    .define('B',Items.GLASS_PANE)
                    .define('C', Items.AMETHYST_SHARD)
                    .define('D',Items.COPPER_BLOCK)
                    .define('E',ModItems.MAGNETOELECTRIC_CORE)
                    .save(provider)
            )
            .register();



    public static void register(){

    }
}
