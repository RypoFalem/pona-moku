package io.github.nalathnidragon.pona_moku;

import io.github.nalathnidragon.pona_moku.config.FoodStatusConfig;
import io.github.nalathnidragon.pona_moku.config.PonaMokuConfig;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PonaMoku implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("pona moku");
	public static final String MODID = "pona_moku";
	public static final PonaMokuConfig config = PonaMokuConfig.instance;

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());

		StringBuilder sb = new StringBuilder("Loaded Food Status effects:");
		for (Item item : FoodStatusConfig.getFoodStatus().keySet()) {
			sb.append("\n");
			sb.append(Text.translatable(item.getTranslationKey()).getString());
			for (StatusEffect effect : FoodStatusConfig.getFoodStatus().get(item).keySet()) {
				sb.append("\n\t%s for level %d"
					.formatted(
						Text.translatable(effect.getTranslationKey()).getString(),
						FoodStatusConfig.getFoodStatus().get(item).get(effect) + 1)
				);
			}
		}

		LOGGER.info(sb.toString());
	}
}
