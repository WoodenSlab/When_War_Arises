package net.woodenslab.whenwararises.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.init.ModItems;

import java.util.Arrays;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        buildShieldRecipe(pWriter, ModItems.RIOT_SHIELD.get(), Items.YELLOW_WOOL);
        buildShieldRecipe(pWriter, ModItems.HAPPY_RIOT_SHIELD.get(), Items.WHITE_WOOL);
        buildShieldRecipe(pWriter, ModItems.FUNNY_RIOT_SHIELD.get(), Items.RED_WOOL);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.TASER_GUN.get())
                .pattern("DGR")
                .pattern("G  ")
                .pattern("B  ")
                .define('D', Items.DIAMOND)
                .define('G', Items.GOLD_INGOT)
                .define('R', Items.REDSTONE)
                .define('B', ModItems.BATTERY.get())
                .unlockedBy(getHasName(ModItems.BATTERY.get()), has(ModItems.BATTERY.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BATTERY.get())
                .requires(Items.REDSTONE)
                .requires(Items.COPPER_INGOT)
                .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                .save(pWriter);
    }

    private void buildShieldRecipe(Consumer<FinishedRecipe> pWriter, ItemLike resultShield, ItemLike woolColor) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, resultShield)
                .pattern("IWI")
                .pattern("III")
                .pattern(" I ")
                .define('I', Items.IRON_INGOT)
                .define('W', woolColor)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(pWriter);
    }
}
