package org.gfs.recore.example;

import org.gfs.game.graphic.Render;
import org.gfs.game.graphic.TextureManager;

import org.gfs.recore.core.ApplicationLogic;

import org.gfs.recore.graphics.window.Params;
import org.gfs.recore.graphics.window.Window;

public class Main implements ApplicationLogic {

  TextureManager textureManager = new TextureManager();
  Render render = new Render();
  Window window = new Window();

  Params params = Params.getParams();

  private static Main main = new Main();

	@Override
	public void init() {
    params.setTitle("Elderness");
    params.setHeight(1200);
    params.setWidth(1920);
    
    window.init(); 
    render.init();
	}

	@Override
	public void cleanup() {
		System.out.println("ReCore: Cleanup");
	}

	@Override
	public void loop() {
        while (!window.isWindowShouldClose()) {
          
		        window.loop();
            render.update();
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
