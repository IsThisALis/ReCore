# RExample

Includes examples of ReCore usage. 
Only for study

## Buid & Run

### Clone repository 

```
git clone https://github.com/IsThisALis/ReCore.git
cd ReCore/recore-modules/rexample
```

### Build

```
mvn package -Dnative.target=natives-linux
```

### Run

```
java -jar target/rexample-0.0.5-alpha.jar
```

## Performance 

### System:
 OS: **Arch Linux**
 Kernel: **Linux**
 CPU: **AMD R5 5625U**
 GPU: **Vega 7 (Integrated graphics)**

### Standart (5 Mesh, 5 Textures):
 GPU:
 Max: 4%
 Min: 2%
 Average: 4%
 (Depends on zoom)

 CPU:
 Max: 0.2%
 Min: 0.1%
 Average: 0.1%

 RAM:
 Max: 107 MB
 Min: 75 MB

 FPS: 60+

### Stress-test (10005 Mesh, 5 Textures):
 GPU:
 Max: 100%
 Min: 98%
 Average: 99%
 (Depends on zoom) 

 CPU:
 Max: 2%
 Min: 0.1%
 Average: 0.2%

 RAM:
 Max: 165 MB
 Min: 145 MB

 FPS: 10
