# RePhysics

Includes physic, math classes
Not necessary because you can write your own physic module

## Buid & Install

### Clone repository 

```
git clone https://github.com/IsThisALis/ReCore.git
cd ReCore/recore-modules/rephysics
```

### Build

```
mvn package -Dnative.target=natives-linux
```

### Install to local Maven repository

```
mvn install -Dnative.target=natives-linux
```

### Add as dependency

```xml
<dependency>
    <groupId>org.gfs</groupId>
    <artifactId>rephysics</artifactId>
    <version>0.0.4-alpha</version>
</dependency>
```
