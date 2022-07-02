package com.mygdx.magicstorm.hero;

public class Mana {
    private int currentMana;
    private int maxMana;
    private String currentManaString = currentMana + "/" + maxMana;
    public Mana(int currentMana, int maxMana) {
        this.currentMana = currentMana;
        this.maxMana = maxMana;
    }


    public String getCurrentManaString() {
        return this.currentManaString;
    }

    public void setCurrentMana(int mana) {
        this.currentMana = mana;
    }

    public void setMaxMana(int mana) {
        this.maxMana = mana;
    }

    public void setCurrentManaString(String string) {
        this.currentManaString = string;
    }
    public int getCurrentMana() {
        return currentMana;
    }
    public int getMaxMana() {
        return maxMana;
    }
}
