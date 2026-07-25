package com.isthisalis.rexample;

    // Game imports
    // Resources
import com.isthisalis.rexample.resources.TextureManager;
import com.isthisalis.rexample.resources.Resources;
import com.isthisalis.rexample.resources.ShaderManager;
import com.isthisalis.rexample.resources.ResourceManager;
    // Game objects
import com.isthisalis.rexample.gmobj.ObjectManager;

    // Interaction
import com.isthisalis.rexample.interaction.*;

    // ReCore imports
    // Core
import com.isthisalis.recore.core.ApplicationLogic;
import com.isthisalis.recore.core.GameLoop;

    // Window
import com.isthisalis.recore.graphics.window.Params;
import com.isthisalis.recore.graphics.window.Window;
import com.isthisalis.recore.util.Input;

public class Main implements ApplicationLogic {
	    // Getting instances
  private Params params = Params.getParams();
  private static Window window = Params.getWindowInst();
  private ShaderManager shaderManager = Resources.getShaderManager();
  private TextureManager textureManager = Resources.getTextureManager();
  private ResourceManager resourceManager = Resources.getResourceManager();
  private ObjectManager objectManager = new ObjectManager();
  private Collizion collizion = new Collizion();

  private static Main main = new Main();

	@Override
	public void init() {
		// Window settings
    params.setTitle("ReCore");
    params.setHeight(1200);
    params.setWidth(1920);
    params.setVsyncStatus(true);
       // Initializing components
    window.init();
    window.setIcon("samples/textures/icon.png");
    Input.setCursor("samples/textures/cursor_default.png");
    shaderManager.init();
    textureManager.init();
    resourceManager.init();
    objectManager.init();
    collizion.init();
	}

	@Override
	public void cleanup() {
	    // TODO: cleanup
		System.out.println("ReCore: Cleanup");
	}

	@Override
	public void loop() {
          // Window and rendering loops
    GameLoop.getGameLoop().startFrame();
    InputActions.update();
    collizion.update();
    objectManager.draw();
    window.update();
  }

	@Override
	public void update() {

	}




	public static void main(String[] args) {
		// Initialization and game loop
	    main.init();
      while(!window.isWindowShouldClose()) {
        main.loop();
      }
	}
}
