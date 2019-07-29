package tk.rdvdev2.dimensiontest;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.BiFunction;

@Mod("dimensiontest")
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD)
public class DimensionTest
{
    private static final ModDimension MOD_DIMENSION = new ModDimension() {
        @Override
        public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
            return OverworldDimension::new;
        }
    }.setRegistryName("dimensiontest:dim");

    public DimensionTest() {
        MinecraftForge.EVENT_BUS.addListener(this::registerDimensions);
        MinecraftForge.EVENT_BUS.addListener(this::onWorldLoading);
    }

    private void registerDimensions(RegisterDimensionsEvent event) {
        System.out.println(" ***** RegisterDimensionsEvent was fired");
        DimensionManager.registerDimension(MOD_DIMENSION.getRegistryName(), MOD_DIMENSION, null, true);
    }

    @SubscribeEvent
    public static void registerModDimensions(RegistryEvent.Register<ModDimension> event) {
        event.getRegistry().register(MOD_DIMENSION);
    }

    private void onWorldLoading(WorldEvent.Load event) {
        DimensionType.getAll().forEach(d -> System.out.println(d.toString()));
    }
}
