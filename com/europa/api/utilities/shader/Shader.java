/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 *  org.lwjgl.opengl.ARBShaderObjects
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL20
 */
package com.europa.api.utilities.shader;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class Shader {
    public int program;
    public Map<String, Integer> uniformsMap;

    public Shader(String fragmentShader) {
        int fragmentShaderID;
        int vertexShaderID;
        try {
            InputStream vertexStream = this.getClass().getResourceAsStream("/assets/europa/shader/vertex.vert");
            vertexShaderID = this.createShader(IOUtils.toString((InputStream)vertexStream, (Charset)Charset.defaultCharset()), 35633);
            IOUtils.closeQuietly((InputStream)vertexStream);
            InputStream fragmentStream = this.getClass().getResourceAsStream("/assets/europa/shader/" + fragmentShader);
            fragmentShaderID = this.createShader(IOUtils.toString((InputStream)fragmentStream, (Charset)Charset.defaultCharset()), 35632);
            IOUtils.closeQuietly((InputStream)fragmentStream);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (vertexShaderID == 0 || fragmentShaderID == 0) {
            return;
        }
        this.program = ARBShaderObjects.glCreateProgramObjectARB();
        if (this.program == 0) {
            return;
        }
        ARBShaderObjects.glAttachObjectARB((int)this.program, (int)vertexShaderID);
        ARBShaderObjects.glAttachObjectARB((int)this.program, (int)fragmentShaderID);
        ARBShaderObjects.glLinkProgramARB((int)this.program);
        ARBShaderObjects.glValidateProgramARB((int)this.program);
    }

    public void startShader() {
        GL11.glPushMatrix();
        GL20.glUseProgram((int)this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap<String, Integer>();
            this.setupUniforms();
        }
        this.updateUniforms();
    }

    public void stopShader() {
        GL20.glUseProgram((int)0);
        GL11.glPopMatrix();
    }

    public abstract void setupUniforms();

    public abstract void updateUniforms();

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int createShader(String string, int n) {
        StringBuilder stringBuilder;
        RuntimeException runtimeException;
        void shaderSource;
        void v0;
        int shader = 0;
        try {
            void shaderType;
            v0 = shaderType;
        }
        catch (Exception e) {
            ARBShaderObjects.glDeleteObjectARB((int)shader);
            throw e;
        }
        int n2 = ARBShaderObjects.glCreateShaderObjectARB((int)v0);
        shader = n2;
        if (shader == 0) {
            return 0;
        }
        int n3 = shader;
        void v3 = shaderSource;
        ARBShaderObjects.glShaderSourceARB((int)n3, (CharSequence)v3);
        int n4 = shader;
        ARBShaderObjects.glCompileShaderARB((int)n4);
        int n5 = shader;
        int n6 = 35713;
        int n7 = ARBShaderObjects.glGetObjectParameteriARB((int)n5, (int)n6);
        if (n7 != 0) return shader;
        RuntimeException runtimeException2 = runtimeException;
        RuntimeException runtimeException3 = runtimeException;
        StringBuilder stringBuilder2 = stringBuilder;
        StringBuilder stringBuilder3 = stringBuilder;
        stringBuilder2();
        String string2 = "Error creating shader: ";
        StringBuilder stringBuilder4 = stringBuilder3.append(string2);
        Shader shader2 = this;
        int n8 = shader;
        String string3 = shader2.getLogInfo(n8);
        StringBuilder stringBuilder5 = stringBuilder4.append(string3);
        String string4 = stringBuilder5.toString();
        runtimeException2(string4);
        throw runtimeException3;
    }

    /*
     * WARNING - void declaration
     */
    public String getLogInfo(int n) {
        void i;
        return ARBShaderObjects.glGetInfoLogARB((int)i, (int)ARBShaderObjects.glGetObjectParameteriARB((int)i, (int)35716));
    }

    /*
     * WARNING - void declaration
     */
    public void setUniform(String string, int n) {
        void location;
        void uniformName;
        this.uniformsMap.put((String)uniformName, (int)location);
    }

    /*
     * WARNING - void declaration
     */
    public void setupUniform(String string) {
        void uniformName;
        this.setUniform((String)uniformName, GL20.glGetUniformLocation((int)this.program, (CharSequence)uniformName));
    }

    /*
     * WARNING - void declaration
     */
    public int getUniform(String string) {
        void uniformName;
        return this.uniformsMap.get(uniformName);
    }

    public int getProgramId() {
        return this.program;
    }
}

