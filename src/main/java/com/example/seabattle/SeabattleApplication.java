package com.example.seabattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SeabattleApplication {
    public static void drawfield(int[][] a){
        for (int y=0;y<10;y++){
            for (int x=0;x<10;x++){
                if (a[x][y]==1){
                    System.out.print("1|");
                }else if(a[x][y]==-1){
                    System.out.print("0|");
                } else{
                    System.out.print(" |");
                }
            };
            System.out.println();
        }
    }
    public static void drawfieldofshots(int[][] a){
        for (int y=0;y<10;y++){
            for (int x=0;x<10;x++){
                if (a[x][y]==1){
                    System.out.print("1|");
                } else if (a[x][y]==2) {
                    System.out.print("0|");
                } else {
                        System.out.print(" |");
                    }
                };
            System.out.println();
            };

        }


    public static void main(String[] args) {
        SpringApplication.run(SeabattleApplication.class, args);
        Scanner scanner=new Scanner(System.in);
        System.out.println("Игра морской бой");
        System.out.println("Первый игрок, расставьте свои корабли");
        int[][] player1field =new int[10][10];
        int k1=20;         //кол-во неубитых клеток у 1-го игрока, 1 4-палубный, 2 3-палубных, 3 2-палубных, 4 1-палубных
        int[][] player2field =new int[10][10];
        int[][] player1shots =new int[10][10];
        int[][] player2shots =new int[10][10];
        int k2=20;
        int g=1;     //кто ходит
        for (int i=0;i<20;i++){
            System.out.println("Введите координату х корабля");
            int x=scanner.nextInt();
            System.out.println("Введите координату y корабля");
            int y=scanner.nextInt();
            player1field[x-1][y-1]=1;
        };
        drawfield(player1field);
        System.out.println("Второй игрок, расставьте свои корабли");
        for (int i=0;i<20;i++){
            System.out.println("Введите координату х корабля");
            int x=scanner.nextInt();
            System.out.println("Введите координату y корабля");
            int y=scanner.nextInt();
            player2field[x-1][y-1]=1;
        };
        drawfield(player2field);
        while (k1>0&&k2>0){
            if (g==1){
                int b=1;
                while (b==1&&k2>0) {
                    System.out.println("Ход первого игрока");
                    System.out.println("Введите, КУДА ВЫ СТРЕЛЯЕТЕ");
                    System.out.println("Введите координату х корабля");
                    int x = scanner.nextInt()-1;
                    System.out.println("Введите координату y корабля");
                    int y = scanner.nextInt()-1;
                    if (player2field[x][y]==1){
                        if (k2==1){
                            b=0;
                            k2=0;
                            player2field[x][y]=-1;
                            player1shots[x][y]=1;
                        } else{
                        System.out.println("Вы попали! Стреляете ещё раз");
                        player2field[x][y]=-1;
                        k2=k2-1;
                        player1shots[x][y]=1;
                        }
                    } else{
                        System.out.println("Вы промазали, ход переходит к другому игроку");
                        g=2;
                        b=0;
                        player1shots[x][y]=2;
                        player2field[x][y]=-1;
                    };
                    System.out.println("Поле ваших выстрелов");
                    drawfieldofshots(player1shots);
                    System.out.println("Ваше поле");
                    drawfield(player1field);
                }
            } else {
                int b=1;
                while (b==1&&k1>0) {
                    System.out.println("Ход второго игрока");
                    System.out.println("Введите, КУДА ВЫ СТРЕЛЯЕТЕ");
                    System.out.println("Введите координату х корабля");
                    int x = scanner.nextInt()-1;
                    System.out.println("Введите координату y корабля");
                    int y = scanner.nextInt()-1;
                    if (player1field[x][y]==1){
                        if (k1==1){
                            b=0;
                            k1=0;
                            player1field[x][y]=-1;
                            player2shots[x][y]=1;
                        } else{
                            System.out.println("Вы попали! Стреляете ещё раз");
                            player1field[x][y]=-1;
                            k1=k1-1;
                            player2shots[x][y]=1;}
                    } else{
                        System.out.println("Вы промазали, ход переходит к другому игроку");
                        g=1;
                        b=0;
                        player2shots[x][y]=2;
                        player1field[x][y]=-1;
                    };
                    System.out.println("Поле ваших выстрелов");
                    drawfieldofshots(player2shots);
                    System.out.println("Ваше поле");
                    drawfield(player2field);
                }
            }
        };
        if (g==1){
            System.out.println("Победил первый игрок!");
        } else{
            System.out.println("Победил второй игрок!");
        }
    }

}
