package net.woodenslab.whenwararises.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.init.ModItems;

import java.util.List;

@JeiPlugin
public class WhenWarArisesJEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(WhenWarArises.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        ItemStack ironIngot = new ItemStack(Items.IRON_INGOT);
        ItemStack battery = new ItemStack(ModItems.BATTERY.get());

        registerItemRepair(registration, ModItems.RIOT_SHIELD.get(), ironIngot, 750);
        registerItemRepair(registration, ModItems.HAPPY_RIOT_SHIELD.get(), ironIngot, 750);
        registerItemRepair(registration, ModItems.FUNNY_RIOT_SHIELD.get(), ironIngot, 750);

        registerItemRepair(registration, ModItems.TASER_GUN.get(), battery, 16);
    }

    private void registerItemRepair(IRecipeRegistration registration, Item item, ItemStack repairMaterial, int maxUses) {

        int repairedUses = (int) Math.floor((double) maxUses / 4);

        ItemStack damagedItem = new ItemStack(item);
        damagedItem.setDamageValue(maxUses);

        ItemStack repairedItem = new ItemStack(item);
        repairedItem.setDamageValue(maxUses - repairedUses);

        ItemStack fusedItem = new ItemStack(item);
        fusedItem.setDamageValue(maxUses - (int) Math.min(repairedUses * 2 + Math.floor(0.12 * maxUses), maxUses));

        IJeiAnvilRecipe repairRecipe = registration.getVanillaRecipeFactory().createAnvilRecipe(
                List.of(damagedItem),
                List.of(repairMaterial),
                List.of(repairedItem)
        );

        IJeiAnvilRecipe fuseRecipe = registration.getVanillaRecipeFactory().createAnvilRecipe(
                List.of(repairedItem),
                List.of(repairedItem.copy()),
                List.of(fusedItem)
        );

        registration.addRecipes(RecipeTypes.ANVIL, List.of(repairRecipe, fuseRecipe));
    }
}
