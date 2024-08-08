package Game;

import java.util.Random;
import java.util.Scanner;

public class Game{
    private LinkedList<Integer> rooms;
    private int playerPosition;
    private ArrayStack<Integer> moveStack;
    private ArrayStack<Integer> treasureStack;
    private static final int MAX_ROOMS = 10;
    private static final int MAX_DOORS = 10;
    
    //hazinenin listesi
    private int[][] treasureMap = { 
        {2, 3},
        {1, 4},
        {1, 4},
        {2, 3, 5},
        {4, 6},
        {5, 7},
        {6, 8},
        {7, 9},
        {8, 10},
        {9}
    };

    private int[][] doorMap ={
        {2, 3},
        {1, 4},
        {1, 4},
        {2, 3, 5},
        {4, 6},
        {5, 7},
        {6, 8},
        {7, 9},
        {8, 10},
        {9}
    };

    public Game(){
        rooms = new LinkedList<>();
        moveStack = new ArrayStack<>(100);  
        treasureStack = new ArrayStack<>(10); 
        loadMap(); // Haritayı yükler
        initializeRooms();
        playerPosition = new Random().nextInt(MAX_ROOMS) + 1; // Zar atıp başlangıc noktasını belirler
    }

    private void loadMap(){
        for (int i = 0; i < doorMap.length; i++) {
            rooms.addNode(i + 1, MAX_DOORS);
            Node<Integer> room = rooms.getNode(i + 1);
            for (int j = 0; j < doorMap[i].length; j++) {
                room.doors[j] = doorMap[i][j];
            }
        }
    }

    // Odaları başlatır, hazine  ve tuzakları konumlandırır.
    private void initializeRooms(){
        Node<Integer> temp = rooms.head;
        int roomId = 1;
        while (temp != null){
            boolean hasTreasure = false;
            for (int treasureRoom : treasureMap[roomId - 1]) {
                if (treasureRoom == roomId) {
                    temp.treasure = true;
                    hasTreasure = true;
                }
            }
            if (!hasTreasure){
                temp.trap = true;
            }
            temp = temp.next;
            roomId++;
        }
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            if (rooms.isEmpty()){
                System.out.println(" There is no room ");
                break;
            }
            Node<Integer> currentRoom = rooms.getNode(playerPosition);
            System.out.print(" Current Room :  " + currentRoom.data + ", Doors: ");
            for (int door : currentRoom.doors) {
                if (door != 0) System.out.print(door + " ");
            }
            System.out.println();
            System.out.print(" Treasures Collected :  ");
            Object[] treasures = treasureStack.getAllElements();
            for (Object treasure : treasures){
                System.out.print(treasure + " ");
            }
            System.out.println();
            System.out.print(" Choose a door to move:  ");
            int move = scanner.nextInt();
            if (isValidMove(currentRoom, move)) {
                moveStack.push(playerPosition);
                playerPosition = move;
                handleRoom(rooms.getNode(playerPosition));
            } else{
                System.out.println(" There is no room with that number ");
            }
        }
        scanner.close();
    }

    private boolean isValidMove(Node<Integer> currentRoom, int move){
        for (int door : currentRoom.doors){
            if (door == move){
                return true;
            }
        }
        return false;
    }

    private void handleRoom(Node<Integer> room){
        if (room.treasure){
            System.out.println("You found a treasure!");
            treasureStack.push(room.data);
            room.treasure = false;
        } else if (room.trap){
            System.out.println("You failed its a trap! Rolling dice now ");
            if (rollDice()){
                System.out.println("You passed the trap!");
                room.trap = false;
            } else{
                System.out.println("You failed to passed the trap! You are going back to previous room.");
                if (!moveStack.isEmpty()){
                    playerPosition = moveStack.pop();
                }
            }
        }
    }

    private boolean rollDice(){
        return new Random().nextInt(6) % 2 == 0;
    }

    public static void main(String[] args){
        Game game = new Game();
        game.play();
    }
}
