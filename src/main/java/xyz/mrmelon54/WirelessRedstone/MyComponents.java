package xyz.mrmelon54.WirelessRedstone;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.util.Identifier;
import xyz.mrmelon54.WirelessRedstone.component.WirelessFrequencyStorageComponent;

public final class MyComponents implements WorldComponentInitializer {
    public static final ComponentKey<WirelessFrequencyStorageComponent> FrequencyStorage = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("wireless-redstone:wireless_frequency_storage"), WirelessFrequencyStorageComponent.class);

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(FrequencyStorage, world -> new WirelessFrequencyStorageComponent());
    }
}
