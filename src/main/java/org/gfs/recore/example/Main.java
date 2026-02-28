package org.gfs.recore.example;

//Core imports
import org.gfs.recore.core.ApplicationLogic;

//Graphic imports
//Window
import org.gfs.recore.graphics.window.Params;
import org.gfs.recore.graphics.window.Window;

import org.gfs.recore.example.shaders.Renderer;

public class Main implements ApplicationLogic {

  Window window = new Window();
  Params params = Params.getParams();

  Renderer renderer;

  private static Main main = new Main();

	@Override
	public void init() {
    params.setTitle("Elderness");
    params.setHeight(1200);
    params.setWidth(1920);

		System.out.println("ReCore: Initializing");

    window.init();

    renderer = new Renderer();

    renderer.init();
	}

	@Override
	public void cleanup() {
		System.out.println("ReCore: Cleanup");
	}

	@Override
	public void loop() {
    while(!window.isWindowShouldClose()) {
		        window.loop();
            renderer.render();
          }    
    }

	@Override
	public void input() {
		System.out.println("ReCore: Registering user input");
	}

	@Override
	public void update() {
		System.out.println("ReCore: Updating some data");
	}




	public static void main(String[] args) {
		main.init();
    main.loop();
	}

}
