/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.petitgrand.myapplication;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import android.widget.TextView;

import com.petitgrand.myapplication.model.Game;
import com.petitgrand.myapplication.model.shapes.animals.Bear;
import com.petitgrand.myapplication.model.shapes.animals.Doe;
import com.petitgrand.myapplication.model.shapes.buttons.Equal;
import com.petitgrand.myapplication.model.shapes.animals.Fox;
import com.petitgrand.myapplication.model.shapes.animals.Hedgehog;
import com.petitgrand.myapplication.model.shapes.buttons.Minus;
import com.petitgrand.myapplication.model.shapes.buttons.Plus;
import com.petitgrand.myapplication.model.shapes.Polygon;
import com.petitgrand.myapplication.model.shapes.animals.Ant;
import com.petitgrand.myapplication.model.shapes.animals.Frog;
import com.petitgrand.myapplication.model.shapes.animals.Snail;
import com.petitgrand.myapplication.model.shapes.buttons.Save;

import java.util.ArrayList;
import java.util.List;

/* MyGLRenderer implémente l'interface générique GLSurfaceView.Renderer */

public class MyGLRenderer implements GLSurfaceView.Renderer {

    //private static final String TAG = "MyGLRenderer";

    // Les matrices habituelles Model/View/Projection
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    private final float[] mModelMatrix = new float[16];


    private List<Polygon> examples = new ArrayList<>();
    private List<Polygon> cards = new ArrayList<>();
    private List<Polygon> topStack = new ArrayList<>();

    private final Game game = new Game();

    private Polygon plus;
    private Polygon minus;
    private Polygon equal;
    private Polygon save;

    private boolean finish = false;

    private int topStackId = 0;
    private Polygon mycard;

    private TextView interaction;
    private final Context context;



    private void initExamplePolygons(){
        examples.add(new Ant(
                  Float.parseFloat(context.getResources().getString(R.string.ANT_X))
                , Float.parseFloat(context.getResources().getString(R.string.ANT_Y))
                ,0.5f));

        examples.add(new Snail(
                Float.parseFloat(context.getResources().getString(R.string.SNAIL_X))
                , Float.parseFloat(context.getResources().getString(R.string.SNAIL_Y))
                ,0.5f));

        examples.add(new Frog(
                Float.parseFloat(context.getResources().getString(R.string.FROG_X))
                , Float.parseFloat(context.getResources().getString(R.string.FROG_Y))
                ,0.5f));

        examples.add(new Hedgehog(
                Float.parseFloat(context.getResources().getString(R.string.HEDGEHOG_X))
                , Float.parseFloat(context.getResources().getString(R.string.HEDGEHOG_Y))
                ,0.5f));

        examples.add(new Fox(
                Float.parseFloat(context.getResources().getString(R.string.FOX_X))
                , Float.parseFloat(context.getResources().getString(R.string.FOX_Y))
                ,0.5f));

        examples.add(new Doe(
                Float.parseFloat(context.getResources().getString(R.string.DOE_X))
                , Float.parseFloat(context.getResources().getString(R.string.DOE_Y))
                ,0.5f));

        examples.add(new Bear(
                  Float.parseFloat(context.getResources().getString(R.string.BEAR_X))
                , Float.parseFloat(context.getResources().getString(R.string.BEAR_Y))
                ,0.5f));

    }

    private void initPlusMinusEqual(){
        plus = new Plus(
                Float.parseFloat(context.getResources().getString(R.string.PLUS_X))
                , Float.parseFloat(context.getResources().getString(R.string.PLUS_Y))
                ,0.5f);

        minus = new Minus(
                Float.parseFloat(context.getResources().getString(R.string.MINUS_X))
                , Float.parseFloat(context.getResources().getString(R.string.MINUS_Y))
                ,0.5f);

        equal = new Equal(
                Float.parseFloat(context.getResources().getString(R.string.EQUAL_X))
                , Float.parseFloat(context.getResources().getString(R.string.EQUAL_Y))
                ,0.5f);

        save = new Save(
                Float.parseFloat(context.getResources().getString(R.string.SAVE_X))
                , Float.parseFloat(context.getResources().getString(R.string.SAVE_Y))
                ,0.5f);
    }

    private void initCards(){
        for (int i = 0; i < game.NumberOfcards(); i++) {
            cards.add(new Ant((i * 0.5f) - 7f,-6.5f,0.5f));
        }
    }

    private void initCardTopStack(){
        topStackId = game.getFirstCardTopStackId();

        topStack.add(new Ant(
                Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_Y))
                ,1f));

        topStack.add(new Snail(
                Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_Y))
                ,1f));

        topStack.add(new Frog(
                Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_Y))
                ,1f));

        topStack.add(new Hedgehog(
                Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_Y))
                ,1f));

        topStack.add(new Fox(
                Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_Y))
                ,1f));

        topStack.add(new Doe(
                Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_Y))
                ,1f));

        topStack.add(new Bear(
                Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_Y))
                ,1f));

        topStack.get(topStackId).setPosition(
                Float.parseFloat(context.getResources().getString(R.string.CARD_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_Y)));
    }

    private void initHiddenCard() {
        mycard = new Ant(-4f,0.0f,5f);
    }

    private List<Polygon> allPolygons(){

        List<Polygon> result = new ArrayList<>(examples);
        result.add(plus);
        result.add(minus);
        result.add(equal);
        result.add(save);
        result.add(topStack.get(topStackId));
        result.add(mycard);
        result.addAll(cards);

        return result;
    }

    MyGLRenderer(TextView t, Context context){
        this.interaction = t;
        this.context = context;
    }


    /* Première méthode équivalente à la fonction init en OpenGLSL */
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        // la couleur du fond d'écran
        GLES30.glClearColor(0.925f, 0.917f, 0.894f, 1.0f);

        /* on va définir une classe Square pour dessiner des carrés */
        initExamplePolygons();
        initPlusMinusEqual();
        initCards();

        initCardTopStack();
        initHiddenCard();


    }

    /* Deuxième méthode équivalente à la fonction Display */
    @Override
    public void onDrawFrame(GL10 unused) {

        Log.d("NEW STEP","a new modification");

        // glClear rien de nouveau on vide le buffer de couleur et de profondeur */
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT );

        for (Polygon data : allPolygons()) {

            /*Log.d("START : ", data.getClass().toString());

            StringBuilder res = new StringBuilder();
            for ( float d: data.getCoords()
            ) {
                res.append(" ").append(Float.toString(d));
            }
            Log.d("COORDS : ", res.toString());

            Log.d("POSITION : ", "X  = "+Float.toString(data.getPosition()[0]));
            Log.d("POSITION : ", "Y = "+Float.toString(data.getPosition()[1]));*/



            float[] scratch = new float[16];

            Matrix.setIdentityM(mViewMatrix,0);

            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

            Matrix.setIdentityM(mModelMatrix,0);

            Matrix.translateM(mModelMatrix, 0, data.getPosition()[0], data.getPosition()[1], 0);

            Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mModelMatrix, 0);

            data.draw(scratch);
        }


    }

    /* équivalent au Reshape en OpenGLSL */
    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        /* ici on aurait pu se passer de cette méthode et déclarer
        la projection qu'à la création de la surface !!
         */
        GLES30.glViewport(0, 0, width, height);
        Matrix.orthoM(mProjectionMatrix, 0, -10.0f, 10.0f, -10.0f, 10.0f, -1.0f, 1.0f);

    }

    /* La gestion des shaders ... */
    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES30.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES30.GL_FRAGMENT_SHADER)
        int shader = GLES30.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);

        return shader;
    }


    float[] getPositionPlus() {
        return plus.getPosition();
    }

    float[] getPositionMinus() {
        return minus.getPosition();
    }

    float[] getPositionEqual() {
        return equal.getPosition();
    }

    float[] getPositionSave() { return save.getPosition(); }

    private void removeCard() {
        if(game.NumberOfcards() >= 0)
            cards.get(game.NumberOfcards())
                    .setPosition(
                    Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_X))
                    ,Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_Y)));
    }

    void test(int i) {
        topStack.get(topStackId).setPosition(
                Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_HIDDEN_Y))
                );

        if(game.isGoodAnswer(i)){
            if(game.win()){
                interaction.setText(R.string.winner);
                finish = true;
            }else{
                game.changeSetFirstTopStack();
                topStackId = game.getFirstCardTopStackId();

                interaction.setText(R.string.yes);
            }

            removeCard();

        }else{
            if(i == 2){
                interaction.setText(R.string.save);
                game.cleanTmp();
            }else {
                interaction.setText(R.string.no);

                for (int j = game.NumberOfcards(); j < game.NumberOfcards()+game.NumberInStack(); j++) {
                    cards.get(j).setPosition((j * 0.5f) - 7f, -6.5f);
                }

                game.failed();
                topStackId = game.getFirstCardTopStackId();
            }
        }
        topStack.get(topStackId).setPosition(
                Float.parseFloat(context.getResources().getString(R.string.CARD_X))
                , Float.parseFloat(context.getResources().getString(R.string.CARD_Y)));

        /*int count = 0;
        for(Polygon card : cards){
            if(card.getPosition()[0] != -20.0f){
                count ++;
            }
        }

        Log.d("COUNT CARDS LEFT"," DISPLAY = "+String.valueOf(count)+" REAL = "+String.valueOf(game.NumberOfcards()));*/
    }

    boolean isFinish() {
        return finish;
    }


}
