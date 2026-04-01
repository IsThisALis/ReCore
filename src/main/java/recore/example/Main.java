package recore.example;

    // Game components imports
import recore.example.resources.TextureManager;
import recore.example.resources.Resources;
import recore.example.resources.ShaderManager;
import recore.example.gmobj.ObjectManager;
import recore.example.resources.ResourceManager;
// ReCore imports
    // Core
import recore.core.ApplicationLogic;

    // Window
import recore.graphics.window.Params;
import recore.graphics.window.Window;

public class Main implements ApplicationLogic {
	    // Getting instances 
  Params params = Params.getParams();
  Window window = Params.getWindowInst();
  ShaderManager shaderManager = Resources.getShaderManager();
  TextureManager textureManager = Resources.getTextureManager();
  ResourceManager resourceManager = Resources.getResourceManager();
  ObjectManager objectManager = new ObjectManager();

  private static Main main = new Main();

	@Override
	public void init() {
		// Window settings
    params.setTitle("ReCore");
    params.setHeight(1200);
    params.setWidth(1920);
       // Initializing components
    window.init();
    shaderManager.init();
    textureManager.init();
    resourceManager.init();
    objectManager.init();
	}

	@Override
	public void cleanup() {
	    // TODO: cleanup
		System.out.println("ReCore: Cleanup");
	}

	@Override
	public void loop() {
        while (!window.isWindowShouldClose()) {
            // Window and rendering loops
		    window.loop();
        objectManager.draw();
        }
  }

	@Override
	public void update() {

	}




	public static void main(String[] args) {
		// Initialization and game loop
	    main.init();
      main.loop();
	}

}
