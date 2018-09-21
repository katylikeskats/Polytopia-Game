/**
 *[Terrain.java]
 * The Terrain object class
 * @author Katelyn Wang and Brian Li
 * June 14 2018
 */
abstract public class Terrain {
    int row;
    int col;

    /**
     * Terrain Constructor
     * @param row the row coordinate of the terrain
     * @param col the column coordinate of the terrain
     */
    Terrain(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * The row coordinate getter
     * @return the row coordinate
     */
    public int getRow() {
        return row;
    }

    /**
     * The row coordinate setter
     * @param row the new row coordinate
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * The column coordinate getter
     * @return the column coordiante
     */
    public int getCol() {
        return col;
    }

    /**
     * Setting the column coordinate
     * @param col the new column coordinate
     */
    public void setCol(int col) {
        this.col = col;
    }

}
