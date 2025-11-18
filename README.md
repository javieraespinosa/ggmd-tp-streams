
[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/codespaces/new?hide_repo_select=true&ref=main&repo=1097878214&machine=standardLinuxXL)

 ### Build image

```sh
# Apple Silicon version
docker build --build-arg ARCH=arm64 -t jaeo/ggmd:tp-stream .

# Codespaces version
docker build -t jaeo/ggmd:tp-stream .
```


### Local Cluster Test

#### Terminal 1

* Create container: 

```sh
docker run --rm -it \
   --name stormracer  \
   -v "${PWD}"/StormRacer:/storm/examples/StormRacer \
   -v "${PWD}"/StreamRunners4docker:/StreamRunners   \
   jaeo/ggmd:tp-stream /bin/bash
```

* Compile StormRacer topology:

```sh
cd /storm/examples/StormRacer/
mvn package
```

* Run StormRacer topology in local cluster for `120` secs:

```sh
storm local --local-ttl 120 target/stormTP-1.0.jar stormTP.topology.TopologyT1 9001 9005
```


#### Terminal 2

* Connect to container:

```sh
docker exec -it stormracer /bin/bash
```

* Start producer:

```sh
cd /ggmd-storm-stream
./startStream.sh tortoise 10 150 9001
```

Stop containers: **CTRL + D**


#### Terminal 3

* Connect to container:

```sh
docker exec -it stormracer /bin/bash
```

* Start listener output:

```sh
cd /ggmd-storm-listner
./startListner.sh 9005
```

Stop containers: **CTRL + D**


--------------

### Storm Cluster Test

#### Terminal 1

* Start cluster: 

```sh
docker compose up
```

#### Terminal 2

* Connect to the storm client:

```sh
docker compose exec client /bin/bash
```

* Compile the **storm topology**:

```sh
cd /storm/examples/StormRacer/
mvn package
```

* Submit to cluster:

```sh
storm local --local-ttl 120 target/stormTP-1.0.jar stormTP.topology.TopologyT1 9001 9005
```

#### Terminal 3

* Connect to the storm client:

```sh
docker compose exec client /bin/bash
```

* Start the **runners**

```sh
cd /ggmd-storm-stream
./startStream.sh tortoise 10 150 9001
```

#### Terminal 4

* Connect to container:

```sh
docker exec -it stormracer /bin/bash
```

* Start listener output:

```sh
cd /ggmd-storm-listner
./startListner.sh 9005
```

Stop containers: **CTRL + D**
> The Storm UI is available at http://localhost:8080 when working locally.
> 
> See the forward ports sections if you are using Github codespaces
