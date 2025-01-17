package com.thanhozin.cochiemthanh.manager;

import com.thanhozin.cochiemthanh.model.Ability;
import com.thanhozin.cochiemthanh.model.Chess;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ThanhND on 10/23/2017.
 */


/*
Ý tưởng thiết kế thuật toán di chuyển quân cờ
 - mỗi khi clịc vào một vị trí trên bàn cờ sẽ kiểm tra vị trí được click chứa gì, và nếu nó là quân cờ thì kiểm tra lần
 lượt 4 hướng dông, tây, nam, bắc. Nếu hướng nào bị chặn thì k tạo các ô khẳ năng có thể bước đến của quân cờ và ngược lại
 và lưu lại quân cờ được click vào một biến nhơ chessRemember
 - Xét di chuyên nếu khi click vào 1 vị trí mà vị trí đấy là một ô khả năng thì set lại tọa độ cho quân cờ đã được lưu
 đến vị trí mới là vị trí click
 KẾT THÚC THUẬT TOÁN
 */
public class GameManager {
    private ArrayList<Chess> chesss;
    private ArrayList<Ability> abilities;
    private Chess chessRemember;
    private char[] listXLocation = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private boolean flagsFly;


    public GameManager() {
        chesss = new ArrayList<>();
        abilities = new ArrayList<>();
        flagsFly=false;
        initalizeChess();
    }

    //chuyển từ tọa độ x của máy ra ký hiệu tọa độ bàn cờ
    private char coverXLocation(int xLocation) {
        if (xLocation >= 34 && xLocation < 111) {
            return 'a';
        }
        if (xLocation >= 114 && xLocation < 188) {
            return 'b';
        }
        if (xLocation >= 192 && xLocation < 268) {
            return 'c';
        }
        if (xLocation >= 271 && xLocation < 348) {
            return 'd';
        }
        if (xLocation >= 351 && xLocation < 428) {
            return 'e';
        }
        if (xLocation >= 430 && xLocation < 508) {
            return 'f';
        }
        if (xLocation >= 510 && xLocation < 584) {
            return 'g';
        }
        if (xLocation >= 587 && xLocation < 665) {
            return 'h';
        }
        return ' ';
    }

    //chuyển từ ký hiệu tọa độ trên bàn cờ ra tọa độ máy
    private int unCoverXLocation(char charX) {
        switch (charX) {
            case 'a':
                return 36;
            case 'b':
                return 116;
            case 'c':
                return 194;
            case 'd':
                return 273;
            case 'e':
                return 353;
            case 'f':
                return 432;
            case 'g':
                return 512;
            case 'h':
                return 589;
            default:
                return 0;
        }
    }

    //chuyển từ tọa độ y của máy ra ký hiệu tọa độ bàn cờ
    private int coverYLocation(int yLocation) {
        if (yLocation >= 35 && yLocation < 110) {
            return 1;
        }
        if (yLocation >= 113 && yLocation < 191) {
            return 2;
        }
        if (yLocation >= 193 && yLocation < 270) {
            return 3;
        }
        if (yLocation >= 272 && yLocation < 349) {
            return 4;
        }
        if (yLocation >= 352 && yLocation < 428) {
            return 5;
        }
        if (yLocation >= 430 && yLocation < 507) {
            return 6;
        }
        if (yLocation >= 510 && yLocation < 585) {
            return 7;
        }
        if (yLocation >= 588 && yLocation < 665) {
            return 8;
        }
        return 0;
    }

    //chuyển từ ký hiệu tọa độ trên bàn cờ ra tọa dộ máy
    private int unCoverYLocation(int intY) {
        switch (intY) {
            case 1:
                return 37;
            case 2:
                return 115;
            case 3:
                return 195;
            case 4:
                return 274;
            case 5:
                return 354;
            case 6:
                return 432;
            case 7:
                return 512;
            case 8:
                return 590;
            default:
                return 0;
        }
    }

    public void initalizeChess() {
        Chess chessWhileK = new Chess(unCoverXLocation('a'), unCoverYLocation(8), Chess.WHILE_K);
        chesss.add(chessWhileK);
        Chess chessWhileL = new Chess(unCoverXLocation('d'), unCoverYLocation(8), Chess.WHILE_L);
        chesss.add(chessWhileL);
        Chess chessWhileM = new Chess(unCoverXLocation('h'), unCoverYLocation(8), Chess.WHILE_M);
        chesss.add(chessWhileM);

        Chess chessBlackO = new Chess(unCoverXLocation('a'), unCoverYLocation(1), Chess.BLACK_O);
        chesss.add(chessBlackO);
        Chess chessBlackP = new Chess(unCoverXLocation('e'), unCoverYLocation(1), Chess.BLACK_P);
        chesss.add(chessBlackP);
        Chess chessBlackQ = new Chess(unCoverXLocation('h'), unCoverYLocation(1), Chess.BLACK_Q);
        chesss.add(chessBlackQ);


    }

    public void drawChess(Graphics2D graphics2D) {
        for (int i = 0; i < chesss.size(); i++) {
            chesss.get(i).drawChess(graphics2D);
        }
    }

    public void drawAbility(Graphics2D graphics2D) {
        for (int i = 0; i < abilities.size(); i++) {
            abilities.get(i).drawAbility(graphics2D);
        }
    }

    public void checkType(String type){
        if (type=="ENTER"){
        }else if (type=="SPACE"){

        }else {
            for (int i=0;i<chesss.size();i++){
                if (chesss.get(i).getType()==type){
                    chessRemember= chesss.get(i);
                    initalizeAbility();
                }
            }
        }
    }

    public void checkOnClick(int xOnClick, int yOnClick) {
        int tempChess = -1;
        int countOfChesss = 0;
        int countOfAbility = 0;
        char coordinatesOfX = coverXLocation(xOnClick);
        int coordinatesOfY = coverYLocation(yOnClick);

        for (int i = 0; i < chesss.size(); i++) {
            Chess chess = chesss.get(i);
            if (coordinatesOfX == coverXLocation(chess.getX()) && coordinatesOfY == coverYLocation(chess.getY())) {
                tempChess = i;
                countOfChesss = 1;
                break;
            }
        }

        for (int i = 0; i < abilities.size(); i++) {
            Ability ability = abilities.get(i);
            if (coordinatesOfX == coverXLocation(abilities.get(i).getX()) && coordinatesOfY == coverYLocation(abilities.get(i).getY())) {
                countOfAbility = 1;
                break;
            }
        }

        //Kiểm tra xem vị trí vừa click chứa quân cờ hay khả năng di chuyển
        if (countOfChesss == 1 && countOfAbility == 1) {
            /*Nếu vị trí vừa click chứa quân cờ và ô khả năng
            -> Thì thay chỗ quân cò đối phương bằng quân cờ được lưu tại chessRemember
            -> Và xóa hết tất cả các ô khă năng (Ability)
            */
            chesss.remove(tempChess);
            abilities.clear();
            moveChess(coordinatesOfX, coordinatesOfY);
        } else {
            if (tempChess != -1) {
                if (!flagsFly) {
                    chessRemember = chesss.get(tempChess);
                }
            }
            if (countOfChesss == 1) {
            /*
            Nếu vị trí click là 1 quân cờ
            -> Thì xóa hết các ô khẳ năng cũ và cập nhật ô khả năng mới cho quân cơ
             */
                if (!flagsFly) {
                    abilities.clear();
                    initalizeAbility();
                }

            } else if (countOfAbility == 1) {
            /*
            Nếu vị trí vừa click là 1 ô khả năng
            -> Thì di chuyển quân cờ được lưu trong chessRemeber đến vị trí ô được click
            -> Và xóa hết tất cả các ô khả năng
             */
                if (flagsFly){
                    flagsFly=false;
                }
                abilities.clear();
                moveChess(coordinatesOfX, coordinatesOfY);
            } else {
            /*
            Nếu vị trí vừa click là một ô trống
            -> thì chi xóa đi các ô khả năng.
             */
                if (!flagsFly) {
                    abilities.clear();
                }
            }

        }
    }


    //Kiểm tra tạo ra các ô khả năng
    private void initalizeAbility() {
        char xOfChessOnSelect = coverXLocation(chessRemember.getX());
        int yOfChessOnSelect = coverYLocation(chessRemember.getY());
        int indexOfXInList = -2;

        //truyền dãy các chữ cái vào 1 mảng để có thể dễ dàng tiến lùi vị trí của ô cần sét
        //lấy ra số thứ tự trong mmảng theo vị trí
        for (int i = 0; i < 8; i++) {
            if (listXLocation[i] == xOfChessOnSelect) {
                indexOfXInList = i;
                break;
            }
        }


        //Kiểm tra theo hường bắc
        if (yOfChessOnSelect > 2) {
            int tempIndexOfX = indexOfXInList;
            char tempXNorth = xOfChessOnSelect;
            int tempYNorth = yOfChessOnSelect - 1;
            int tempCount = 0;
            for (int i = 0; i < chesss.size(); i++) {
                if (coverXLocation(chesss.get(i).getX()) == tempXNorth && coverYLocation(chesss.get(i).getY()) == tempYNorth) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                int yAbility = tempYNorth - 1;
                //Kiểm tra vị trí được click có gần biên bàn cờ hay không.
                // Các phần code dưới tương tự
                if (tempXNorth != 'a') {
                    char xAbility = listXLocation[tempIndexOfX - 1];
                    if (!checkColor(chessRemember.coverType(chessRemember.getType()), xAbility, yAbility)) {
                        Ability ability = new Ability(unCoverXLocation(xAbility) - 2, unCoverYLocation(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempXNorth != 'h') {
                    char xAbility = listXLocation[tempIndexOfX + 1];
                    if (!checkColor(chessRemember.coverType(chessRemember.getType()), xAbility, yAbility)) {
                        Ability ability = new Ability(unCoverXLocation(xAbility) - 2, unCoverYLocation(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
            }
        }


        //Kiểm tra theo hướng Đông
        if (indexOfXInList < 6) {
            int tempIndexOfX = indexOfXInList;
            char tempXEast = listXLocation[tempIndexOfX + 1];
            int tempYEast = yOfChessOnSelect;
            int tempCount = 0;
            for (int i = 0; i < chesss.size(); i++) {
                if (coverXLocation(chesss.get(i).getX()) == tempXEast && coverYLocation(chesss.get(i).getY()) == tempYEast) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                char xAbility = listXLocation[tempIndexOfX + 2];
                if (tempYEast != 1) {
                    int yAbility = tempYEast - 1;
                    if (!checkColor(chessRemember.coverType(chessRemember.getType()), xAbility, yAbility)) {
                        Ability ability = new Ability(unCoverXLocation(xAbility) - 2, unCoverYLocation(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempYEast != 8) {
                    int yAbility = tempYEast + 1;
                    if (!checkColor(chessRemember.coverType(chessRemember.getType()), xAbility, yAbility)) {
                        Ability ability = new Ability(unCoverXLocation(xAbility) - 2, unCoverYLocation(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
            }
        }


        //Kiểm tra theo hướng nam
        if (yOfChessOnSelect < 7) {
            int tempIndexOfX = indexOfXInList;
            char tempXSouth = xOfChessOnSelect;
            int tempYSouth = yOfChessOnSelect + 1;
            int tempCount = 0;
            for (int i = 0; i < chesss.size(); i++) {
                if (coverXLocation(chesss.get(i).getX()) == tempXSouth && coverYLocation(chesss.get(i).getY()) == tempYSouth) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                int yAbility = tempYSouth + 1;
                if (tempXSouth != 'a') {
                    char xAbility = listXLocation[tempIndexOfX - 1];
                    if (!checkColor(chessRemember.coverType(chessRemember.getType()), xAbility, yAbility)) {
                        Ability ability = new Ability(unCoverXLocation(xAbility) - 2, unCoverYLocation(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempXSouth != 'h') {
                    char xAbility = listXLocation[tempIndexOfX + 1];
                    if (!checkColor(chessRemember.coverType(chessRemember.getType()), xAbility, yAbility)) {
                        Ability ability = new Ability(unCoverXLocation(xAbility) - 2, unCoverYLocation(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
            }
        }


        //Kiểm tra theo hướng Tây
        if (indexOfXInList > 1) {
            int tempIndexOfX = indexOfXInList;
            char tempXEast = listXLocation[tempIndexOfX - 1];
            int tempYEast = yOfChessOnSelect;
            int tempCount = 0;
            for (int i = 0; i < chesss.size(); i++) {
                if (coverXLocation(chesss.get(i).getX()) == tempXEast && coverYLocation(chesss.get(i).getY()) == tempYEast) {
                    tempCount++;
                    break;
                }
            }
            if (tempCount == 0) {
                char xAbility = listXLocation[tempIndexOfX - 2];
                if (tempYEast != 1) {
                    int yAbility = tempYEast - 1;
                    if (!checkColor(chessRemember.coverType(chessRemember.getType()), xAbility, yAbility)) {
                        Ability ability = new Ability(unCoverXLocation(xAbility) - 2, unCoverYLocation(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
                if (tempYEast != 8) {
                    int yAbility = tempYEast + 1;
                    if (!checkColor(chessRemember.coverType(chessRemember.getType()), xAbility, yAbility)) {
                        Ability ability = new Ability(unCoverXLocation(xAbility) - 2, unCoverYLocation(yAbility) - 2);
                        abilities.add(ability);
                    }
                }
            }
        }
    }

    //Kiểm tra màu của quân cờ ở vị trí ô khả năng
    public boolean checkColor(char typeChess, char x, int y) {
        int temp = -1;
        for (int i = 0; i < chesss.size(); i++) {
            if (coverXLocation(chesss.get(i).getX()) == x && coverYLocation(chesss.get(i).getY()) == y) {
                temp = i;
                break;
            }
        }
        if (temp >= 0) {
            if (chesss.get(temp).coverType(chesss.get(temp).getType()) == typeChess) {
                return true;
            } else
                return false;
        } else
            return false;
    }

    private void moveChess(char coordinatesOfX, int coordinatesOfY) {
        int tempChess = -1;
        char xOfChessOnSelect = coverXLocation(chessRemember.getX());
        int yOfChessOnSelect = coverYLocation(chessRemember.getY());
        for (int i = 0; i < chesss.size(); i++) {
            if (coverXLocation(chesss.get(i).getX()) == xOfChessOnSelect && coverYLocation(chesss.get(i).getY()) == yOfChessOnSelect) {
                tempChess = i;
                break;
            }
        }
        chesss.get(tempChess).setX(unCoverXLocation(coordinatesOfX));
        chesss.get(tempChess).setY(unCoverYLocation(coordinatesOfY));

        if (coordinatesOfX == 'd' && coordinatesOfY == 1) {
            if (chessRemember.coverType(chessRemember.getType()) == 'W') {
                abilities.clear();
                supreAbility();
                Ability ability1 = new Ability(unCoverXLocation('c') - 2, unCoverYLocation(6) - 2);
                Ability ability2 = new Ability(unCoverXLocation('e') - 2, unCoverYLocation(6) - 2);
                Ability ability3 = new Ability(unCoverXLocation('b') - 2, unCoverYLocation(7) - 2);
                Ability ability4 = new Ability(unCoverXLocation('f') - 2, unCoverYLocation(7) - 2);
                Ability ability5 = new Ability(unCoverXLocation('d') - 2, unCoverYLocation(8) - 2);
                Ability ability6 = new Ability(unCoverXLocation('e') - 2, unCoverYLocation(8) - 2);
                abilities.add(ability1);
                abilities.add(ability2);
                abilities.add(ability3);
                abilities.add(ability4);
                abilities.add(ability5);
                abilities.add(ability6);
                for (int i = 0; i < chesss.size(); i++) {
                    char xChess = coverXLocation(chesss.get(i).getX());
                    int yChess = coverYLocation(chesss.get(i).getY());
                    for (int j = 0; j < abilities.size(); j++) {
                        if (coverXLocation(abilities.get(j).getX()) == xChess && coverYLocation(abilities.get(j).getY()) == yChess) {
                            abilities.remove(j);
                            break;
                        }
                    }
                }
                flagsFly=true;
            }
        }
        if (coordinatesOfX == 'e' && coordinatesOfY == 8) {
            if (chessRemember.coverType(chessRemember.getType()) == 'B') {
                abilities.clear();
                supreAbility();
                Ability ability1 = new Ability(unCoverXLocation('e') - 2, unCoverYLocation(1) - 2);
                Ability ability2 = new Ability(unCoverXLocation('d') - 2, unCoverYLocation(1) - 2);
                Ability ability3 = new Ability(unCoverXLocation('c') - 2, unCoverYLocation(2) - 2);
                Ability ability4 = new Ability(unCoverXLocation('g') - 2, unCoverYLocation(2) - 2);
                Ability ability5 = new Ability(unCoverXLocation('d') - 2, unCoverYLocation(3) - 2);
                Ability ability6 = new Ability(unCoverXLocation('f') - 2, unCoverYLocation(3) - 2);
                abilities.add(ability1);
                abilities.add(ability2);
                abilities.add(ability3);
                abilities.add(ability4);
                abilities.add(ability5);
                abilities.add(ability6);

                //Xóa bỏ các ô có quân cờ
                for (int i = 0; i < chesss.size(); i++) {
                    char xChess = coverXLocation(chesss.get(i).getX());
                    int yChess = coverYLocation(chesss.get(i).getY());
                    for (int j = 0; j < abilities.size(); j++) {
                        if (coverXLocation(abilities.get(j).getX()) == xChess && coverYLocation(abilities.get(j).getY()) == yChess) {
                            abilities.remove(j);
                            break;
                        }
                    }
                }
                flagsFly=true;
            }
        }


    }

    public void supreAbility() {
        for (int y = 1; y <= 8; y++) {
            for (int x = 0; x < 8; x++) {
                char tempx = listXLocation[x];
                if (y == 1 && tempx == 'd' || y == 1 && tempx == 'e' || y == 2 && tempx == 'c' || y == 2 && tempx == 'g' ||
                        y == 3 && tempx == 'd' || y == 3 && tempx == 'f' || y == 6 && tempx == 'c' || y == 6 && tempx == 'e' ||
                        y == 7 && tempx == 'b' || y == 7 && tempx == 'f' || y == 8 && tempx == 'd' || y == 8 && tempx == 'e') {

                } else {
                    Ability ability = new Ability(unCoverXLocation(tempx) - 2, unCoverYLocation(y) - 2);
                    abilities.add(ability);
                }
            }
        }


    }
}
