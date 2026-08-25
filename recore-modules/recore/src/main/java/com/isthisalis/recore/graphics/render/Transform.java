package com.isthisalis.recore.graphics.render;

import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.isthisalis.recore.graphics.shaders.uniforms.Uniform;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.Builder.Default;

@Builder
public class Transform {
    
    private @Default @Setter @Getter Vector3f position = new Vector3f(0, 0, 0);
    private @Default @Setter @Getter Vector3f rotation = new Vector3f(0, 0, 0);
    private @Default @Setter @Getter Vector3f scale    = new Vector3f(1, 1, 1);

    private @NonNull Uniform uniform;

    private @Default float[] modelData = new float[16];
    private @Default Matrix4f modelMatrix = new Matrix4f();


    public Matrix4f buildModelMatrix() {
        return modelMatrix.identity()
          .translate(position.x, position.y, 0)
          .translate(scale.x * 0.5f, scale.y, 0)
          .scale(scale);
    //return modelMatrix.identity().translate(this.position).rotateZ(this.rotation.z).scale(this.scale);
  }


    /**
    * Moves mesh to position 
    *
    * @param x New x position
    * @param y New y position
    * @param speed Speed to move object with 
    * @param deltaTime Time between past and now frames 
    */
    public void move(float x, float y, float speed, float deltaTime) {
      position.x += x*speed*deltaTime;
      position.y += y*speed*deltaTime; 
  }

  public void bind() {
    buildModelMatrix().get(modelData);
    glUniformMatrix4fv(uniform.location(), false, modelData);
  }
}
