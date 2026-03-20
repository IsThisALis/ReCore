package org.gfs.recore.example;

    // Game components imports
import org.gfs.recore.example.graphic.Render;
import org.gfs.recore.example.graphic.TextureManager;

    // ReCore imports
    // Core
import org.gfs.recore.core.ApplicationLogic;

    // Util
import org.gfs.recore.util.KeyboardInput;

    // Window
import org.gfs.recore.graphics.window.Params;
import org.gfs.recore.graphics.window.Window;

public class Main implements ApplicationLogic {
	    // Getting instances
  TextureManager textureManager = new TextureManager();
  Render render = new Render();

  Params params = Params.getParams();
  Window window = Params.getWindowInst();

  KeyboardInput KeyboardInput = new KeyboardInput();

      // Initializing data 
    float x = 0.5f;
    float y = -x;
        float[] verticesBottomRight = {
    // x     y     z    u     v
    x, y, 0.0f, 0.0f, 1.0f,
    x+0.5f, y, 0.0f, 1.0f, 1.0f,
    x+0.5f, y-0.5f, 0.0f, 1.0f, 0.0f,
    x, y-0.5f, 0.0f, 0.0f, 0.0f
        };

        int[] indices = {
            0, 1, 2,
            2, 3, 0
        };

        float speed;

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
    KeyboardInput.keyMapCreate();
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
        main.input();
        main.update();
        render.update();
        }
  }

	@Override
	public void input() {

    if(KeyboardInput.keyCallback(KeyboardInput.getKey("A"))) {
      x-=0.01f;
    }

    if(KeyboardInput.keyCallback(KeyboardInput.getKey("D"))) {
      x+=0.01f;
    }

    if(KeyboardInput.keyCallback(KeyboardInput.getKey("W"))) {
      y+=0.01f;
    }

    if(KeyboardInput.keyCallback(KeyboardInput.getKey("S"))) {
      y-=0.01f;
    }
    
    verticesBottomRight = new float[] {
    // x     y     z    u     v
    x, y, 0.0f, 0.0f, 1.0f,
    x+0.5f, y, 0.0f, 1.0f, 1.0f,
    x+0.5f, y-0.5f, 0.0f, 1.0f, 0.0f,
    x, y-0.5f, 0.0f, 0.0f, 0.0f
    };
	}

	@Override
	public void update() {
    render.updateData(verticesBottomRight, indices, render.mesh3);
	}




	public static void main(String[] args) {
		// Initialization and game loop
	    main.init();
      main.loop();
	}

}
