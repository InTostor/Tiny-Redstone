package com.dannyandson.tinyredstone.blocks.panelcovers;

import com.dannyandson.tinyredstone.TinyRedstone;
import com.dannyandson.tinyredstone.api.IPanelCover;
import com.dannyandson.tinyredstone.blocks.RenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;

public class DarkCover implements IPanelCover {

    public static ResourceLocation TEXTURE_DARK_COVER = new ResourceLocation(TinyRedstone.MODID,"block/dark_cover");

    /**
     * Drawing the cover on the panel
     */
    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, int color) {

        float x1 = 0, x2 = 1, y1 = 0.125f, y2 = 1;
        TextureAtlasSprite sprite = RenderHelper.getSprite(TEXTURE_DARK_COVER);

        matrixStack.translate(0, y2, 1);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(270));
        RenderHelper.drawRectangle(buffer.getBuffer(RenderType.solid()),matrixStack,x1,x2,x1,x2,sprite,combinedLight,color,1f);

        matrixStack.mulPose(Vector3f.XP.rotationDegrees(90));
        matrixStack.translate(0,-y2,-x1);
        RenderHelper.drawRectangle(buffer.getBuffer(RenderType.solid()),matrixStack,x1,x2,y1,y2,sprite,combinedLight,color,1f);

        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
        matrixStack.translate(-x1,0,1-x1);
        RenderHelper.drawRectangle(buffer.getBuffer(RenderType.solid()),matrixStack,x1,x2,y1,y2,sprite,combinedLight,color,1f);

        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
        matrixStack.translate(-x1,0,1-x1);
        RenderHelper.drawRectangle(buffer.getBuffer(RenderType.solid()),matrixStack,x1,x2,y1,y2,sprite,combinedLight,color,1f);

        matrixStack.mulPose(Vector3f.YP.rotationDegrees(90));
        matrixStack.translate(-x1,0,1-x1);
        RenderHelper.drawRectangle(buffer.getBuffer(RenderType.solid()),matrixStack,x1,x2,y1,y2,sprite,combinedLight,color,1f);
    }

    /**
     * Does this cover allows light output?
     *
     * @return true if cells can output light, false if not.
     */
    @Override
    public boolean allowsLightOutput() {
        return false;
    }
}
