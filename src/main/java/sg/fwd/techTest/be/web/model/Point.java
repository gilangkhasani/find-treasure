package sg.fwd.techTest.be.web.model;

import com.fasterxml.jackson.annotation.JsonView;
import sg.fwd.techTest.be.web.jsonview.Views;
/*
Author : Gilang Permadi Khasani
Description : Point represent of Matrix Coordinate when the game begin
Version : 0.1
Last Update : 17-05-2021
 */


public class Point {
    @JsonView(Views.Public.class)
    public Integer x = 0;
    @JsonView(Views.Public.class)
    public Integer y = 0;
    @JsonView(Views.Public.class)
    public Boolean status;
    @JsonView(Views.Public.class)
    public String locationDetail;

    //Constructor for represent matrix x and y
    public Point(Integer x, Integer y, Boolean status, String locationDetail) {
        this.x = x;
        this.y = y;
        this.status = status;
        this.locationDetail = locationDetail;
    }

    //current location compared to other things on the current location
    public Boolean isEqualTo(Point pt) {
        if (pt == null) {
            return false;
        }

        if (this.x == pt.x && this.y == pt.y) {
            return true;
        }

        return false;
    }

    //compare the current location to another thing around the current location
    public Boolean isAdjacentTo(Point pt){
        if ((this.y < pt.y - 1) || (this.y > pt.y + 1)){
            return false;
        }

        if ((this.x < pt.x - 1) || (this.x > pt.x + 1)){
            return false;
        }

        return true;
    }

}

