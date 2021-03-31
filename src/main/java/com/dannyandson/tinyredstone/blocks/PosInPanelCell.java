package com.dannyandson.tinyredstone.blocks;

import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class PosInPanelCell extends PanelCellPos {
    private final double x;
    private final double z;

    protected PosInPanelCell(PanelTile panelTile, int row, int cell, double x, double z) {
        super(panelTile,row, cell);
        this.x = x;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public PanelCellSegment getSegment() {
        int segmentRow = Math.round((float)(this.x*3f)-0.5f);
        int segmentColumn = Math.round((float)(this.z*3f)-0.5f);

        if(segmentRow == 0) {
            switch (segmentColumn) {
                case 0: return PanelCellSegment.FRONT_RIGHT;
                case 1: return PanelCellSegment.FRONT;
                case 2: return PanelCellSegment.FRONT_LEFT;
            }
        } else if(segmentRow == 1) {
            switch (segmentColumn) {
                case 0: return PanelCellSegment.RIGHT;
                case 1: return PanelCellSegment.CENTER;
                case 2: return PanelCellSegment.LEFT;
            }
        } else if(segmentRow == 2) {
            switch (segmentColumn) {
                case 0: return PanelCellSegment.BACK_RIGHT;
                case 1: return PanelCellSegment.BACK;
                case 2: return PanelCellSegment.BACK_LEFT;
            }
        }
        return null;
    }

    public static PosInPanelCell fromCoordinates(PanelTile panelTile, PanelCellPos panelCellPos, double x, double z) {
        if(x >= 0.0 && x <= 1.0 && z >= 0.0 && z <= 1.0) {
            double rotatedX;
            double rotatedZ;

            Side direction = panelCellPos.getCellFacing();

            if (direction == Side.FRONT) {
                rotatedX = z;
                rotatedZ = 1.0 - x;
            } else if (direction == Side.RIGHT) {
                rotatedX = 1.0 - x;
                rotatedZ = 1.0 - z;
            } else if (direction == Side.BACK){
                rotatedX = 1.0 - z;
                rotatedZ = x;
            } else {
                rotatedX = x;
                rotatedZ = z;
            }

            return new PosInPanelCell(panelTile, panelCellPos.getRow(), panelCellPos.getColumn(), rotatedX, rotatedZ);
        }
        return null;
    }

    public static PosInPanelCell fromHitVec(PanelTile panelTile, BlockPos pos, Vector3d hitVec) {
        double x = hitVec.x - pos.getX();
        double z = hitVec.z - pos.getZ();
        Direction facing = panelTile.getBlockState().get(BlockStateProperties.FACING);

        if (facing==Direction.NORTH)
            z = 1-(hitVec.y-pos.getY());
        else if (facing==Direction.EAST)
            x = hitVec.y-pos.getY();
        else if (facing==Direction.SOUTH)
            z = hitVec.y-pos.getY();
        else if (facing==Direction.WEST)
            x = 1-(hitVec.y-pos.getY());
        else if (facing==Direction.UP)
            z = 1-z;


        PanelCellPos panelCellPos = PanelCellPos.fromCoordinates(panelTile, x, z);
        if(panelCellPos == null) return null;

        x = (x - (panelCellPos.getRow()/8d))*8d;
        z = (z - (panelCellPos.getColumn()/8d))*8d;

        return fromCoordinates(panelTile, panelCellPos, x, z);
    }
}
