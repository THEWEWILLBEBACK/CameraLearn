package com.adnonstop.cameranew.gl;

import android.opengl.GLES20;
import android.view.View;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Xiejq on 2017/11/24.
 * 绘制一个三角形的render
 */

public class Triangle extends Shape {

    private FloatBuffer vertexBuffer;//纹理buffer
    private final String vertexShaderCode =   //添加顶点着色脚本
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    private final String fragmentShaderCode =  //添加片元着色脚本
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private int mProgram;

    static final int COORDS_PRE_VERTES = 3;
    static float triangleDoords[] = {
            0.5f, 0.5f, 0.0f,//top
            -0.5f, -0.5f, 0.0f,//bottom left
            0.5f, -0.5f, 0.0f //bottom right
    };

    private int mPositionHandle;
    private int mColorHandler;

    private float[] mViewMatrix = new float[16];

    private final int vertexCount = triangleDoords.length / COORDS_PRE_VERTES;//顶点个数
    //顶点之间的偏移量
    private final int vertexStride = COORDS_PRE_VERTES * 4;//每个顶点4个字节

    //设置颜色
    float color[] = {1.0f, 1.0f, 1.0f, 1.0f};

    public Triangle(View view) {
        super(view);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(triangleDoords.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuffer.asFloatBuffer();
        vertexBuffer.put(triangleDoords);
        vertexBuffer.position(0);

        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmetShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        //创建一个空的OpenglES程序
        mProgram = GLES20.glCreateProgram();
        //将顶点着色器添加到程序中
        GLES20.glAttachShader(mProgram, vertexShader);
        //将片元着色器添加到程序中
        GLES20.glAttachShader(mProgram, fragmetShader);
        //连接到着色器
        GLES20.glLinkProgram(mProgram);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        //将程序加入到OpenGles2.0环境
        GLES20.glUseProgram(mProgram);

        //获取顶点着色器的vPostion成员句柄
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        //启用三角形的顶点句柄
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        //准备三角形的坐标句柄
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PRE_VERTES, GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandler, 1, color, 0);
        //绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        //禁止顶点数组的句柄
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
