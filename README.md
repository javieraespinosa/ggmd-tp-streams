
[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/codespaces/new?hide_repo_select=true&ref=main&repo=648726487)

 ### Build image

```sh
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
storm local --local-ttl 120 target/storm-ggmd-2.8.3.jar stormTP.topology.TopologyT1 1 1
```


#### Terminal 2

* Connect to container:

```sh
docker exec -it stormracer /bin/bash
```

* Start producer:

```sh
cd /StreamRunners
./startStream.sh tortoise 1 9001
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
storm jar target/storm-ggmd-2.8.3.jar stormTP.topology.TopologyT1 1 1
```

#### Terminal 3

* Connect to the storm client:

```sh
docker compose exec client /bin/bash
```

* Start the **runners**

```sh
cd /StreamRunners
./startStream.sh tortoise 10 9001
```

> The Storm UI is available at http://localhost:8080 when working locally.
> 
> See the forward ports sections if you are using Github codespaces