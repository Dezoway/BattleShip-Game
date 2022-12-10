package main;

public enum Messages {
    /*
        Набор фраз для диалогового окна во время игры
     */
    PrepareShips("Расстановка кораблей..."),
    BattleReady("Всё готово к началу боя"),
    PrepareBattle("Расстановка кораблей противником..."),
    StartBattle("Бой начался"),
    MissFirePlayer("Вы промахнулись"),
    MissFireComputerPlayer("Противник промахнулся"),
    HittingTheDeckPlayer("Вы попали по палубе"),
    HittingTheDeckComputerPlayer("Противник попал по палубе"),
    PlayerDestroyedShip("Вы уничтожили корабль противника"),
    ComputerPlayerDestroyedShip("Противник уничтожил корабль"),
    PlayerWin("Вы выиграли сражение"),
    PlayerLose("Вы проиграли сражение");




    private String message; // Приватная строка для отображения текущих действий в игре
    Messages(String text){
        this.message = text;
    }

    @Override
    public String toString(){ // Метод для получения строки
        return this.message;
    }
}
