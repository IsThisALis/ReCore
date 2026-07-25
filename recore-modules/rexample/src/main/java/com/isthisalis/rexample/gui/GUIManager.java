package com.isthisalis.rexample.gui;

import com.isthisalis.recore.graphics.material.Material;
import com.isthisalis.recore.graphics.render.Mesh;
import com.isthisalis.recore.graphics.shaders.ShaderProgram;

import com.isthisalis.recore.gui.*;

import com.isthisalis.rephysics.shapes.Square;

import com.isthisalis.rexample.interaction.InputActions;
import com.isthisalis.rexample.resources.MeshFactory;
import com.isthisalis.rexample.resources.Resources;
import com.isthisalis.rexample.resources.TextureFactory;

public class GUIManager {

  private static MeshFactory meshFactory = Resources.getMeshFactory();
  private static TextureFactory textureFactory = Resources.getTextureFactory();

  private static ShaderProgram program;
  private static UIElement exit;
  private static Material mat;

public static void init() {
    program = Resources.getShaderProgramMap().getObj("ct2");
    
    exit = new UIElement();
    exit.setPosition(1920f / 2f, 1080f / 2f);
    exit.setSize(100f, 100f);
    
    mat = new Material(textureFactory.newTexture("samples/textures/exit.png", program), program);
    mat.setTransparency(true);
    
    Mesh uiMesh = meshFactory.newMesh(program);
    uiMesh.setScale(10f, 10f);
    exit.UIRenderSettings(uiMesh, mat);
    
    UIElement.initMesh(Square.getVertices(), Square.getIndices()); 
    
    exit.setOnClick(() -> {
        System.out.println("CLICK");
        InputActions.close();
    });
    
    exit.newElement(exit);
    exit.setTransparent(false);
}

  public static void update() {
    exit.update();
    if(exit.onElement()) System.out.println("ON OBJ");
  }


  public static void render() {
    exit.render();
  }
}
