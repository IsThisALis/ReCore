package rexample;

    // Game imports
    // Resources
import rexample.resources.TextureManager;
import rexample.resources.Resources;
import rexample.resources.ShaderManager;
import rexample.resources.ResourceManager;
    // Game objects
import rexample.gmobj.ObjectManager;
    // Interaction
import rexample.interaction.*;

    // ReCore imports
    // Core
import recore.core.ApplicationLogic;
import recore.core.GameLoop;

    // Window
import recore.graphics.window.Params;
import recore.graphics.window.Window;
import recore.util.Input;

public class Main implements ApplicationLogic {
	    // Getting instances
  private Params params = Params.getParams();
  private static Window window = Params.getWindowInst();
  private ShaderManager shaderManager = Resources.getShaderManager();
  private TextureManager textureManager = Resources.getTextureManager();
  private ResourceManager resourceManager = Resources.getResourceManager();
  private ObjectManager objectManager = new ObjectManager();
  private Collizion collizion = new Collizion();
  private InputActions inputActions;
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
    inputActions = new InputActions();
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
    inputActions.update();
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
