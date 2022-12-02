public enum Colors{
    RED("Красный"),
    GREEN("Зеленый"),
    BLUE("Синий"),
    WHITE("Белый"),
    BLACK("Черный"),
    PURPLE("Фиолетовый"),
    CYAN("Голубой"),
    MAGENTA("Пурпурный"),
    BROWN("Коричневый"),
    GRAY("Серый"),
    LIGHT_GRAY("Светло-серый");

    private final String color;

    Colors(String color){
        this.color = color;
    }

    public String getName(){
        return color;
    }
}
