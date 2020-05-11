package com.petitgrand.myapplication;



import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

/* Ce tutorial est issu d'un tutorial http://developer.android.com/training/graphics/opengl/index.html :
openGLES.zip HelloOpenGLES20
 */


public class OpenGLES20Activity extends Activity {

    // le conteneur View pour faire du rendu OpenGL

    public GLSurfaceView myGLSurfaceView;

    public TextView Title;

    public TextView Instruction;

    public TextView Interaction;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        /* Création de View et association à Activity
           MyGLSurfaceView : classe à implémenter et en particulier la partie renderer */

        /* Pour le plein écran */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        /* Définition de View pour cette activité */


        Title = new TextView(this);
        Title.setText(R.string.titre);
        Title.setTextColor(Color.BLACK);
        Title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);

        Instruction = new TextView(this);
        Instruction.setText(R.string.instructions);
        Instruction.setTextColor(Color.BLACK);
        Instruction.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Instruction.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        Interaction = new TextView(this);
        Interaction.setText("");
        Interaction.setTextColor(Color.BLACK);
        Interaction.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Interaction.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        myGLSurfaceView = new MyGLSurfaceView(this,Interaction);
        setContentView(myGLSurfaceView);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.START;
        params.gravity = Gravity.CENTER_HORIZONTAL;
        addContentView(Title, params);

        FrameLayout.LayoutParams paramsInstruction = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsInstruction.topMargin = 400;
        paramsInstruction.gravity = Gravity.CENTER_HORIZONTAL;
        addContentView(Instruction, paramsInstruction);

        FrameLayout.LayoutParams paramsInteraction = new FrameLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsInteraction.topMargin = metrics.heightPixels- 500;
        paramsInteraction.gravity = Gravity.CENTER_HORIZONTAL;
        addContentView(Interaction, paramsInteraction);

    }


}
