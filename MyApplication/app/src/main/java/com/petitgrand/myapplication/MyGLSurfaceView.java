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

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.TextView;

/* La classe MyGLSurfaceView avec en particulier la gestion des événements
  et la création de l'objet renderer

*/


/* On va dessiner un carré qui peut se déplacer grace à une translation via l'écran tactile */

public class MyGLSurfaceView extends GLSurfaceView {

    /* Un attribut : le renderer (GLSurfaceView.Renderer est une interface générique disponible) */
    /* MyGLRenderer va implémenter les méthodes de cette interface */

    private final MyGLRenderer mRenderer;


    public MyGLSurfaceView(Context context,TextView t) {
        super(context);

        t.setText(R.string.gamble);

        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        // Création d'un context OpenGLES 2.0
        setEGLContextClientVersion(3);

        // Création du renderer qui va être lié au conteneur View créé
        mRenderer = new MyGLRenderer(t,context);
        setRenderer(mRenderer);

        // Option pour indiquer qu'on redessine uniquement si les données changent
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    /* Comment interpréter les événements sur l'écran tactile */
    @Override
    public boolean onTouchEvent(MotionEvent e) {


        /* accès aux paramètres du rendu (cf MyGLRenderer.java)
        soit la position courante du centre du carré
         */
        float[] positionPlus = mRenderer.getPositionPlus();
        float[] positionMinus = mRenderer.getPositionMinus();
        float[] positionEqual = mRenderer.getPositionEqual();
        float[] positionSave = mRenderer.getPositionSave();


        boolean testPlus = isTouch(positionPlus,e);
        boolean testMinus = isTouch(positionMinus,e);
        boolean testEqual = isTouch(positionEqual,e);
        boolean testSave = isTouch(positionSave,e);


        //Log.d("message","test_square="+ testPlus);

        if(e.getAction() == MotionEvent.ACTION_UP && !mRenderer.isFinish()) {
            if (testPlus) {  mRenderer.test(1); }

            else { if (testMinus) { mRenderer.test(-1); }

            else { if (testEqual) { mRenderer.test(0); }

            else { if (testSave) { mRenderer.test(2); }}}}

            requestRender(); // équivalent de glutPostRedisplay pour lancer le dessin avec les modifications.
        }

        return true;
    }

    public boolean isTouch(float[] position, MotionEvent motionEvent){

        // Les coordonnées du point touché sur l'écran
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        // Des messages si nécessaires */
        //Log.d("message", "x = "+ x);
        //Log.d("message", "y = "+ y);

        float x_opengl = 20.0f*x/getWidth() - 10.0f;
        float y_opengl = -20.0f*y/getHeight() + 10.0f;

        //Log.d("message","x_opengl = "+x_opengl);
        //Log.d("message","y_opengl = "+y_opengl);

        return ((x_opengl < position[0]+1.0) && (x_opengl > position[0]-1.0) && (y_opengl < position[1]+1.0) && (y_opengl > position[1]-1.0));
    }


}
