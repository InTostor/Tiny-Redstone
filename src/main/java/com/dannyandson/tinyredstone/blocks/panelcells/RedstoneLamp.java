package com.dannyandson.tinyredstone.blocks.panelcells;

import com.dannyandson.tinyredstone.blocks.IPanelCell;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class RedstoneLamp extends TinyBlock implements IPanelCell {

    public static ResourceLocation TEXTURE_REDSTONE_LAMP = new ResourceLocation("minecraft","block/redstone_lamp");
    public static ResourceLocation TEXTURE_REDSTONE_LAMP_ON = new ResourceLocation("minecraft","block/redstone_lamp_on");

    /**
     * Drawing the cell on the panel
     *
     * @param matrixStack     positioned for this cell
     *                        scaled to 1/8 block size such that length and width of cell are 1.0
     *                        starting point is (0,0,0)
     * @param buffer
     * @param combinedLight
     * @param combinedOverlay
     */
    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        IVertexBuilder builder = buffer.getBuffer(RenderType.getSolid());
        TextureAtlasSprite sprite;

        if (weakSignalStrength+strongSignalStrength>0) sprite = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(TEXTURE_REDSTONE_LAMP_ON);
        else sprite = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(TEXTURE_REDSTONE_LAMP);

        matrixStack.translate(0,0,1.0);

        add(builder, matrixStack, 0,0,0, sprite.getMinU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,0,0, sprite.getMaxU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,1,0, sprite.getMaxU(), sprite.getMinV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 0,1,0, sprite.getMinU(), sprite.getMinV(),combinedLight,combinedOverlay);

        matrixStack.rotate(Vector3f.XP.rotationDegrees(90));
        matrixStack.translate(0,-1,0);
        add(builder, matrixStack, 0,0,0, sprite.getMinU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,0,0, sprite.getMaxU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,1,0, sprite.getMaxU(), sprite.getMinV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 0,1,0, sprite.getMinU(), sprite.getMinV(),combinedLight,combinedOverlay);

        matrixStack.rotate(Vector3f.YP.rotationDegrees(90));
        matrixStack.translate(0,0,1);
        add(builder, matrixStack, 0,0,0, sprite.getMinU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,0,0, sprite.getMaxU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,1,0, sprite.getMaxU(), sprite.getMinV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 0,1,0, sprite.getMinU(), sprite.getMinV(),combinedLight,combinedOverlay);

        matrixStack.rotate(Vector3f.YP.rotationDegrees(90));
        matrixStack.translate(0,0,1);
        add(builder, matrixStack, 0,0,0, sprite.getMinU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,0,0, sprite.getMaxU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,1,0, sprite.getMaxU(), sprite.getMinV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 0,1,0, sprite.getMinU(), sprite.getMinV(),combinedLight,combinedOverlay);

        matrixStack.rotate(Vector3f.YP.rotationDegrees(90));
        matrixStack.translate(0,0,1);
        add(builder, matrixStack, 0,0,0, sprite.getMinU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,0,0, sprite.getMaxU(), sprite.getMaxV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 1,1,0, sprite.getMaxU(), sprite.getMinV(),combinedLight,combinedOverlay);
        add(builder, matrixStack, 0,1,0, sprite.getMinU(), sprite.getMinV(),combinedLight,combinedOverlay);

    }

    private void add(IVertexBuilder renderer, MatrixStack stack, float x, float y, float z, float u, float v, int combinedLightIn, int combinedOverlayIn) {
        renderer.pos(stack.getLast().getMatrix(), x, y, z)
                .color(1.0f, 1.0f, 1.0f, 1.0f)
                .tex(u, v)
                .lightmap((weakSignalStrength+strongSignalStrength>0)?15728880:combinedLightIn)
                .normal(1, 0, 0)
                .endVertex();
    }

    /**
     * If this cell outputs light, return the level here. Otherwise, return 0.
     *
     * @return Light level to output 0-15
     */
    @Override
    public int lightOutput() {
        return (weakSignalStrength+strongSignalStrength>0)?1:0;
    }

}
