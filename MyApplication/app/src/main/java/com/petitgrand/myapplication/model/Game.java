package com.petitgrand.myapplication.model;

import android.util.Log;

import com.petitgrand.myapplication.model.shapes.animals.Ant;
import com.petitgrand.myapplication.model.shapes.animals.Bear;
import com.petitgrand.myapplication.model.shapes.animals.Doe;
import com.petitgrand.myapplication.model.shapes.animals.Fox;
import com.petitgrand.myapplication.model.shapes.animals.Frog;
import com.petitgrand.myapplication.model.shapes.animals.Hedgehog;
import com.petitgrand.myapplication.model.shapes.Polygon;
import com.petitgrand.myapplication.model.shapes.animals.Snail;

import java.util.Random;
import java.util.Stack;

public class Game {

    private Polygon topStack;

    private int size;

    private Stack<Polygon> mycards = new Stack<>();

    private static final int[] numberOfOccurence = {8,9,9,9,9,9,8};

    private int[] numberOfOccurenceCurrent;

    private Stack<Polygon> tmpcards = new Stack<>();

    public Game(){

        numberOfOccurenceCurrent = new int[6];

        for ( int i = 0; i < 30; i++ ){
            mycards.push(getPolygonById(getRandomId()));
        }

        for (Polygon card : this.mycards)
            Log.d("SET INITIAL",String.valueOf(card));

        this.size = mycards.size();
        this.topStack = new Doe(0.0f,0.0f,1f);
    }

    public boolean isGoodAnswer(int i) {
        switch (i){
            case -1 : return this.mycards.peek().getRank() < this.topStack.getRank() ;

            case 0 : return this.mycards.peek().getRank() == this.topStack.getRank() ;

            case 1 : return this.mycards.peek().getRank() > this.topStack.getRank() ;

            default: return false;
        }
    }

    public void changeSetFirstTopStack(){
        this.topStack = mycards.pop();
        tmpcards.push(topStack);

        /*for(int i=0; i < tmpcards.size(); i++)
            Log.d("TMP CARDS",tmpcards.get(i).toString());

        for(Polygon p : this.mycards){
            Log.d("MY CARDS",p.toString());
        }*/
    }

    public int getFirstCardTopStackId() {
        return this.topStack.getRank();
    }

    public boolean win() {
        return (mycards.size() == 1);
    }

    public int NumberOfcards() {
        return mycards.size();
    }

    public void failed() {
        if(size != mycards.size()){

            int i = 0;
            while(!tmpcards.isEmpty()){
                //Log.d("COUNT", String.valueOf(i));
                mycards.insertElementAt(tmpcards.pop(), 0);
                i++;
            }

            this.topStack = new Doe(0.0f,0.0f,1f);

            /*for (Polygon card : this.mycards)
                Log.d("MYCARD MODIFIED",String.valueOf(card));

            if(tmpcards.isEmpty()){
                Log.d("TMP CARD","EMPTY");

            }else{
                Log.d("TMP CARD","PAS VIDE");
                for( Polygon p : tmpcards){
                    Log.d("PUTAIN CARD", p.toString());
                }
            }*/
        }

    }

    public Polygon getPolygonById(int id){
        switch (id){
            case 0 : return new Ant(0.0f,0.0f,1f);

            case 1 : return new Snail(0.0f,0.0f,1f);

            case 2 : return new Frog(0.0f,0.0f,1f);

            case 3 : return new Hedgehog(0.0f,0.0f,1f);

            case 4 : return new Fox(0.0f,0.0f,1f);

            case 5 : return new Doe(0.0f,0.0f,1f);

            case 6 : return new Bear(0.0f,0.0f,1f);

            default: return null;
        }
    }

    public int getRandomId(){
        Random rand = new Random();

        int r;
        do {
            r = rand.nextInt(6);
        }while (numberOfOccurenceCurrent[r] > numberOfOccurence[r]);

        numberOfOccurenceCurrent[r]++;

        return r;
    }

    public void cleanTmp() {
        tmpcards.removeAllElements();
    }

    public int NumberInStack() {
        return tmpcards.size();
    }
}
