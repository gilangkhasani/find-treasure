package sg.fwd.techTest.be.web.model;

/*
Author : Gilang Permadi Khasani
Description : Grid Size for json request
Version : 0.1
Last Update : 17-05-2021
 */
public class Grid {

    Integer gridSize;

    public Integer getGridSize() {
        return gridSize;
    }

    public void setGridSize(Integer gridSize) {
        this.gridSize = gridSize;
    }

    @Override
    public String toString() {
        return "Grid [gridSize=" + gridSize + "]";
    }

}
