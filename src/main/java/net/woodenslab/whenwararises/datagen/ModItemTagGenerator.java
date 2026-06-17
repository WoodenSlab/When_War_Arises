package net.woodenslab.whenwararises.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.woodenslab.whenwararises.WhenWarArises;
import net.woodenslab.whenwararises.init.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, WhenWarArises.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(Tags.Items.TOOLS)
                .add(ModItems.RIOT_SHIELD.get(),
                        ModItems.HAPPY_RIOT_SHIELD.get(),
                        ModItems.FUNNY_RIOT_SHIELD.get(),
                        ModItems.TASER_GUN.get());

        this.tag(Tags.Items.TOOLS_SHIELDS)
                .add(ModItems.RIOT_SHIELD.get(),
                        ModItems.HAPPY_RIOT_SHIELD.get(),
                        ModItems.FUNNY_RIOT_SHIELD.get());
    }
}
