package org.gfs.recore.example;

    // Game components imports
import org.gfs.recore.example.graphic.Render;
import org.gfs.recore.example.graphic.TextureManager;

    // ReCore imports
    // Core
import org.gfs.recore.core.ApplicationLogic;

    // Window
import org.gfs.recore.graphics.window.Params;
import org.gfs.recore.graphics.window.Window;

public class Main implements ApplicationLogic {
	    // Getting instances
  TextureManager textureManager = new TextureManager();
  Render render = new Render();

  Params params = Params.getParams();
  Window window = Params.getWindowInst();

  InputManager inputManager = new InputManager();

  private static Main main = new Main();

	@Override
	public void init() {
		// Window settings
    params.setTitle("ReCore");
    params.setHeight(1200);
    params.setWidth(1920);
       // Initializing components
    window.init(); 
    render.init();
    inputManager.init();
	}

	@Override
	public void cleanup() {
	    // TODO: cleanup
		System.out.println("ReCore: Cleanup");
	}

	@Override
	public void loop() {
        while (!window.isWindowShouldClose()) {

            // Time util work
        inputManager.time.tick();

            // Window and rendering loops
		    window.loop();
        main.input();
        main.update();
        render.update();
        }
  }

	@Override
	public void input() {
    inputManager.input();
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
