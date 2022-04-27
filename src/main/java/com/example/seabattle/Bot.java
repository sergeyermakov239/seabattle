package com.example.seabattle;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {
    public static final String TOKEN = "5056913305:AAGIe3Fi25jLQWF-2pCwgTnh_Wx__EyFa_A";
    public static final String USERNAME = "BestWeather239bot";
    public int t = 0;  //place in code
    public int t1=0;
    int f=0;
    int[][] player1field =new int[10][10];
    int[][] player2field =new int[10][10];
    int[][] player1shots =new int[10][10];
    int[][] player2shots =new int[10][10];
    int x;
    int y;
    int k1=20;
    int k2=20;
    int g=1;
    int b=1;


    public static String drawfield(int[][] a){
        String s="";
        for (int y=0;y<10;y++){
            for (int x=0;x<10;x++){
                if (a[x][y]==1){
                    s=s+"1|";
                }else if(a[x][y]==-1){
                    s=s+"0|";
                } else{
                    s=s+" |";
                }
            };
            s=s+"\n";
        }
        return s;
    }

    public static String drawfieldofshots(int[][] a){
        String s="";
        for (int y=0;y<10;y++){
            for (int x=0;x<10;x++){
                if (a[x][y]==1){
                    s=s+"1|";
                } else if (a[x][y]==2) {
                    s=s+"0|";
                } else {
                    s=s+" |";
                }
            };
            s=s+"\n";
        };
        return s;

    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage sendMessage = new SendMessage();
            Long chatId = update.getMessage().getChatId();
            sendMessage.setChatId(String.valueOf(chatId));
            String message = String.valueOf(update.getMessage().getText());
            if (t == 0) {
                sendMessage.setText("Игра морской бой");
                execute(sendMessage);
                sendMessage.setText("Первый игрок, расставьте свои корабли");
                execute(sendMessage);
                sendMessage.setText("Введите координату х корабля");
                execute(sendMessage);
                t=1;
                t1=0;
                f=0;

            } else if (t==1){
                if (t1<19){
                    if (f==0) {
                        x = Integer.parseInt(message);
                        sendMessage.setText("Введите координату y корабля");
                        execute(sendMessage);
                        f=1;
                    } else{
                        y = Integer.parseInt(message);
                        sendMessage.setText("Введите координату x корабля");
                        execute(sendMessage);
                        f=0;
                        t1=t1+1;
                        player1field[x-1][y-1]=1;
                    }
                } else if (t1==19){
                    if (f==0) {
                        x = Integer.parseInt(message);
                        sendMessage.setText("Введите координату y корабля");
                        execute(sendMessage);
                        f=1;
                    } else{
                        y = Integer.parseInt(message);
                        player1field[x-1][y-1]=1;
                        String s=drawfield(player1field);
                        sendMessage.setText("Корабли первого игрока");
                        execute(sendMessage);
                        sendMessage.setText(s);
                        execute(sendMessage);
                        sendMessage.setText("Второй игрок, расставьте свои корабли");
                        execute(sendMessage);
                        sendMessage.setText("Введите координату x корабля");
                        execute(sendMessage);
                        f=0;
                        t1=0;
                        t=2;

                    }
                }
            } else if (t==2){
                if (t1<19){
                    if (f==0) {
                        x = Integer.parseInt(message);
                        sendMessage.setText("Введите координату y корабля");
                        execute(sendMessage);
                        f=1;
                    } else{
                        y = Integer.parseInt(message);
                        sendMessage.setText("Введите координату x корабля");
                        execute(sendMessage);
                        f=0;
                        t1=t1+1;
                        player2field[x-1][y-1]=1;
                    }
                } else if (t1==19){
                    if (f==0) {
                        x = Integer.parseInt(message);
                        sendMessage.setText("Введите координату y корабля");
                        execute(sendMessage);
                        f=1;
                    } else{
                        y = Integer.parseInt(message);
                        player2field[x-1][y-1]=1;
                        sendMessage.setText("Корабли второго игрока");
                        execute(sendMessage);
                        String s=drawfield(player2field);
                        sendMessage.setText(s);
                        execute(sendMessage);
                        sendMessage.setText("Ход первого игрока");
                        execute(sendMessage);
                        sendMessage.setText("Введите, КУДА ВЫ СТРЕЛЯЕТЕ");
                        execute(sendMessage);
                        sendMessage.setText("Введите координату х корабля");
                        execute(sendMessage);
                        f=0;
                        t1=0;
                        t=3;

                    }
                }
            } else if (t==3){
                if (k1>0&&k2>0){
                    if (g==1){
                        if (b==1&&k2>0){
                            if (f==0) {
                                x = Integer.parseInt(message)-1;
                                sendMessage.setText("Введите координату y корабля");
                                execute(sendMessage);
                                f=1;
                            } else{
                                y = Integer.parseInt(message)-1;
                                if (player2field[x][y]==1){
                                    if (k2==1){
                                        b=0;
                                        k2=0;
                                        player2field[x][y]=-1;
                                        player1shots[x][y]=1;
                                        f=0;
                                    } else{
                                        sendMessage.setText("Вы попали! Стреляете ещё раз");
                                        execute(sendMessage);
                                        player2field[x][y]=-1;
                                        k2=k2-1;
                                        player1shots[x][y]=1;
                                        f=0;
                                        sendMessage.setText("Поле ваших выстрелов");
                                        execute(sendMessage);
                                        String s=drawfieldofshots(player1shots);
                                        sendMessage.setText(s);
                                        execute(sendMessage);
                                        sendMessage.setText("Введите координату х корабля");
                                        execute(sendMessage);

                                    }
                                } else{
                                    sendMessage.setText("Вы промазали, ход переходит к другому игроку");
                                    execute(sendMessage);
                                    g=2;
                                    b=1;
                                    f=0;
                                    player1shots[x][y]=2;
                                    player2field[x][y]=-1;
                                    sendMessage.setText("Поле ваших выстрелов");
                                    execute(sendMessage);
                                    String s=drawfieldofshots(player1shots);
                                    sendMessage.setText(s);
                                    execute(sendMessage);
                                    sendMessage.setText("Ход второго игрока");
                                    execute(sendMessage);
                                    sendMessage.setText("Введите, КУДА ВЫ СТРЕЛЯЕТЕ");
                                    execute(sendMessage);
                                    sendMessage.setText("Введите координату х корабля");
                                    execute(sendMessage);

                                };
                            }
                        }
                    } else{
                        if (b==1&&k1>0){
                            if (f==0) {
                                x = Integer.parseInt(message)-1;
                                sendMessage.setText("Введите координату y корабля");
                                execute(sendMessage);
                                f=1;
                            } else{
                                y = Integer.parseInt(message)-1;
                                if (player1field[x][y]==1){
                                    if (k1==1){
                                        b=0;
                                        k1=0;
                                        player1field[x][y]=-1;
                                        player2shots[x][y]=1;
                                        f=0;
                                    } else{
                                        sendMessage.setText("Вы попали! Стреляете ещё раз");
                                        execute(sendMessage);
                                        player1field[x][y]=-1;
                                        k1=k1-1;
                                        player2shots[x][y]=1;
                                        f=0;
                                        sendMessage.setText("Поле ваших выстрелов");
                                        execute(sendMessage);
                                        String s=drawfieldofshots(player2shots);
                                        sendMessage.setText(s);
                                        execute(sendMessage);
                                        sendMessage.setText("Введите координату х корабля");
                                        execute(sendMessage);
                                    }
                                } else{
                                    sendMessage.setText("Вы промазали, ход переходит к другому игроку");
                                    execute(sendMessage);
                                    g=1;
                                    b=1;
                                    f=0;
                                    player2shots[x][y]=2;
                                    player1field[x][y]=-1;
                                    sendMessage.setText("Поле ваших выстрелов");
                                    execute(sendMessage);
                                    String s=drawfieldofshots(player1shots);
                                    sendMessage.setText(s);
                                    execute(sendMessage);
                                    sendMessage.setText("Ход первого игрока");
                                    execute(sendMessage);
                                    sendMessage.setText("Введите, КУДА ВЫ СТРЕЛЯЕТЕ");
                                    execute(sendMessage);
                                    sendMessage.setText("Введите координату х корабля");
                                    execute(sendMessage);
                                };
                            }
                        }
                    }
                } else{
                    if (g==1){
                        sendMessage.setText("Победил первый игрок!");
                        execute(sendMessage);
                    } else{
                        sendMessage.setText("Победил второй игрок!");
                        execute(sendMessage);
                    }
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}