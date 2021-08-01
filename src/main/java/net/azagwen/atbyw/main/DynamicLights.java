package net.azagwen.atbyw.main;

import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;

import static dev.lambdaurora.lambdynlights.api.DynamicLightHandlers.registerDynamicLightHandler;

/**
 *  This class is using the LambDynamicLights API
 *  https://github.com/LambdAurora/LambDynamicLights
 */
public class DynamicLights implements DynamicLightsInitializer {

    @Override
    public void onInitializeDynamicLights() {
        registerDynamicLightHandler(EntityTypes.SHROOMSTICK, entity -> 15);
    }
}
