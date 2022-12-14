package entities;

import enums.*;
import exceptions.checked.ItemNotFoundException;
import interfaces.*;
import items.*;
import places.*;

import java.util.Objects;

public class Human extends Entity implements PutAble, PraiseAble,
        SeemAble, SmackAble, SquintAble, QuackAble, SitAble, UnSitAble{
    private final Lips lips = new Lips("Губы");

    public Human(){
        super.setName("Человек");
    }

    public Human(String name){
        super(name);
    }

    public Human(String name, Place location){
        super(name, location);
    }

    public Lips getLips() {
        return lips;
    }

    public void put(IsPutable canBePut, TakePutAble canTakePut) throws ItemNotFoundException {
        if(!this.getItems().contains((Item) canBePut)){
            throw new ItemNotFoundException("Item is not in inventory: " + canBePut.getName());
        }
        System.out.println(this.getName() + " положил " + canBePut.getName()
                + " на " + canTakePut.getName());
        canTakePut.addItems((Item) canBePut);
        this.removeItem((Item) canBePut);
    }

    public void sit(IsSitable isSitable){
        if(!isSitable.isBusy()){
            System.out.println(this.getName() + " сел на " + isSitable.getName() + " в " + this.getLocation().getName());
            isSitable.setBusy(true);
        }
        else{
            System.out.println(this.getName() + " не смог сесть на " + isSitable.getName() + ", так как он занят");
        }
    }

    public void unSit(IsSitable isSitable){
        if(isSitable.isBusy()){
            System.out.println(this.getName() + " покинул " + isSitable.getName());
            isSitable.setBusy(false);
        }
        else{
            System.out.println(this.getName() + " не находится на " + isSitable.getName());
        }
    }

    public void praise(IsPraiseable ... isPraiseables){
        StringBuilder temp = new StringBuilder();
        for(IsPraiseable praiseable : isPraiseables){
            temp.append(praiseable.getName()).append(", ");
        }
        temp = new StringBuilder(temp.substring(0, temp.length() - 2));
        System.out.println(this.getName() + " хвалит " + temp);
    }

    public void seem(Status status, IsSeemable ... isSeemables){
        StringBuilder temp = new StringBuilder();
        for(IsSeemable seemable : isSeemables){
            temp.append(seemable.getName()).append(", ");
        }
        temp = new StringBuilder(temp.substring(0, temp.length() - 2));
        System.out.println(this.getName() + " кажется " + status.getName() + ": " + temp);
    }

    public void quack(){
        System.out.println(this.getName() + " крякает");
    }

    public void squint(){
        System.out.println(this.getName() + " жмурится");
    }

    public void smack(IsSmackable isSmackable){
        System.out.println(this.getName() + " чмокает " + isSmackable.getName());
    }

    private class Lips implements IsSmackable{
        private String name;

        public Lips(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return "Human{" +
                "lips=" + lips +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Human human = (Human) o;
        return Objects.equals(lips, human.lips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lips);
    }
}