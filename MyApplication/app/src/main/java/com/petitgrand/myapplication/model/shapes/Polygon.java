package com.petitgrand.myapplication.model.shapes;

import android.opengl.GLES30;
import com.petitgrand.myapplication.MyGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class Polygon {

    /* Le vertex shader avec la définition de gl_Position et les variables utiles au fragment shader */
    private static final String VERTEX_SHADER_CODE =
            "#version 300 es\n"+
                    "uniform mat4 uMVPMatrix;\n"+
                    "in vec3 vPosition;\n" +
                    "in vec4 vCouleur;\n"+
                    "out vec4 Couleur;\n"+
                    "out vec3 Position;\n"+
                    "void main() {\n" +
                    "Position = vPosition;\n"+
                    "gl_Position = uMVPMatrix * vec4(vPosition,1.0);\n" +
                    "Couleur = vCouleur;\n"+
                    "}\n";

    private static final String FRAGMENT_SHADER_CODE =
            "#version 300 es\n"+
                    "precision mediump float;\n" + // pour définir la taille d'un float
                    "in vec4 Couleur;\n"+
                    "in vec3 Position;\n"+
                    "out vec4 fragColor;\n"+
                    "void main() {\n" +
                    "float x = Position.x;\n"+
                    "float y = Position.y;\n"+
                    "fragColor = Couleur;\n" +
                    "}\n";


    /* les déclarations pour l'équivalent des VBO */

    private final FloatBuffer vertexBuffer; // Pour le buffer des coordonnées des sommets du carré
    private final ShortBuffer indiceBuffer; // Pour le buffer des indices
    private final FloatBuffer colorBuffer; // Pour le buffer des couleurs des sommets

    /* les déclarations pour les shaders
    Identifiant du programme et pour les variables attribute ou uniform
     */
    private final int IdProgram; // identifiant du programme pour lier les shaders

    private static final int COORDS_PER_VERTEX = 3; // nombre de coordonnées par vertex
    private static final int COULEURS_PER_VERTEX = 4; // nombre de composantes couleur par vertex

    private int rank;

/* Attention au repère au niveau écran (x est inversé)
 Le tableau des coordonnées des sommets
 Oui ce n'est pas joli avec 1.0 en dur ....
 */

    private float[] Coords;

    // Le carré est dessiné avec 2 triangles
    private short[] Indexes;

    private float[] Position;

    private void scaled(float scale, float[] coords){
        this.Coords = new float[coords.length];
        for (int i = 0; i < this.Coords.length; i++) {
            this.Coords[i] = coords[i] * scale;
        }
    }

    public Polygon(float PosX, float PosY, float scale, int rank, float[] coords, float[] colors, short[] indexes ) {

        scaled(scale,coords);
        // Le tableau des couleurs
        this.Indexes = indexes;
        this.Position = new float[]{PosX, PosY};
        this.rank = rank;

        // initialisation du buffer pour les vertex (4 bytes par float)
        ByteBuffer bb = ByteBuffer.allocateDirect(Coords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(Coords);
        vertexBuffer.position(0);

        // initialisation du buffer pour les couleurs (4 bytes par float)
        ByteBuffer bc = ByteBuffer.allocateDirect(colors.length * 4);
        bc.order(ByteOrder.nativeOrder());
        colorBuffer = bc.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        // initialisation du buffer des indices
        ByteBuffer dlb = ByteBuffer.allocateDirect(Indexes.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        indiceBuffer = dlb.asShortBuffer();
        indiceBuffer.put(Indexes);
        indiceBuffer.position(0);

        /* Chargement des shaders */
        int vertexShader = MyGLRenderer.loadShader(GLES30.GL_VERTEX_SHADER, VERTEX_SHADER_CODE);
        int fragmentShader = MyGLRenderer.loadShader(GLES30.GL_FRAGMENT_SHADER, FRAGMENT_SHADER_CODE);

        IdProgram = GLES30.glCreateProgram();             // create empty OpenGL Program
        GLES30.glAttachShader(IdProgram, vertexShader);   // add the vertex shader to program
        GLES30.glAttachShader(IdProgram, fragmentShader); // add the fragment shader to program
        GLES30.glLinkProgram(IdProgram);                  // create OpenGL program executables
        int[] linkStatus = {0};
        GLES30.glGetProgramiv(IdProgram, GLES30.GL_LINK_STATUS, linkStatus,0);


    }






    /* La fonction Display */
    public void draw(float[] mvpMatrix) {
        // Add program to OpenGL environment
        GLES30.glUseProgram(IdProgram);

        // get handle to shape's transformation matrix
        // identifiant (location) pour transmettre la matrice PxVxM
        int idMVPMatrix = GLES30.glGetUniformLocation(IdProgram, "uMVPMatrix");

        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(idMVPMatrix, 1, false, mvpMatrix, 0);


        // get handle to vertex shader's vPosition member et vCouleur member
        // idendifiant (location) pour transmettre les coordonnées au vertex shader
        int idPosition = GLES30.glGetAttribLocation(IdProgram, "vPosition");
        // identifiant (location) pour transmettre les couleurs
        int idCouleur = GLES30.glGetAttribLocation(IdProgram, "vCouleur");

        /* Activation des Buffers */
        GLES30.glEnableVertexAttribArray(idPosition);
        GLES30.glEnableVertexAttribArray(idCouleur);

        /* Lecture des Buffers */
        // le pas entre 2 sommets : 4 bytes per vertex
        int vertexStride = COORDS_PER_VERTEX * 4;
        GLES30.glVertexAttribPointer(
                idPosition, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        // le pas entre 2 couleurs
        int couleurStride = COULEURS_PER_VERTEX * 4;
        GLES30.glVertexAttribPointer(
                idCouleur, COULEURS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                couleurStride, colorBuffer);

        GLES30.glDrawElements(
                GLES30.GL_TRIANGLES, Indexes.length,
                GLES30.GL_UNSIGNED_SHORT, indiceBuffer);

        GLES30.glDisableVertexAttribArray(idPosition);
        GLES30.glDisableVertexAttribArray(idCouleur);

    }

    public float[] getPosition(){ return this.Position; }

    public void setPosition(float pX, float pY) { this.Position = new float[]{pX,pY}; }

    public int getRank() {
            return rank;
    }

    @Override
    public String toString(){
        return "Polygon";
    }

}


