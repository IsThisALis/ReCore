# ReCore (main module)

## Buid & Run

### Clone repository 

```
git clone https://github.com/IsThisALis/ReCore.git
cd ReCore/recore-modules/recore
```

### Build

```
mvn package -Plinux
```

### Install to local Maven repository

```
mvn install -P(linux/windows/OSX)
```

## Installation

### Maven
```xml
<dependency>
    <groupId>org.gfs.recore</groupId>
    <artifactId>recore</artifactId>
    <version>0.0.4-alpha</version>
</dependency>
```
