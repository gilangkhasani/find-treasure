package sg.fwd.techTest.be.web.service;

import sg.fwd.techTest.be.web.model.Point;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/*
Author : Gilang Permadi Khasani
Description : Initialized Game & Rules the Game
Version : 0.1
Last Update : 18-05-2021
 */

public class Initiator {
    Integer gridSize = 0;
    ArrayList<String> treasures = new ArrayList<String>();
    ArrayList<Point> treasureLocations = new ArrayList<Point>();
    Point monsterLocation, playerLocation;

    //Initialize a new game
    public Point initGame(Integer gridSizeRequest){
        treasures.add("Diamond");

        gridSize = gridSizeRequest;

        ArrayList<Point>occupied_locations = new ArrayList<Point>();
        playerLocation = new Point(0, 0, true, "Starting Player Location");
        //Position the player in the top left corner of the grid (0, 0)
        occupied_locations.add(playerLocation);

        //Choose a random location for the monster, excluding the player"s location
        monsterLocation = chooseUnoccupiedLocation(occupied_locations);

        //Randomly locate each of the treasures in unoccupied spaces
        for (int i = 0; i < treasures.size(); i++) {
            treasureLocations.add(chooseUnoccupiedLocation(occupied_locations));
        }
        return playerLocation;
    }

    //Find a point within a list and return its position, or -1 if not found
    public Integer findPoint(ArrayList<Point> list, Point pt) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isEqualTo(pt)){
                return i;
            }
        }

        return -1;
    }

    //Choose a random unoccupied position within a list of used locations.  The new location is added to the
    //list, and the new location is returned.
    public Point chooseUnoccupiedLocation(ArrayList<Point> usedLocations){
        while (true){
            Point location = new Point(ThreadLocalRandom.current().nextInt(1, gridSize), ThreadLocalRandom.current().nextInt(1, gridSize), true, "Monster Location");
            if (findPoint(usedLocations, location) < 0){
                usedLocations.add(location);
                return location;
            }

        }

    }

    //Handle the player entering a new location in the game
    public Point enterLocation(Point location){
        playerLocation = location;
        System.out.println("You are now in location (" + playerLocation.x + "," + playerLocation.y + ")");

        //If the monster is here, the game is over
        if (monsterLocation.isEqualTo(playerLocation)){
            System.out.println("Oh no!!  You've been eaten by a hungry monster!");
            playerLocation = new Point(location.x, location.y, false, "Oh no!!  You've been eaten by a hungry monster!");
        }

        //If the monster is nearby, let the player know
        if (monsterLocation.isAdjacentTo(playerLocation)){
            playerLocation = new Point(location.x, location.y, true, "You can hear a growling sound nearby!");
            System.out.println("You can hear a growling sound nearby!");
        }

        //Determine if any treasure is in the location
        Integer treasureIndex = findPoint(treasureLocations, playerLocation);

        if (treasureIndex >= 0){
            System.out.println("You found the " + treasures.get(treasureIndex));
            playerLocation = new Point(location.x, location.y, true, "You found the " + treasures.get(treasureIndex));
            treasures.remove(treasures.get(treasureIndex));

            //Determine if the game has been won (i.e., all treasure found).  Otherwise, let the player know how many
            //treasures are left.
            if (treasures.size() == 0){
                System.out.println("You found all treasures!  You win!!");
                playerLocation = new Point(location.x, location.y, false, "You found all treasures!  You win!!");
            } else {
                Integer remainingTreasures = treasures.size();
                System.out.println("You have " + remainingTreasures + " more treasure to find!");
            }
        }
        return playerLocation;
    }

    public Point processCommand(String command){
        Point newLocation;
        if(command.isEmpty()){
            System.out.println("Your command is empty, Whats your command ?");
            newLocation = new Point(playerLocation.x, playerLocation.y, true, "Your command is empty, Whats your command ?");
        }

        //Handle a possible movement command
        switch (command.toLowerCase()){
            case "w": // Up
                if (playerLocation.x > 0){
                    newLocation = new Point(playerLocation.x - 1, playerLocation.y, true, "Player Move Up");
                    break;
                }
            case "s": // Down
                if (playerLocation.x < gridSize){
                    newLocation = new Point(playerLocation.x + 1, playerLocation.y, true, "Player Move Down");
                    break;
                }
            case "a": // Left
                if (playerLocation.y > 0){
                    newLocation = new Point(playerLocation.x, playerLocation.y - 1, true, "Player Move Left");
                    break;
                }
            case "d": // Right
                if (playerLocation.y < gridSize){
                    newLocation = new Point(playerLocation.x, playerLocation.y + 1, true, "Player Move Right");
                    break;
                }
            default:
                System.out.println("I don't know what you mean");
                newLocation = new Point(playerLocation.x, playerLocation.y, true, "I don't know what you mean");
        }
        if (newLocation == null){
            System.out.println( "You can't move in that direction");
            newLocation = new Point(playerLocation.x, playerLocation.y, true, "You can't move in that direction");
        }

        return enterLocation(newLocation);
    }

}

