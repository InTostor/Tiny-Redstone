package com.dannyandson.tinyredstone.blocks;

import javax.annotation.CheckForNull;

public class PanelCellGhostPos extends PanelCellPos{
    IPanelCell panelCell;
    Side facing;

    protected PanelCellGhostPos(PanelTile panelTile, int row, int column) {
        super(panelTile, row, column);
    }

    public static PanelCellGhostPos fromPosInPanelCell(PosInPanelCell posInPanelCell, IPanelCell panelCell, Side facing) {

        PanelCellGhostPos pos = new PanelCellGhostPos(posInPanelCell.getPanelTile(),posInPanelCell.getRow(),posInPanelCell.getColumn());
        pos.panelCell=panelCell;
        pos.facing=facing;
        return pos;
    }

    /**
     * Gets the IPanelCell at this position or null if position is empty
     * @return IPanelCell or null
     */
    @Override
    @CheckForNull
    public IPanelCell getIPanelCell()
    {
        return this.panelCell;
    }

    /**
     * Gets the Side the cell at this position is facing within the panel tile
     * or null if position is empty
     * @return Side or null
     */
    @Override
    @CheckForNull
    public Side getCellFacing()
    {
        return this.facing;
    }


}