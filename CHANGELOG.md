# Changelog

## API

    Added setPosition(float x, float y) to camera, used when need to set position instead of moving camera - [9f05059](https://github.com/IsThisALis/ReCore/commit/9f0505918cbb31fac98ff4291c5e9c79b800fe97).
    Added keyReleased(int key) and keyRepeated(int key) to track key pressed or still in pressed state - [795835f](https://github.com/IsThisALis/ReCore/commit/795835fa5caaf5c6eb27436ed5617610ead88e57).
    Added getPosition(), move(float x, float y, float speed, float deltaTime), shortened setPosition(float x, float y) - [6d375d5](https://github.com/IsThisALis/ReCore/commit/6d375d52f3ab56a37a3f34808571ea20c4a85efc)
    Added getPosition() to camera - [33fc251](https://github.com/IsThisALis/ReCore/commit/33fc25109c79326f9a7e1e25410e43d6a19431e8)

## Fixes

    Fixed null ShaderProgram error in mesh when texture is not used - [67accc6](https://github.com/IsThisALis/ReCore/commit/67accc6c3992bb6b107723e760f4ab4a79641528).
    Fixed errors and optimized projection matrix, zoom now multiplicative - [33fc251](https://github.com/IsThisALis/ReCore/commit/33fc25109c79326f9a7e1e25410e43d6a19431e8)

